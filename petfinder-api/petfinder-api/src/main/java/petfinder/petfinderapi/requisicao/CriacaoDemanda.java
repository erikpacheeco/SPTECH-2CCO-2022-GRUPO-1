package petfinder.petfinderapi.requisicao;

public class CriacaoDemanda {

    private String categoria;
    private int fkUsuario;
    private int fkIntituicao;
    private Integer fkPet;

    public CriacaoDemanda(String categoria, int fkUsuario, int fkIntituicao, Integer fkPet) {
        this.categoria = categoria;
        this.fkUsuario = fkUsuario;
        this.fkIntituicao = fkIntituicao;
        this.fkPet = fkPet;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public int getFkIntituicao() {
        return fkIntituicao;
    }

    public void setFkIntituicao(int fkIntituicao) {
        this.fkIntituicao = fkIntituicao;
    }

    public Integer getFkPet() {
        return fkPet;
    }

    public void setFkPet(Integer fkPet) {
        this.fkPet = fkPet;
    }

}
