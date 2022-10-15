package petfinder.petfinderapi.entidades;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Premio {

    // attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String img;
    private Date dataEnvio;
    @NotNull
    @ManyToOne
    private Pet pet;

    public Premio() {}
    public Premio(Pet pet, String imgPath) {
        setPet(pet);
        setImg(imgPath);
        setDataEnvio(new Date());
    }

    // attributes
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
    public Pet getPet() {
        return pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }
    public Date getDataEnvio() {
        return dataEnvio;
    }
    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
