package petfinder.petfinderapi.requisicao;

import java.util.List;

public class FilterRequest {
    private List<String> porte;
    private List<String> caracteristicas;
    private List<String> sexo;
    private List<Boolean> isDoente;
    private List<String> especie;

    public FilterRequest() {
    }

    public FilterRequest(List<String> porte, List<String> caracteristicas, List<String> sexo, List<Boolean> isDoente,List<String> especie) {
        this.porte = porte;
        this.caracteristicas = caracteristicas;
        this.sexo = sexo;
        this.isDoente = isDoente;
        this.especie = especie;
    }

    public Boolean hasFilter(){
        return getEspecie() != null && getCaracteristicas() != null && getSexo() != null && getPorte() != null && getIsDoente() != null;
    }

    public List<String> getPorte() {
        return porte;
    }

    public void setPorte(List<String> porte) {
        this.porte = porte;
    }

    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<String> getSexo() {
        return sexo;
    }

    public void setSexo(List<String> sexo) {
        this.sexo = sexo;
    }

    public List<Boolean> getIsDoente() {
        return isDoente;
    }

    public void setIsDoente(List<Boolean> isDoente) {
        this.isDoente = isDoente;
    }

    public List<String> getEspecie() {
        return especie;
    }

    public void setEspecie(List<String> especie) {
        this.especie = especie;
    }

}
