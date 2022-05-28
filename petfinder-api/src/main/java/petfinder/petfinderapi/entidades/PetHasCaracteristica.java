package petfinder.petfinderapi.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class PetHasCaracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Caracteristica fkCaracteristica;

    @ManyToOne
    private Pet fkPet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Caracteristica getFkCaracteristica() {
        return fkCaracteristica;
    }

    public void setFkCaracteristica(Caracteristica fkCaracteristica) {
        this.fkCaracteristica = fkCaracteristica;
    }

    public Pet getFkPet() {
        return fkPet;
    }

    public void setFkPet(Pet fkPet) {
        this.fkPet = fkPet;
    }
}
