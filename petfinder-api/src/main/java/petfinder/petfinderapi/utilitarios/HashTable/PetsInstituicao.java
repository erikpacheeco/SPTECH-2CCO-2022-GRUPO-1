package petfinder.petfinderapi.utilitarios.HashTable;

import java.util.ArrayList;
import java.util.List;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.resposta.PetPerfil;

public class PetsInstituicao {
    
    // attributes
    private int id;
    private List<PetPerfil> pets;

    // constructor
    public PetsInstituicao(int id) {
        pets = new ArrayList<PetPerfil>();
        this.id = id;
    }

    // methods
    public void add(PetPerfil pet) {
        this.pets.add(pet);
    }

    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<PetPerfil> getPets() {
        return pets;
    }
    public void setPets(List<PetPerfil> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "PetsInstituicao [id=" + id + ", pets=" + pets + "]";
    }

    public Boolean isEmpty() {
        return this.getPets().isEmpty();
    }

    public Boolean isNotEmpty() {
        return !isEmpty();
    }
}
