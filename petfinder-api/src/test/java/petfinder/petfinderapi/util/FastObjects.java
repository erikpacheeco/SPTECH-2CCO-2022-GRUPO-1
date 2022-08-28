package petfinder.petfinderapi.util;

import java.util.Date;
import java.util.List;
import petfinder.petfinderapi.entidades.Caracteristica;
import petfinder.petfinderapi.entidades.Endereco;
import petfinder.petfinderapi.entidades.Instituicao;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.entidades.PetHasCaracteristica;
import petfinder.petfinderapi.resposta.PetPerfil;

public class FastObjects {

    // Endereco
    public static Endereco fastEndereco(int value) {
        Endereco entity = new Endereco();
        entity.setId(value);
        entity.setBairro("bairro " + value);
        entity.setCep("cep " + value);
        entity.setCidade("cidade " + value);
        entity.setComplemento("complemento " + value);
        entity.setLatitude("latitude " + value);
        entity.setLongitude("longitude" + value);
        entity.setNum("num" + value);
        entity.setRua("rua " + value);
        entity.setUf("uf " + value);
        return entity;
    }
    public static Endereco fastEndereco() {
        return fastEndereco(0);
    }

    // Instituicao
    public static Instituicao fastInstituicao(int value) {
        Instituicao entity = new Instituicao();
        entity.setId(value);
        entity.setNome("instituicao " + value);
        entity.setTelefone("telefone " + value);
        entity.setTermoAdocao("termoAdocao " + value);
        entity.setEndereco(fastEndereco(value));
        return entity;
    }
    public static Instituicao fastInstituicao() {
        return fastInstituicao(0);
    }

    // Pet Entity
    public static Pet fastPet(int value) {
        Pet pet = new Pet();
        pet.setAdotado(false);
        pet.setCaminhoImagem("/img/pets/" + value + ".png");
        pet.setDataNasc(new Date());
        pet.setDescricao("descricao " + value);
        pet.setDoente(false);
        pet.setEspecie("especie " + value);
        pet.setId(value);
        pet.setNome("nome " + value);
        pet.setInstituicao(fastInstituicao(value));
        pet.setPetHasCaracteristica(List.of(
            fastPetHasCaracteristica(1), 
            fastPetHasCaracteristica(2), 
            fastPetHasCaracteristica(3))
        );
        return pet;
    }
    public static Pet fastPet() {
        return fastPet(0);
    }    
    
    // PetHasCaracteristica
    public static PetHasCaracteristica fastPetHasCaracteristica(int value) {

        // pet
        Pet pet = new Pet();
        pet.setId(value);

        // caracteristica
        Caracteristica caracteristica = new Caracteristica();
        caracteristica.setId(value);
        caracteristica.setCaracteristica("caracteristica " + value);

        // relacionamento
        PetHasCaracteristica entity = new PetHasCaracteristica();
        entity.setId(value);
        entity.setPet(pet);
        entity.setCaracteristica(caracteristica);
        return entity;
    }

    // Caracteristica
    public static Caracteristica fastCaracteristica(int value) {
        Caracteristica entity = new Caracteristica();
        entity.setId(value);
        entity.setCaracteristica("caracteristica " + value);
        return entity;
    }
    public static Caracteristica fastCaracteristica() {
        return fastCaracteristica(0);
    }

    // PetPerfil Response
    public static PetPerfil fastPetPerfil(int value) {
        return new PetPerfil(fastPet(value));
    }
    public static PetPerfil fastPetPerfil() {
        return fastPetPerfil(0);
    }
    
}
