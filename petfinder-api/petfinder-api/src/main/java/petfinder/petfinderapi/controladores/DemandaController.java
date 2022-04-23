package petfinder.petfinderapi.controladores;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Demanda;
import petfinder.petfinderapi.enums.EnumStatusDemanda;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demanda")
public class DemandaController {

    @Autowired
    private petfinder.petfinderapi.repositorios.DemandaRepositorio demandaRepositorio;

    @GetMapping
    public ResponseEntity getDemanda(){
        List<Demanda> lista = demandaRepositorio.findAll();
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDemandaById(@PathVariable int id){
        Optional<Demanda> demanda = demandaRepositorio.findById(id);
        if (demanda.isPresent()){
            return ResponseEntity.status(200).body(demanda.get()); // lembrar teste
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping
    public ResponseEntity postDemanda(@RequestBody @Valid Demanda novaDemanda){
        demandaRepositorio.save(novaDemanda);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/atualizar/{id}")
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
        System.out.println(" ======== MUNDIAL" + statusAtualizar);
        if (demandaRepositorio.existsById(id)){

            Demanda demanda = demandaRepositorio.getById(id);

            demanda.setStatus(statusAtualizar);
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
