package petfinder.petfinderapi.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class DemandaHasPet {

    // attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @ManyToOne
    private Demanda demanda;
    @NotNull
    @ManyToOne
    private  Pet pet;
    private Boolean ativo;

    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Demanda getDemanda() {
        return demanda;
    }
    public void setDemanda(Demanda demanda) {
        this.demanda = demanda;
    }
    public Pet getPet() {
        return pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }
    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
