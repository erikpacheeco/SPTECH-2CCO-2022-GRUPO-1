package petfinder.petfinderapi.utilitarios.HashTable;

import java.util.ArrayList;
import java.util.List;
import petfinder.petfinderapi.entidades.Pet;

public class PetsInstituicao {
    
    // attributes
    private int id;
    private List<Pet> pets;

    // constructor
    public PetsInstituicao(int id) {
        pets = new ArrayList<Pet>();
        this.id = id;
    }

    // methods
    public void add(Pet pet) {
        this.pets.add(pet);
    }

    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<Pet> getPets() {
        return pets;
    }
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
