package petfinder.petfinderapi.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import petfinder.petfinderapi.entidades.Caracteristica;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.entidades.Pet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PetsControllerTest {

    @Autowired
    private PetsController controller;

    // methods
    public Pet fastDto() {

        Pet pet = new Pet();

        pet.setNome("Arnaldo");
        pet.setEspecie("cachorro");
        pet.setDescricao("Este é um dog fofinho");
        pet.setDataNasc(new Date("2020-05-01"));
        pet.setPorte("Médio");
        pet.setRaca("Golden");
        pet.setSexo("macho");
        pet.setAdotado(false);
        pet.setFkInstituicao(new Instituicao());
        pet.getFkInstituicao().setId(1);
        
        return pet;
    }

    // tests

    // post
    @Test
    @DisplayName("Deve retornar 201 e pet cadstrado no corpo, se request body estiver válida")
    void postPetValido() {

        Pet dto = fastDto();

        // creating
        ResponseEntity<Pet> res = controller.postPet(dto);

        // asserts
        assertEquals(201, res.getStatusCodeValue());
        assertNotNull(res.getBody());
        assertEquals("Médio", res.getBody().getPorte());

        // deleting
        controller.deletePermanentByIdpet(res.getBody().getId());
    }

    @Test
    @DisplayName("Deve lançar ConstraintViolationException se corpo da requisição estiver inválido")
    void postPetInvalido() {

        assertThrows(ConstraintViolationException.class, () -> {
           Pet pet = fastDto();
           pet.setNome(null);
           controller.postPet(pet); 
        });

    }

    // delete
    @Test
    @DisplayName("Deve retornar status 200 e entidade atualizada no corpo da resposta, atributo 'Adotado' deve ser alterado para true")
    void deletePet() {

        // creating
        int id = controller.postPet(fastDto()).getBody().getId();
        ResponseEntity<Pet> res = controller.deletePet(id);

        // deleting
        controller.deletePermanentByIdpet(id);

        // asserts
        assertEquals(200, res.getStatusCodeValue());
        assertNotNull(res.getBody());
        assertInstanceOf(Pet.class, res.getBody());
        assertTrue(res.getBody().getAdotado());
    }

    @Test
    @DisplayName("Deve retornar 404 e corpo vazio se não existir entidade equivalente ao id passado")
    void deletePetNaoEncontrado() {

        // creating
        int id = controller.postPet(fastDto()).getBody().getId();
        controller.deletePermanentByIdpet(id);

        // requesting
        ResponseEntity<Pet> res = controller.deletePet(id);

        // asserts
        assertEquals(404, res.getStatusCodeValue());
        assertNull(res.getBody());
    }

    @Test
    @DisplayName("Deve retornar 200 e entidade no corpo da resposta, se existir entidade equivalente ao id passado")
    void getPetIdValido() {

        // creating
        int id = controller.postPet(fastDto()).getBody().getId();

        // requesting
        ResponseEntity<Pet> res = controller.getPetById(id);

        // asserts
        assertEquals(200, res.getStatusCodeValue());
        assertNotNull(res.getBody());
        assertInstanceOf(Pet.class, res.getBody());
    }

    @Test
    @DisplayName("Deve retornar 404 e corpo vazio, se não existir entidade equivalente ao id passado")
    void getPetIdInvalido() {

        // creating
        int id = controller.postPet(fastDto()).getBody().getId();
        controller.deletePermanentByIdpet(id);

        // requesting
        ResponseEntity<Pet> res = controller.getPetById(id);

        // asserts
        assertEquals(404, res.getStatusCodeValue());
        assertNull(res.getBody());
    }

    // Caracteristica

    @Test
    @DisplayName("Deve retornar 201 e entidade criada, se corpo da requisição for válido")
    void postPetHasCaracteristicaValida() {

        // created
        List<Caracteristica> caracteristicas = controller.getCaracteristica().getBody();
        int idPet = controller.postPet(fastDto()).getBody().getId();
        caracteristicas = new ArrayList<Caracteristica>(List.of(caracteristicas.get(0), caracteristicas.get(1)));

        // request
        ResponseEntity<List<Caracteristica>> res = controller.postHasCaracteristica(caracteristicas, idPet);
        controller.deletePermanentByIdpet(idPet);

        // asserts
        assertEquals(200, res.getStatusCodeValue());
        assertNotNull(res.getBody());
        assertInstanceOf(List.class, res.getBody());
        assertEquals(caracteristicas.get(0), res.getBody().get(1)); 
    }

    @Test
    @DisplayName("Deve retornar 200 e lista de caracteristicas de um pet no corpo, se id passado for válido")
    void getPetHasCategoriaByPet() {
        // created
        List<Caracteristica> caracteristicas = controller.getCaracteristica().getBody();
        int idPet = controller.postPet(fastDto()).getBody().getId();
        caracteristicas = new ArrayList<Caracteristica>(List.of(caracteristicas.get(0), caracteristicas.get(1)));

        // request
        controller.postHasCaracteristica(caracteristicas, idPet);
        ResponseEntity<List<Caracteristica>> res = controller.getCaracteristicasByPetId(idPet);
        controller.deletePermanentByIdpet(idPet);

        // asserts
        assertEquals(200, res.getStatusCodeValue());
        assertEquals("Cheiroso", res.getBody().get(0).getCaracteristicas());
    }

    @Test
    @DisplayName("Deve retornar 404 se id passado não for encontrado")
    void getPetHasCategoriaNaoencontrado() {

        // creating
        int id = controller.postPet(fastDto()).getBody().getId();
        controller.deletePermanentByIdpet(id);

        // requesting
        ResponseEntity<List<Caracteristica>> res = controller.getCaracteristicasByPetId(id);

        // asserts
        assertEquals(404, res.getStatusCodeValue());
        assertNull(res.getBody());
    }
}
