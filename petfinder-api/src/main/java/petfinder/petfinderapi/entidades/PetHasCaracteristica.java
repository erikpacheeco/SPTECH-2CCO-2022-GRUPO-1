package petfinder.petfinderapi.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class PetHasCaracteristica {

    @NotNull
    private int fkCaracteristica;

    @Id
    private int fkPet;

    public int getFkCaracteristica() {
        return fkCaracteristica;
    }

    public void setFkCaracteristica(int fkCaracteristica) {
        this.fkCaracteristica = fkCaracteristica;
    }

    public int getFkPet() {
        return fkPet;
    }

    public void setFkPet(int fkPet) {
        this.fkPet = fkPet;
    }
}
