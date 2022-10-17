package petfinder.petfinderapi.resposta;
import petfinder.petfinderapi.entidades.Premio;

import java.util.Date;

public class PremioDtoData {
    private int id;
    private String img;
    private Date data;

    public PremioDtoData() {
    }

    public PremioDtoData(Premio entity) {
        this.id = entity.getId();
        this.img = entity.getImg();
        this.data = entity.getDataEnvio();
    }

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
