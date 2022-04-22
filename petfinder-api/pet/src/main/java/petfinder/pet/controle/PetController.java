package petfinder.pet.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.pet.entidade.Mimo;
import petfinder.pet.entidade.Pet;
import petfinder.pet.repositorio.MimoRepository;
import petfinder.pet.repositorio.PetRepository;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetRepository repositoryPet;

    @Autowired
    private MimoRepository repositoryMimo;

    @PostMapping("/postPets")
    public ResponseEntity postPet(
            @RequestBody Pet novoPet) {
        repositoryMimo.save(novoPet.getMimo());
        repositoryPet.save(novoPet);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/getPets")
    public ResponseEntity getPets() {
        List<Pet> lista = repositoryPet.findAll();

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/filtro-id/{id}")
    ResponseEntity getByid(@PathVariable int id) {
        List<Pet> lista = repositoryPet.findById(id);
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("filtro-id/instituicao/{id}")
    ResponseEntity getByInstiuicaoId(@PathVariable int id) {
        List<Pet> lista = repositoryPet.findByIdInstituicao(id);
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("delete-id/{id}")
    ResponseEntity deleteById(@PathVariable int id) {
        repositoryPet.deleteById(id);
        return ResponseEntity.status(200).build();
    }











//    @PostMapping("post-mimos")
//    public ResponseEntity postMimo(
//            @RequestBody Mimo novoMimo) {
//        repositoryMimo.save();
//        return ResponseEntity.status(201).build();
//    }
//
//    @GetMapping("get-mimos")
//    public ResponseEntity getMimos() {
//        List<Mimo> lista = repositoryMimo.findAll();
//        return ResponseEntity.status(200).body(lista);
//    }
//
//    @GetMapping("get-mimos/{id}")
//    public ResponseEntity getMimoPorId(@PathVariable int id) {
//        List<Mimo> lista =  repositoryMimo.findById(id);
//        return ResponseEntity.status(200).body(lista);
//    }
//
//    @GetMapping("get-mimos-pet/{id}")
//    public ResponseEntity getMimosByPetId(@PathVariable int id) {
//
//    }
}