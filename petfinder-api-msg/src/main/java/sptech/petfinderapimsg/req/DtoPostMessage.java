package sptech.petfinderapimsg.req;

import java.util.Date;
import javax.validation.constraints.NotNull;

public class DtoPostMessage {
    
    // attributes
    @NotNull
    private String conteudo;
    @NotNull
    private String tipo;
    @NotNull
    private Integer demandaId;    
    @NotNull
    private Integer remetenteId;   
    private Date dataEnvio; // self handled

    // constructor
    public DtoPostMessage() {
        this.dataEnvio = new Date();
    }

    // getters and setters
    public String getConteudo() {
        return conteudo;
    }
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    public Date getDataEnvio() {
        return dataEnvio;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Integer getDemandaId() {
        return demandaId;
    }
    public void setDemandaId(Integer demandaId) {
        this.demandaId = demandaId;
    }
    public Integer getRemetenteId() {
        return remetenteId;
    }
    public void setRemetenteId(Integer remetenteId) {
        this.remetenteId = remetenteId;
    }
}
