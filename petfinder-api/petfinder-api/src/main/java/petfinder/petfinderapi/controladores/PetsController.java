package petfinder.petfinderapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Caracteristica;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.entidades.Premio;
import petfinder.petfinderapi.repositorios.CaracteristicaRepositorio;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.repositorios.PremioRepositorio;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetsController {

    @Autowired
    private PetRepositorio repositoryPet;

    @Autowired
    private PremioRepositorio repositoryPremio;

    @Autowired
    private CaracteristicaRepositorio repositoryCaracteristica;

    public static List<Pet> pets = new ArrayList<>();
    public static List<Premio> premios = new ArrayList<>();

    @PostMapping("/post-pet")
    public ResponseEntity postPet(
            @RequestBody Pet novoPet) {
        repositoryPet.save(novoPet);
        return ResponseEntity.status(201).build();
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
        List<Pet> lista = repositoryPet.findById(id);
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("filtro-id-pets/instituicao/{id}")
    ResponseEntity getByInstiuicaoId(@PathVariable int id) {
        List<Pet> lista = repositoryPet.findByFkInstituicao(id);
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("delete-id-pet/{id}")
    ResponseEntity deleteByIdpet(@PathVariable int id) {
        repositoryPet.deleteById(id);
        return ResponseEntity.status(200).build();
    }


    // ================================================= //
    // Premios
    // ================================================= //

    @PostMapping("post-premio")
    public ResponseEntity postPremio(
            @RequestBody Premio novoPremio) {
        repositoryPremio.save(novoPremio);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("get-premios")
    public ResponseEntity getPremios() {
        List<Premio> lista = repositoryPremio.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/filtro-id-premio/{id}")
    ResponseEntity getByidPremios(@PathVariable int id) {
        List<Premio> lista = repositoryPremio.findById(id);
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("filtro-id/pet/{id}")
    ResponseEntity getByPetId(@PathVariable int id) {
        List<Premio> lista = repositoryPremio.findByFkPet(id);
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("delete-id-premio/{id}")
    ResponseEntity deleteByIdPremio(@PathVariable int id) {
        repositoryPremio.deleteById(id);
        return ResponseEntity.status(200).build();
    }

    // ================================================= //
    // Caracteristicas
    // ================================================= //

    @PostMapping("post-caracteristica")
    public ResponseEntity postCaracteristica(
            @RequestBody Caracteristica novaCaracteristica) {
        repositoryCaracteristica.save(novaCaracteristica);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("get-caracteristicas")
    public ResponseEntity getCaracteristica() {
        List<Caracteristica> lista = repositoryCaracteristica.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/filtro-id-caracteristica/{id}")
    ResponseEntity getByIdCaracteristica(@PathVariable int id) {
        List<Caracteristica> lista = repositoryCaracteristica.findById(id);
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("delete-id-caracteristica/{id}")
    ResponseEntity deleteByIdCaracteristca(@PathVariable int id) {
        repositoryCaracteristica.deleteById(id);
        return ResponseEntity.status(200).build();
    }
}
