package sptech.petfinderapimsg.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Demanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank
    private String categoria;
    @NotNull
    private Date dataAbertura;
    private Date dataFechamento;
    @NotNull
    @NotBlank
    private String status;
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @NotNull
    @ManyToOne
    private Instituicao instituicao;
    @ManyToOne
    private Pet pet;
    @ManyToOne
    private Usuario colaborador;

    public Demanda(String categoria, Date dataAbertura,
                   Date dataFechamento, String status,
                   Usuario usuario, Instituicao instituicao,
                   Pet pet, Usuario colaborador) {
        this.categoria = categoria;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.status = status;
        this.usuario = usuario;
        this.instituicao = instituicao;
        this.pet = pet;
        this.colaborador = colaborador;
    }

    public Demanda(String categoria, Usuario usuario, Instituicao instituicao, Pet pet) {
        this(categoria.toUpperCase(), null,
                null, "ABERTO",
                usuario, instituicao,
                pet, null);
    }
    public Demanda() {
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
        this.categoria = categoria.toUpperCase();
    }
    public Date getDataAbertura() {
        return dataAbertura;
    }
    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
    public Date getDataFechamento() {
        return dataFechamento;
    }
    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status.toUpperCase();
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Instituicao getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
    public Pet getPet() {
        return pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }
    public Usuario getColaborador() {
        return colaborador;
    }
    public void setColaborador(Usuario colaborador) {
        this.colaborador = colaborador;
    }
}
