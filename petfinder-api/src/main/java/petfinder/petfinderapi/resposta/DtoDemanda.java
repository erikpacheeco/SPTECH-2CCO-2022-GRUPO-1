package petfinder.petfinderapi.resposta;

import java.util.Date;
import java.util.Objects;
import petfinder.petfinderapi.entidades.Demanda;

public class DtoDemanda {
    
    // attributes
    private int id;
    private String categoria;
    private Date dataAbertura;
    private String status;
    private DtoUsuarioSimples usuario;
    private DtoUsuarioSimples colaborador;
    private DtoPetSimples pet;
    
    // constructors
    public DtoDemanda() {}
    public DtoDemanda(Demanda d) {
        this.id = d.getId();
        this.categoria = d.getCategoria();
        this.dataAbertura = d.getDataAbertura();
        this.status = d.getStatus();
        this.usuario = new DtoUsuarioSimples(d.getUsuario());
        this.colaborador = Objects.nonNull(d.getColaborador()) ? new DtoUsuarioSimples(d.getColaborador()) : null;
        this.pet = Objects.nonNull(d.getPet()) ? new DtoPetSimples(d.getPet()) : null;
    }

    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public Date getDataAbertura() {
        return dataAbertura;
    }
    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public DtoUsuarioSimples getUsuario() {
        return usuario;
    }
    public void setUsuario(DtoUsuarioSimples usuario) {
        this.usuario = usuario;
    }
    public DtoUsuarioSimples getColaborador() {
        return colaborador;
    }
    public void setColaborador(DtoUsuarioSimples colaborador) {
        this.colaborador = colaborador;
    }
    public DtoPetSimples getPet() {
        return pet;
    }
    public void setPet(DtoPetSimples pet) {
        this.pet = pet;
    }
}
