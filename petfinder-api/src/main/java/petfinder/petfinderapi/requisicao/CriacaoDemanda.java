package petfinder.petfinderapi.requisicao;

import javax.validation.constraints.NotNull;

public class CriacaoDemanda {

    // atributos
    private String categoria;
    @NotNull
    private Integer fkUsuario;
    @NotNull
    private Integer fkIntituicao;
    private Integer fkPet;

    // construtor
    public CriacaoDemanda(String categoria, Integer fkUsuario, Integer fkIntituicao, Integer fkPet) {
        this.categoria = categoria;
        this.fkUsuario = fkUsuario;
        this.fkIntituicao = fkIntituicao;
        this.fkPet = fkPet;
    }

    // métodos
    @Override
    public String toString() {
        return "CriacaoDemanda [categoria=" + categoria + ", fkIntituicao=" + fkIntituicao + ", fkPet=" + fkPet
                + ", fkUsuario=" + fkUsuario + "]";
    }

    // getters e setters
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
