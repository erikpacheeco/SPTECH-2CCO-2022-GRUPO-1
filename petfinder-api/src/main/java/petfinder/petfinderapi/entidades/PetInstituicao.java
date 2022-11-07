package petfinder.petfinderapi.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class PetInstituicao {
    
    // attributes
    @Id
    private int id;
    private String nomePet;
    private String instNome;

    // constructor
    public PetInstituicao() {}

    // getters and setters
    public String getNomePet() {
        return nomePet;
    }
    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }
    public String getInstNome() {
        return instNome;
    }
    public void setInstNome(String instNome) {
        this.instNome = instNome;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}