package petfinder.petfinderapi.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DistanciaResposta {

    @JsonProperty("origin")
    private String cepUsuario;

    @JsonProperty("destination")
    private String cepInstituicao;

    @JsonProperty("distance")
    private Integer distancia;

    public DistanciaResposta(String cepUsuario, String cepInstituicao, Integer distancia) {
        this.cepUsuario = cepUsuario;
        this.cepInstituicao = cepInstituicao;
        this.distancia = distancia;
    }

    public String getCepUsuario() {
        return cepUsuario;
    }

    public void setCepUsuario(String cepUsuario) {
        this.cepUsuario = cepUsuario;
    }

    public String getCepInstituicao() {
        return cepInstituicao;
    }

    public void setCepInstituicao(String cepInstituicao) {
        this.cepInstituicao = cepInstituicao;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }
}
