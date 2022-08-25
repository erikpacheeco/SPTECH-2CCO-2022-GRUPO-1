package petfinder.petfinderapi.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.resposta.PetPerfil;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;
import petfinder.petfinderapi.util.FastObjects;

@SpringBootTest
public class PetsControllerTest {

    @Autowired
    private PetsController controller;
    
    @MockBean
    private PetRepositorio repository;

    // tests

    @Test
    @DisplayName("Deve retornar status 200 e PetPerfil no response body")
    public void testGetPetPerfilOk() {
        
        // mocking
        int petId = 1;
        Integer userId = null;
        Mockito.when(repository.findPetPerfilById(petId)).thenReturn(Optional.of(FastObjects.fastPetPerfil(petId)));

        // requesting
        ResponseEntity<PetPerfil> res = controller.getPetPerfil(petId, userId);

        // asserting
        assertEquals(200, res.getStatusCodeValue());
        assertEquals("nome " + petId, res.getBody().getNome());
        assertEquals("/img/pets/" + petId + ".png", res.getBody().getCaminhoImagem());
        assertEquals("instituicao " + petId, res.getBody().getInstituicao());
        assertEquals(3, res.getBody().getCaracteristicas().size());
        assertEquals("caracteristica 1", res.getBody().getCaracteristicas().get(0));
    }

    @Test
    @DisplayName("Deve lançar a exceção EntityNotFoundException")
    public void testGetPetPerfilNotFound() {

        // mocking
        int petId = 1;
        Integer userId = null;
        Mockito.when(repository.findPetPerfilById(petId)).thenReturn(Optional.empty());

        // asserting
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            controller.getPetPerfil(petId, userId);
        });
        assertEquals("Id " + petId + " not found", exception.getMessage());
    }


    // // post
    // @Test
    // @DisplayName("Deve retornar 201 e pet cadstrado no corpo, se request body estiver válida")
    // void postPetValido() {

    //     Pet dto = fastDto();

    //     // creating
    //     ResponseEntity<Pet> res = controller.postPet(dto);

    //     // asserts
    //     assertEquals(201, res.getStatusCodeValue());
    //     assertNotNull(res.getBody());
    //     assertEquals("Médio", res.getBody().getPorte());

    //     // deleting
    //     controller.deletePermanentByIdpet(res.getBody().getId());
    // }

    // @Test
    // @DisplayName("Deve lançar ConstraintViolationException se corpo da requisição estiver inválido")
    // void postPetInvalido() {

    //     assertThrows(ConstraintViolationException.class, () -> {
    //        Pet pet = fastDto();
    //        pet.setNome(null);
    //        controller.postPet(pet); 
    //     });

    // }

    // // delete
    // @Test
    // @DisplayName("Deve retornar status 200 e entidade atualizada no corpo da resposta, atributo 'Adotado' deve ser alterado para true")
    // void deletePet() {

    //     // creating
    //     int id = controller.postPet(fastDto()).getBody().getId();
    //     ResponseEntity<Pet> res = controller.deletePet(id);

    //     // deleting
    //     controller.deletePermanentByIdpet(id);

    //     // asserts
    //     assertEquals(200, res.getStatusCodeValue());
    //     assertNotNull(res.getBody());
    //     assertInstanceOf(Pet.class, res.getBody());
    //     assertTrue(res.getBody().getAdotado());
    // }

    // @Test
    // @DisplayName("Deve retornar 404 e corpo vazio se não existir entidade equivalente ao id passado")
    // void deletePetNaoEncontrado() {

    //     // creating
    //     int id = controller.postPet(fastDto()).getBody().getId();
    //     controller.deletePermanentByIdpet(id);

    //     // requesting
    //     ResponseEntity<Pet> res = controller.deletePet(id);

    //     // asserts
    //     assertEquals(404, res.getStatusCodeValue());
    //     assertNull(res.getBody());
    // }

    // @Test
    // @DisplayName("Deve retornar 200 e entidade no corpo da resposta, se existir entidade equivalente ao id passado")
    // void getPetIdValido() {

    //     // creating
    //     int id = controller.postPet(fastDto()).getBody().getId();

    //     // requesting
    //     ResponseEntity<Pet> res = controller.getPetById(id);

    //     // asserts
    //     assertEquals(200, res.getStatusCodeValue());
    //     assertNotNull(res.getBody());
    //     assertInstanceOf(Pet.class, res.getBody());
    // }

    // @Test
    // @DisplayName("Deve retornar 404 e corpo vazio, se não existir entidade equivalente ao id passado")
    // void getPetIdInvalido() {

    //     // creating
    //     int id = controller.postPet(fastDto()).getBody().getId();
    //     controller.deletePermanentByIdpet(id);

    //     // requesting
    //     ResponseEntity<Pet> res = controller.getPetById(id);

    //     // asserts
    //     assertEquals(404, res.getStatusCodeValue());
    //     assertNull(res.getBody());
    // }

    // // Caracteristica

    // @Test
    // @DisplayName("Deve retornar 201 e entidade criada, se corpo da requisição for válido")
    // void postPetHasCaracteristicaValida() {

    //     // created
    //     List<Caracteristica> caracteristicas = controller.getCaracteristica().getBody();
    //     int idPet = controller.postPet(fastDto()).getBody().getId();
    //     caracteristicas = new ArrayList<Caracteristica>(List.of(caracteristicas.get(0), caracteristicas.get(1)));

    //     // request
    //     ResponseEntity<List<Caracteristica>> res = controller.postHasCaracteristica(caracteristicas, idPet);
    //     controller.deletePermanentByIdpet(idPet);

    //     // asserts
    //     assertEquals(200, res.getStatusCodeValue());
    //     assertNotNull(res.getBody());
    //     assertInstanceOf(List.class, res.getBody());
    //     assertEquals(caracteristicas.get(0), res.getBody().get(1)); 
    // }

    // @Test
    // @DisplayName("Deve retornar 200 e lista de caracteristicas de um pet no corpo, se id passado for válido")
    // void getPetHasCategoriaByPet() {
    //     // created
    //     List<Caracteristica> caracteristicas = controller.getCaracteristica().getBody();
    //     int idPet = controller.postPet(fastDto()).getBody().getId();
    //     caracteristicas = new ArrayList<Caracteristica>(List.of(caracteristicas.get(0), caracteristicas.get(1)));

    //     // request
    //     controller.postHasCaracteristica(caracteristicas, idPet);
    //     ResponseEntity<List<Caracteristica>> res = controller.getCaracteristicasByPetId(idPet);
    //     controller.deletePermanentByIdpet(idPet);

    //     // asserts
    //     assertEquals(200, res.getStatusCodeValue());
    //     assertEquals("Cheiroso", res.getBody().get(0).getCaracteristicas());
    // }

    // @Test
    // @DisplayName("Deve retornar 404 se id passado não for encontrado")
    // void getPetHasCategoriaNaoencontrado() {

    //     // creating
    //     int id = controller.postPet(fastDto()).getBody().getId();
    //     controller.deletePermanentByIdpet(id);

    //     // requesting
    //     ResponseEntity<List<Caracteristica>> res = controller.getCaracteristicasByPetId(id);

    //     // asserts
    //     assertEquals(404, res.getStatusCodeValue());
    //     assertNull(res.getBody());
    // }
}
