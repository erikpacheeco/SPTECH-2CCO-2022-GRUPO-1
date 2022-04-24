package petfinder.petfinderapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.*;
import petfinder.petfinderapi.repositorios.CaracteristicaRepositorio;
import petfinder.petfinderapi.repositorios.PetHasCaracteristicaRepositorio;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.repositorios.PremioRepositorio;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pets")
public class PetsController {

    @Autowired
    private PetRepositorio repositoryPet;

    @Autowired
    private PremioRepositorio repositoryPremio;

    @Autowired
    private CaracteristicaRepositorio repositoryCaracteristica;

    @Autowired
    private PetHasCaracteristicaRepositorio repositoryHasCaracteristica;


    public static List<Pet> pets = new ArrayList<>();
    public static List<Premio> premios = new ArrayList<>();

    @PostMapping("/post-pet")
    public ResponseEntity<Object> postPet(
            @RequestBody @Valid Pet novoPet) {
        if (Objects.nonNull(novoPet)) {
            repositoryPet.save(novoPet);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/atualizar-pet/{indice}")
    public ResponseEntity putPet(@RequestBody Pet petAtualizado, @PathVariable int indice) {
        if (repositoryPet.existsById(indice)) {
            petAtualizado.setId(indice);
            repositoryPet.save(petAtualizado);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/get-pets")
    public ResponseEntity getPets() {
        List<Pet> lista = repositoryPet.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/filtro-id-pet/{id}")
    ResponseEntity getByid(@PathVariable int id) {
        if (repositoryPet.existsById(id)) {
            List<Pet> lista = repositoryPet.findById(id);
            return ResponseEntity.status(200).body(lista);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/filtro-id-pets/instituicao/{id}")
    ResponseEntity getByInstiuicaoId(@PathVariable int id) {
        if (repositoryPet.existsById(id)) {
            List<Pet> lista = repositoryPet.findByFkInstituicao(id);
            return ResponseEntity.status(200).body(lista);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/delete-id-pet/{id}")
    ResponseEntity deleteByIdpet(@PathVariable int id) {
        if (repositoryPet.existsById(id)) {
            repositoryPet.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }


    // ================================================= //
    // Premios
    // ================================================= //

    @PostMapping("/post-premio")
    public ResponseEntity postPremio(
            @RequestBody Premio novoPremio) {

        if (Objects.nonNull(novoPremio)) {
            repositoryPremio.save(novoPremio);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/atualizar-premio/{indice}")
    public ResponseEntity putPremio(@RequestBody Premio premioAtualizado, @PathVariable int indice) {
        if (repositoryPremio.existsById(indice)) {
            premioAtualizado.setId(indice);
            repositoryPremio.save(premioAtualizado);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/get-premios")
    public ResponseEntity getPremios() {
        List<Premio> lista = repositoryPremio.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/filtro-id-premio/{id}")
    ResponseEntity getByidPremios(@PathVariable int id) {
        if (repositoryPremio.existsById(id)) {
            List<Premio> lista = repositoryPremio.findById(id);
            return ResponseEntity.status(200).body(lista);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/delete-id-premio/{id}")
    ResponseEntity deleteByIdPremio(@PathVariable int id) {
        if (repositoryPremio.existsById(id)) {
            repositoryPremio.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    // ================================================= //
    // Caracteristicas
    // ================================================= //

    @PostMapping("/post-caracteristica")
    public ResponseEntity postCaracteristica(
            @RequestBody Caracteristica novaCaracteristica) {
        if (Objects.nonNull(novaCaracteristica)) {
            repositoryCaracteristica.save(novaCaracteristica);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/atualizar-caracteristica/{indice}")
    public ResponseEntity putCaracteristica(@RequestBody Caracteristica caracteristicaAtualizada, @PathVariable int indice) {
        if (repositoryCaracteristica.existsById(indice)) {
            caracteristicaAtualizada.setId(indice);
            repositoryCaracteristica.save(caracteristicaAtualizada);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/get-caracteristicas")
    public ResponseEntity getCaracteristica() {
        List<Caracteristica> lista = repositoryCaracteristica.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/filtro-id-caracteristica/{id}")
    ResponseEntity getByidCaracteristca(@PathVariable int id) {
        if (repositoryCaracteristica.existsById(id)) {
            List<Caracteristica> lista = repositoryCaracteristica.findById(id);
            return ResponseEntity.status(200).body(lista);
        }
        return ResponseEntity.status(404).build();
    }


    @DeleteMapping("/delete-id-caracteristica/{id}")
    ResponseEntity deleteByIdCaracteristica(@PathVariable int id) {
        if (repositoryCaracteristica.existsById(id)) {
            repositoryCaracteristica.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    // ================================================= //
    // Pet Has Caracteristicas
    // ================================================= //

    @PostMapping("/post-has-caracteristica")
    public ResponseEntity postHasCaracteristica(
            @RequestBody PetHasCaracteristica novaHasCaracteristica) {
        if (Objects.nonNull(novaHasCaracteristica)) {
            repositoryHasCaracteristica.save(novaHasCaracteristica);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/get-has-caracteristicas")
    public ResponseEntity getHasCaracteristica() {
        List<PetHasCaracteristica> lista = repositoryHasCaracteristica.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @PutMapping("/atualizar-has-caracteristica/{indice}")
    public ResponseEntity putHasCaracteristica(@RequestBody PetHasCaracteristica hasCaracteristicaAtualizada, @PathVariable int indice) {
        if (repositoryHasCaracteristica.existsById(indice)) {
            hasCaracteristicaAtualizada.setFkPet(indice);
            repositoryHasCaracteristica.save(hasCaracteristicaAtualizada);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

}