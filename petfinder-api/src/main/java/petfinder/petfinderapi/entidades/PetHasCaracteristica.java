package petfinder.petfinderapi.entidades;

import javax.persistence.*;

@Entity
public class PetHasCaracteristica {

    // attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Caracteristica caracteristica;
    @ManyToOne
    private Pet pet;

    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Caracteristica getCaracteristica() {
        return caracteristica;
    }
    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }
    public Pet getPet() {
        return pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }

}
