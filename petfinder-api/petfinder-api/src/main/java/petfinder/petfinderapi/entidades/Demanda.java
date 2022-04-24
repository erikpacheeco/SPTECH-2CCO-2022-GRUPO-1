package petfinder.petfinderapi.entidades;

import petfinder.petfinderapi.enums.EnumCategoria;
import petfinder.petfinderapi.enums.EnumStatusDemanda;
import petfinder.petfinderapi.requisicao.CriacaoDemanda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Entity
public class Demanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
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
    private int fkUsuario;
    private Integer fkInstituicao;
    private Integer fkPet;

    public Demanda( String categoria, String dataAbertura, String dataFechamento,
                   String status, int fkUsuario, Integer fkInstituicao, Integer fkPet) {
        this.categoria = categoria.toLowerCase(Locale.ROOT);
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.status = status.toLowerCase(Locale.ROOT);
        this.fkUsuario = fkUsuario;
        this.fkInstituicao = fkInstituicao;
        this.fkPet = fkPet;
    }

    public Demanda(CriacaoDemanda demanda) {

        this(demanda.getCategoria(), null, null,
                "ABERTO", demanda.getFkUsuario(), demanda.getFkIntituicao(), demanda.getFkPet() );
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = dateFormat.format(date);
        this.dataAbertura = strDate;
    }

    public Demanda() {
    }

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
        this.status = status;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Integer getFkInstituicao() {
        return fkInstituicao;
    }

    public void setFkInstituicao(Integer fkInstituicao) {
        this.fkInstituicao = fkInstituicao;
    }

    public Integer getFkPet() {
        return fkPet;
    }

    public void setFkPet(Integer fkPet) {
        this.fkPet = fkPet;
    }
}
