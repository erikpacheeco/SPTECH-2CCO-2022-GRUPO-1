package petfinder.pet.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mimo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String img;
    private Pet fkPet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Pet getFkPet() {
        return fkPet;
    }

    public void setFkPet(Pet fkPet) {
        this.fkPet = fkPet;
    }
}
