package petfinder.petfinderapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Demanda;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.enums.EnumStatusDemanda;
import petfinder.petfinderapi.listaObj.ListaObj;
import petfinder.petfinderapi.requisicao.CriacaoDemanda;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/demanda")
public class DemandaController {

    @Autowired
    private petfinder.petfinderapi.repositorios.DemandaRepositorio demandaRepositorio;

    private String[] listaCategoria = {"ADOCAO", "PAGAMENTO", "RESGATE"};
    private String[] listaStatus = {"ABERTO", "CONCLUIDO", "CANCELADO", "DOCUMENTO_VALIDO",
    "PGTO_REALIZADO_USER", "PGTO_REALIZADO_INST", "RESGATE_INVALIDO", "RESGATE_VALIDO"};

    private ListaObj<String> categoria = new ListaObj<String>(listaCategoria);
    private ListaObj<String> status = new ListaObj<String>(listaStatus);

    @PostMapping
    public ResponseEntity postDemanda(@RequestBody CriacaoDemanda novaDemanda){
        Demanda demanda = new Demanda(novaDemanda);

        if (Objects.isNull(novaDemanda) || !categoria.verificaElemento(novaDemanda.getCategoria())){
            return ResponseEntity.status(400).build();
        }
        demandaRepositorio.save(demanda);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity getDemandas(){
        List<Demanda> lista = demandaRepositorio.findAll();
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/user/{fkUsuario}")
    public ResponseEntity getDemandasUserById(@PathVariable int fkUsuario){
        List<Demanda> lista = demandaRepositorio.findAllByFkUsuario(fkUsuario);
        if (lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return  ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/user/{fkUsuario}/status-aberto")
    public ResponseEntity getDemandasUserByIdStatusAberto(@PathVariable int fkUsuario){
        List<Demanda> lista = demandaRepositorio.findAllByFkUsuarioAndStatus(fkUsuario, "ABERTO");
        if (lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return  ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDemandaById(@PathVariable int id){
        Optional<Demanda> demanda = demandaRepositorio.findById(id);
        if (demanda.isPresent()){
            return ResponseEntity.status(200).body(demanda.get());
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/atualizar-demanda/{id}")
    public ResponseEntity putDemanda(@PathVariable int id, @RequestBody @Valid Demanda demandaAtualizar){
        if (demandaRepositorio.existsById(id)){
            Demanda demanda = demandaRepositorio.getById(id);

            demanda.setCategoria(demandaAtualizar.getCategoria());
            demanda.setDataAbertura(demandaAtualizar.getDataAbertura());
            demanda.setDataFechamento(demandaAtualizar.getDataFechamento());
            demanda.setStatus(demandaAtualizar.getStatus());
            Demanda demandaAtualizada = demandaRepositorio.save(demanda);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PatchMapping("/atualizar-status/{id}")
    public ResponseEntity patchDemandaStatus(@PathVariable int id, @RequestBody EnumStatusDemanda statusAtualizar){
        if (demandaRepositorio.existsById(id)){

            Demanda demanda = demandaRepositorio.getById(id);

//            demanda.setStatus(statusAtualizar);
            Demanda demandaAtulizada = demandaRepositorio.save(demanda);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deleteDemanda(@PathVariable int id){
        if (demandaRepositorio.existsById(id)){
            demandaRepositorio.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

}
