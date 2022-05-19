package petfinder.petfinderapi.requisicao;

public class InteresseUsuario {

    private Integer fkCaracteristica;
    private Integer fkUsuario;

    public InteresseUsuario(Integer fkCaracteristica, Integer fkUsuario) {
        this.fkCaracteristica = fkCaracteristica;
        this.fkUsuario = fkUsuario;
    }

    public Integer getFkCaracteristica() {
        return fkCaracteristica;
    }
    public void setFkCaracteristica(Integer fkCaracteristica) {
        this.fkCaracteristica = fkCaracteristica;
    }
    public Integer getFkUsuario() {
        return fkUsuario;
    }
    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
