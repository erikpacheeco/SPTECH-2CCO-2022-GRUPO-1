package petfinder.petfinderapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Demanda;
import petfinder.petfinderapi.listaObj.ListaObj;
import petfinder.petfinderapi.repositorios.DemandaRepositorio;
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

    // enums
    private ListaObj<String> categoriasPossiveis = new ListaObj<String>(new String[]{"ADOCAO", "PAGAMENTO", "RESGATE"});
    private ListaObj<String> statusPossiveis = new ListaObj<String>(new String[]{"ABERTO", "CONCLUIDO", "CANCELADO", "DOCUMENTO_VALIDO",
            "PGTO_REALIZADO_USER", "PGTO_REALIZADO_INST", "RESGATE_INVALIDO", "RESGATE_VALIDO"});

    // endpoints
    @PostMapping
    public ResponseEntity<Object> postDemanda(@RequestBody @Valid CriacaoDemanda novaDemanda){
        
        // verificando corpo da requisição
        if (!categoriasPossiveis.elementoExiste(novaDemanda.getCategoria())){
            
            // 400 bad request
            return ResponseEntity.status(400).body(new Message("Corpo da requisição vazio ou categoria de demanda inválida"));
        }
        
        // 201 recurso criado
        Demanda demanda = new Demanda(novaDemanda);
        demanda.setCategoria(demanda.getCategoria());
        demandaRepositorio.save(demanda);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<Demanda>> getDemandas(){
        List<Demanda> lista = demandaRepositorio.findAll();

        // 204, em caso de lista vazia
        if (lista.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        // 200
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/user/{fkUsuario}")
    public ResponseEntity<Object> getDemandasUserById(@PathVariable int fkUsuario){

        // verificando se usuario existe
        if (usuarioRepositorio.existsById(fkUsuario)) {

            // armazenando lista de demandas do usuário
            List<Demanda> lista = demandaRepositorio.findAllByFkUsuario(fkUsuario);

            // verificando se a lista de demandas do usuário está vazia
            if (lista.isEmpty()){

                // 204 no content
                return ResponseEntity.status(204).build();
            }

            // 200 
            return  ResponseEntity.status(200).body(lista);
        }

        // 404 usuario not found
        return ResponseEntity.status(404).body(new Message("Usuário não encontrado"));
    }

    @GetMapping("/user/{fkUsuario}/status-aberto")
    public ResponseEntity<Object> getDemandasUserByIdStatusAberto(@PathVariable int fkUsuario){
        List<Demanda> lista = demandaRepositorio.findAllByFkUsuarioAndStatus(fkUsuario, "ABERTO");
        if (lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return  ResponseEntity.status(200).body(lista);
    } /// Terminar este!!!!!!
    // !!!!!!!!!!!!!!!!!!!!!!!
    // 💀💀💀 Lucas msg: ao meu ver, "status-aberto" pode ser apenas "status", deixando o usuário passar que tipo de status quer filtrar!

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDemandaById(@PathVariable int id){
        Optional<Demanda> demanda = demandaRepositorio.findById(id);

        // verificando existencia da demanda
        if (demanda.isPresent()){

            // 200
            return ResponseEntity.status(200).body(demanda.get());
        }

        // 404 demanda não encontrada
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> patchDemanda(@PathVariable int id, @RequestBody @Valid Demanda demandaAtualizar){

        // verificando se demanda existe
        if (demandaRepositorio.existsById(id)){

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
            Demanda demanda = demandaRepositorio.getById(id);
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

    @PatchMapping("/status/{id}/{statusAtualizar}")
    public ResponseEntity<Object> patchDemandaStatus(@PathVariable int id, @PathVariable String statusAtualizar){

        // verificando existencia da demanda
        if (demandaRepositorio.existsById(id)){

            // verificando validade do status
            if (!statusPossiveis.elementoExiste(statusAtualizar)){

                // 404 - status inválido
                return ResponseEntity.status(404).body(new Message("Status inválido"));
            }

            // atualizando status da demanda
            Demanda demanda = demandaRepositorio.getById(id);
            demanda.setStatus(statusAtualizar.toLowerCase(Locale.ROOT));
            demandaRepositorio.save(demanda);

            // 200 - empty response
            return ResponseEntity.status(200).build();
        }

        // 404 - demanda não encontrada
        return ResponseEntity.status(404).body(new Message("Demanda não encontrada"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDemanda(@PathVariable int id){

        // verificando se demanda existe
        if (demandaRepositorio.existsById(id)){

            // deletando demanda
            demandaRepositorio.deleteById(id);

            // 200
            return ResponseEntity.status(200).build();
        }

        // 404 demanda não encontrada
        return ResponseEntity.status(404).build();
    }
}
