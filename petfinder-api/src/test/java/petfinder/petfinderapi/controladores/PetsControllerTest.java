package petfinder.petfinderapi.controladores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import petfinder.petfinderapi.entidades.Pet;

@SpringBootTest
public class PetsControllerTest {

    @Autowired
    private PetsController controller;

    public static Pet fastPet() {
        return null;
    }

    @Test
    @DisplayName("Deve retornar 201 e pet cadstrado no corpo, se request body estiver válida")
    void postPetValido() {

    }

    @Test
    @DisplayName("Deve lançar TransactionSystemException se corpo da requisição estiver inválido")
    void postPetInvalido() {

    }

    @Test
    @DisplayName("Deve retornar 200 e entidade deletada se o ID passado for válido")
    void deletePet() {

    }

    @Test
    @DisplayName("Deve retornar 404 e corpo vazio se não existir entidade equivalente ao id passado")
    void deletePetNaoEncontrado() {

    }

    @Test
    @DisplayName("Deve retornar 200 e entidade no corpo da resposta, se existir entidade equivalente ao id passado")
    void getPetIdValido() {

    }

    @Test
    @DisplayName("Deve retornar 404 e corpo vazio, se não existir entidade equivalente ao id passado")
    void getPetIdInvalido() {

    }

    // Caracteristica

    @Test
    @DisplayName("Deve retornar 201 e entidade criada, se corpo da requisição for válido")
    void postPetHasCaracteristicaValida() {

    }

    @Test
    @DisplayName("Deve lançar TransactionSystemException, se corpo da requisição for inválido")
    void postPetHasCaracteristicaInvalida() {

    }

    @Test
    @DisplayName("Deve retornar 200 e lista de caracteristicas de um pet no corpo, se id passado for válido")
    void getPetHasCategoriaByPet() {

    }

    @Test
    @DisplayName("Deve retornar 404 se id passado não for encontrado")
    void getPetHasCategoriaNaoencontrado() {

    }
}
