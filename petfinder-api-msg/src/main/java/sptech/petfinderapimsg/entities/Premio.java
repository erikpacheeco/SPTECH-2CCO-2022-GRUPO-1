package sptech.petfinderapimsg.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50_000_000)
    @JsonIgnore
    private byte[] img;
    @NotNull
    @ManyToOne
    private Pet fkPet;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public byte[] getImg() {
        return img;
    }
    public void setImg(byte[] img) {
        this.img = img;
    }
    public Pet getFkPet() {
        return fkPet;
    }
    public void setFkPet(Pet fkPet) {
        this.fkPet = fkPet;
    }
}
