package petfinder.petfinderapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Demanda;
import petfinder.petfinderapi.listaObj.ListaObj;
import petfinder.petfinderapi.repositorios.DemandaRepositorio;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.requisicao.CriacaoDemanda;
import petfinder.petfinderapi.resposta.Message;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/demandas")
public class DemandaController {

    // repositorios
    @Autowired
    private DemandaRepositorio demandaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private InstituicaoRepositorio instituicaoRepositorio;

    // enums
    private ListaObj<String> categoriasPossiveis = new ListaObj<String>(new String[]{"ADOCAO", "PAGAMENTO", "RESGATE"});
    private ListaObj<String> statusPossiveis = new ListaObj<String>(new String[]{"ABERTO", "CONCLUIDO", "CANCELADO", "DOCUMENTO_VALIDO",
            "PGTO_REALIZADO_USER", "PGTO_REALIZADO_INST", "RESGATE_INVALIDO", "RESGATE_VALIDO", "EM_ANDAMENTO"});

    private ListaObj<String> tiposMenssagensPossiveis = new ListaObj<String>(new String[]{"ARQUIVO", "MENSSAGEM"});

    // endpoints
    @PostMapping
    public ResponseEntity<Object> postDemanda(@RequestBody @Valid CriacaoDemanda novaDemanda){

        if (instituicaoRepositorio.existsById(novaDemanda.getFkIntituicao())){

            // verificando corpo da requisição
            if (!categoriasPossiveis.elementoExiste(novaDemanda.getCategoria())){

                // 400 bad request
                return ResponseEntity.status(400).body(new Message("Corpo da requisição vazio ou categoria de demanda inválida"));
            }

            if (!usuarioRepositorio.existsById(novaDemanda.getFkUsuario())){
                // 404 Usuario not found
                return ResponseEntity.status(404).body(new Message("Usuario não encontrada"));
            }

            // 201 recurso criado
            Demanda demanda = new Demanda(novaDemanda);
            demanda.setCategoria(demanda.getCategoria());
            demandaRepositorio.save(demanda);
            return ResponseEntity.status(201).build();

        }
        // 404 instituição not found
        return ResponseEntity.status(404).body(new Message("Instituição não encontrada"));

    }

    @GetMapping
    public ResponseEntity getDemanda(){
        List<Demanda> lista = demandaRepositorio.findAll();

        // 204, em caso de lista vazia
        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(new Message("Lista vázia"));
        }

