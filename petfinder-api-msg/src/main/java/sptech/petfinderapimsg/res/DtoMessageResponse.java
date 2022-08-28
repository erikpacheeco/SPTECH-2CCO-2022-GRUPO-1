package sptech.petfinderapimsg.res;

import java.util.Date;
import sptech.petfinderapimsg.entities.Mensagem;

public class DtoMessageResponse {
    
    // attributes
    private Integer id;
    private String conteudo;
    private Date dataEnvio;
    private String tipo;
    private DtoUsuarioResponse remetente;

    // constructor
    public DtoMessageResponse() {}
    public DtoMessageResponse(Mensagem m) {
        this.id = m.getId();
        this.conteudo = m.getConteudo();
        this.dataEnvio = m.getDataEnvio();
        this.tipo = m.getTipo();
        this.remetente = new DtoUsuarioResponse(m.getUsuario());
    }

    // getters and setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getConteudo() {
        return conteudo;
    }
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    public Date getDataEnvio() {
        return dataEnvio;
    }
    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public DtoUsuarioResponse getRemetente() {
        return remetente;
    }
    public void setRemetente(DtoUsuarioResponse remetente) {
        this.remetente = remetente;
    }
}
