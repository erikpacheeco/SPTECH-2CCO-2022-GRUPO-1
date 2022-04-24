package petfinder.petfinderapi.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PetHasCaracteristica {

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