        // 200
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/{idDemanda}")
    public ResponseEntity<Object> getDemandaById(@PathVariable int idDemanda){
        Optional<Demanda> demanda = demandaRepositorio.findById(idDemanda);

        // verificando existencia da demanda
        if (demanda.isPresent()){

            // 200
            return ResponseEntity.status(200).body(demanda.get());
        }

        // 404 demanda não encontrada
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/user/{fkUsuario}")
    public ResponseEntity<Object> getDemandasUserById(@PathVariable int fkUsuario){

        // verificando se usuario existe
        if (usuarioRepositorio.existsById(fkUsuario)) {
            // Retornar demandas isoladas de acordo com o status
            // armazenando lista de demandas do usuário
            List<Demanda> lista = demandaRepositorio.findAllByFkUsuario(fkUsuario);

            // verificando se a lista de demandas do usuário está vazia
            if (lista.isEmpty()){

                // 204 no content
                return ResponseEntity.status(204).body(new Message("Lista vázia"));
            }

            // 200 
            return  ResponseEntity.status(200).body(lista);
        }

        // 404 usuario not found
        return ResponseEntity.status(404).body(new Message("Usuário não encontrado"));
    }

    @GetMapping("/instituicao/{fkInstituicao}")
    public ResponseEntity<Object> getDemandaAbertoInstituicao(@PathVariable int fkInstituicao){
        if (instituicaoRepositorio.existsById(fkInstituicao)){
            List<Demanda> lista = demandaRepositorio.findAllByFkInstituicaoAndStatus(fkInstituicao, "aberto");
            if (lista.isEmpty()){
                return ResponseEntity.status(204).body(new Message("Lista vázia"));
            }
            return  ResponseEntity.status(200).body(lista);
        }
        // 404 instituição not found
        return ResponseEntity.status(404).body(new Message("Instituição não encontrada"));

    } /// Terminar este!!!!!!

    @PutMapping("/{idDemanda}")
    public ResponseEntity<Object> patchDemanda(@PathVariable int idDemanda, @RequestBody @Valid Demanda demandaAtualizar){

        // verificando se demanda existe
        if (demandaRepositorio.existsById(idDemanda)){

            // validando categoria da demanda
            if (!categoriasPossiveis.elementoExiste(demandaAtualizar.getCategoria())){

                // 400 bad request - categoria da demanda inválida
                return ResponseEntity.status(400).body(new Message("Categoria da demanda inválida"));
            }

            // validando status da demanda
            if (!statusPossiveis.elementoExiste(demandaAtualizar.getStatus())) {

                // 400 bad request - status da demanda inválida
                return ResponseEntity.status(400).body(new Message("Status da demanda inválido"));
            }

            // atualizando valores da demanda
            Demanda demanda = demandaRepositorio.getById(idDemanda);
            demanda.setCategoria(demandaAtualizar.getCategoria());
            demanda.setDataAbertura(demandaAtualizar.getDataAbertura());
            demanda.setDataFechamento(demandaAtualizar.getDataFechamento());
            demanda.setStatus(demandaAtualizar.getStatus());
            demandaRepositorio.save(demanda);

            // 200
            return ResponseEntity.status(200).build();
        }

        // 404 demanda não encontrada
        return ResponseEntity.status(404).build();
    }

    @PatchMapping("/colaborador-abrir/{idDemanda}/{fkColaborador}")
    public ResponseEntity<Object> patchDemandaColaborador(@PathVariable int idDemanda, @PathVariable int fkColaborador){

        // verificando existencia da demanda
        if (demandaRepositorio.existsById(idDemanda)){
            if (usuarioRepositorio.existsById(fkColaborador)){
                // atualizando status da demanda
                Demanda demanda = demandaRepositorio.getById(idDemanda);
                demanda.setStatus("em_andamento");
                demanda.setFkColaborador(fkColaborador);
                demandaRepositorio.save(demanda);
                // 200 - empty response
                return ResponseEntity.status(200).build();
            }
            // 404 - demanda não encontrada
            return ResponseEntity.status(404).body(new Message("Colaborador não encontrada"));
        }
        // 404 - demanda não encontrada
        return ResponseEntity.status(404).body(new Message("Demanda não encontrada"));
    }

    @PatchMapping("/status/{idDemanda}/{statusAtualizar}")
    public ResponseEntity<Object> patchDemandaStatus(@PathVariable int idDemanda, @PathVariable String statusAtualizar){

        // verificando existencia da demanda
        if (demandaRepositorio.existsById(idDemanda)){

            // verificando validade do status
            if (!statusPossiveis.elementoExiste(statusAtualizar)){

                // 404 - status inválido
                return ResponseEntity.status(404).body(new Message("Status inválido"));
            }

            // atualizando status da demanda
            Demanda demanda = demandaRepositorio.getById(idDemanda);
            demanda.setStatus(statusAtualizar.toLowerCase(Locale.ROOT));
            demandaRepositorio.save(demanda);

            // 200 - empty response
            return ResponseEntity.status(200).build();
        }

        // 404 - demanda não encontrada
        return ResponseEntity.status(404).body(new Message("Demanda não encontrada"));
    }

    @DeleteMapping("/{idDemanda}")
    public ResponseEntity<Object> deleteDemanda(@PathVariable int idDemanda){

        // verificando se demanda existe
        if (demandaRepositorio.existsById(idDemanda)){

            // deletando demanda
            demandaRepositorio.deleteById(idDemanda);

            // 200
            return ResponseEntity.status(200).build();
        }

        // 404 demanda não encontrada
        return ResponseEntity.status(404).build();
    }

    // endpoint menssagem
//    @PostMapping("/mensagens")
//    public ResponseEntity postMensagem(@RequestBody @Valid Message novaMensagem){
//
////
//
//    }
}
