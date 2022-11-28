package petfinder.petfinderapi.resposta;
import petfinder.petfinderapi.entidades.Premio;

public class PremioDto {
    private int id;
    private String img;
    private String pet;

    public PremioDto() {
    }

    public PremioDto(Premio entity) {
        this.id = entity.getId();
        this.img = entity.getImg();
        this.pet = entity.getPet().getNome();
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

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }
}
