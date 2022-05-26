package petfinder.petfinderapi.controladores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.*;
import petfinder.petfinderapi.repositorios.CaracteristicaRepositorio;
import petfinder.petfinderapi.repositorios.PetHasCaracteristicaRepositorio;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.repositorios.PremioRepositorio;

import javax.validation.Valid;
import javax.websocket.OnError;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pets")
@Tag(name = "Pet",description = "API para controlar os pets, os prêmios e as caracteristicas")
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

    @PatchMapping(value = "/foto/{id}", consumes = "image/jpeg")
    @Operation(description = "EndPoint para cadastrar a foto de perfil do animal")
    public ResponseEntity patchFoto(@PathVariable int id, @RequestBody byte[] novaFoto) {

        Pet petEncontrado = repositoryPet.getById(id);
        petEncontrado.setFotoPerfil(novaFoto);
        repositoryPet.save(petEncontrado);

        return ResponseEntity.status(200).build();
    }

    @GetMapping(value = "/foto/{codigo}", produces = "image/jpeg")
    @Operation(description = "EndPoint para ver as fotos dos animais")
    public ResponseEntity<byte[]> getFoto(@PathVariable int codigo) {
        if (!repositoryPet.existsById(codigo)){
            return ResponseEntity.status(404).build();
        }
        Pet petEncontrado = repositoryPet.getById(codigo);
        return ResponseEntity.status(200).body(petEncontrado.getFotoPerfil());
    }

    @PostMapping("/post-pet")
    @Operation(description = "Endpoint para cadastro de um novo pet em uma instituição especifica")
    public ResponseEntity<Object> postPet(
            @RequestBody @Valid Pet novoPet) {
        if (Objects.nonNull(novoPet)) {
            repositoryPet.save(novoPet);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/atualizar-pet/{id}")
    @Operation(description = "Endpoint para atualizar informações de um pet especifico")
    public ResponseEntity putPet(@RequestBody Pet petAtualizado, @PathVariable int id) {
        if (repositoryPet.existsById(id)) {
            petAtualizado.setId(id);
            repositoryPet.save(petAtualizado);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/get-pets")
    @Operation(description = "Endpoint que retorna uma lista com todos os pets")
    public ResponseEntity getPets() {
        List<Pet> lista = repositoryPet.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/filtro-id-pet/{id}")
    @Operation(description = "Endpoint que retorna um pet especifico pelo ID")
    ResponseEntity getByid(@PathVariable int id) {
        if (repositoryPet.existsById(id)) {
            List<Pet> lista = repositoryPet.findById(id);
            return ResponseEntity.status(200).body(lista);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/filtro-id-pets/instituicao/{id}")
    @Operation(description = "Endpoint que retorna uma lista de pets de uma instituição especifica")
    ResponseEntity getByInstiuicaoId(@PathVariable int id) {
        if (repositoryPet.existsById(id)) {
            List<Pet> lista = repositoryPet.findByFkInstituicao(id);
            return ResponseEntity.status(200).body(lista);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/delete-id-pet/{id}")
    @Operation(description = "Endpoint que deleta um pet especifico pelo ID")
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

    @PatchMapping(value = "/premio/foto/{id}", consumes = "image/jpeg")
    @Operation(description = "Inserir imagem no premio")
    public ResponseEntity patchFotoPremio(@PathVariable int id, @RequestBody byte[] novaFoto) {

        Premio premioEncontrado = repositoryPremio.getById(id);
        premioEncontrado.setImg(novaFoto);
        repositoryPremio.save(premioEncontrado);

        return ResponseEntity.status(200).build();
    }

    @GetMapping(value = "/premio/foto/{codigo}", produces = "image/jpeg")
    @Operation(description = "Endpoint pegar foto do premio por id do premio")
    public ResponseEntity<byte[]> getFotoPremio(@PathVariable int codigo) {
        if (!repositoryPremio.existsById(codigo)){
            return ResponseEntity.status(404).build();
        }
        Premio premioEncontrado = repositoryPremio.getById(codigo);
        return ResponseEntity.status(200).body(premioEncontrado.getImg());
    }

    @PostMapping("/post-premio")
    @Operation(description = "Endpoint para cadastrar um novo premio")
    public ResponseEntity postPremio(
            @RequestBody @Valid Premio novoPremio) {

        if (Objects.nonNull(novoPremio)) {
            repositoryPremio.save(novoPremio);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/atualizar-premio/{id}")
    @Operation(description = "Endpoint que atualiza um premio especifico filtrado pelo ID")
    public ResponseEntity putPremio(@RequestBody Premio premioAtualizado, @PathVariable int id) {
        if (repositoryPremio.existsById(id)) {
            premioAtualizado.setId(id);
            repositoryPremio.save(premioAtualizado);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/get-premios")
    @Operation(description = "Endpoint que retorna uma lista de todos os premios")
    public ResponseEntity getPremios() {
        List<Premio> lista = repositoryPremio.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/filtro-id-premio/{id}")
    @Operation(description = "Endpoint que retorna um premio filtrado pelo ID")
    ResponseEntity getByidPremios(@PathVariable int id) {
        if (repositoryPremio.existsById(id)) {
            List<Premio> lista = repositoryPremio.findById(id);
            return ResponseEntity.status(200).body(lista);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/delete-id-premio/{id}")
    @Operation(description = "Endpoint que deleta um premio especifico filtrado pelo ID")
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
    @Operation(description = "Endpoint para cadastrar uma caracteristica")
    public ResponseEntity postCaracteristica(
            @RequestBody @Valid Caracteristica novaCaracteristica) {
        if (Objects.nonNull(novaCaracteristica)) {
            repositoryCaracteristica.save(novaCaracteristica);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/atualizar-caracteristica/{id}")
    @Operation(description = "Endpoint para atualizar uma caracteristica especifica filtrada pelo ID ")
    public ResponseEntity putCaracteristica(@RequestBody Caracteristica caracteristicaAtualizada, @PathVariable int id) {
        if (repositoryCaracteristica.existsById(id)) {
            caracteristicaAtualizada.setId(id);
            repositoryCaracteristica.save(caracteristicaAtualizada);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/get-caracteristicas")
    @Operation(description = "Endpoint que retorna uma lista com todas as caracteristicas")
    public ResponseEntity getCaracteristica() {
        List<Caracteristica> lista = repositoryCaracteristica.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/filtro-id-caracteristica/{id}")
    @Operation(description = "Endpoint que retorna uma caracteristica especifica filtrada pelo ID")
    ResponseEntity getByidCaracteristca(@PathVariable int id) {
        if (repositoryCaracteristica.existsById(id)) {
            List<Caracteristica> lista = repositoryCaracteristica.findById(id);
            return ResponseEntity.status(200).body(lista);
        }
        return ResponseEntity.status(404).build();
    }


    @DeleteMapping("/delete-id-caracteristica/{id}")
    @Operation(description = "Endpoint que deleta uma caracteristica especifica filtrada pelo ID")
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
    // !! REVISAR ESSA DESCRIÇÃO
    @PostMapping("/post-has-caracteristica")
    @Operation(description = "Endpoint para cadastrar um relacionamento de uma caracteristica")
    public ResponseEntity postHasCaracteristica(
            @RequestBody PetHasCaracteristica novaHasCaracteristica) {
        if (Objects.nonNull(novaHasCaracteristica)) {
            repositoryHasCaracteristica.save(novaHasCaracteristica);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/get-has-caracteristicas")
    @Operation(description = "Endpoint para retornar todos os registros de relacionamentos")
    public ResponseEntity getHasCaracteristica() {
        List<PetHasCaracteristica> lista = repositoryHasCaracteristica.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(lista);
        }

        return ResponseEntity.status(200).body(lista);
    }

    @PutMapping("/atualizar-has-caracteristica/{indice}")
    @Operation(description = "Endpoint para atualização do relacionamente de uma caracteristica")
    public ResponseEntity putHasCaracteristica(@RequestBody PetHasCaracteristica hasCaracteristicaAtualizada, @PathVariable int indice) {
        if (repositoryHasCaracteristica.existsById(indice)) {
            hasCaracteristicaAtualizada.setFkPet(indice);
            repositoryHasCaracteristica.save(hasCaracteristicaAtualizada);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

}