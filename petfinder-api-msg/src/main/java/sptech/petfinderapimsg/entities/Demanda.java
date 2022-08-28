package sptech.petfinderapimsg.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private String dataAbertura;
    private String dataFechamento;
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

    public Demanda(String categoria, String dataAbertura,
                   String dataFechamento, String status,
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

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = dateFormat.format(date);
        this.dataAbertura = strDate;
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
    public String getDataAbertura() {
        return dataAbertura;
    }
    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
    public String getDataFechamento() {
        return dataFechamento;
    }
    public void setDataFechamento(String dataFechamento) {
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
