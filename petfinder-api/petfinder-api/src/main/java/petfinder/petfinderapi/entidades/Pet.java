package petfinder.petfinderapi.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
// import javax.validation.constraints.NotNull;
import java.util.Objects;

    @Entity
public class Pet implements Validavel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String dataNasc;

    @NotNull
    @NotBlank
    private String especie;

    @NotNull
    @NotBlank
    private String raca;

    @NotNull
    @NotBlank
    private String porte;

    @NotNull
    @NotBlank
    private String sexo;

    @NotNull
    @NotBlank
    private String descricao;

    private Boolean emAdocao = false;

    @NotNull
    private int fkInstituicao;

    //    MÉTODOS

    @Override
    public Boolean validar() {
        Boolean boolNome = Objects.isNull(this.nome) || this.nome.isEmpty() || this.nome.isBlank();
        Boolean boolNasc = Objects.isNull(this.dataNasc) || this.dataNasc.isEmpty() || this.dataNasc.isBlank();
        Boolean boolTipo = Objects.isNull(this.especie) || this.especie.isEmpty() || this.especie.isBlank();
        Boolean boolRaca = Objects.isNull(this.raca) || this.raca.isEmpty() || this.raca.isBlank();
        Boolean boolPorte = Objects.isNull(this.porte) || this.porte.isEmpty() || this.porte.isBlank();
        Boolean boolSexo = Objects.isNull(this.sexo) || this.sexo.isEmpty() || this.sexo.isBlank();

        if(!(boolNome || boolTipo || boolRaca || boolPorte || boolSexo || boolNasc)){
            return true;
        }
        return false;
    }

    //    GETTERS E SETTERS


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRaca() {
        return raca;
    }
    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }
    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Boolean isEmAdocao() {
        return emAdocao;
    }
    public void setEstadoAdocao(Boolean adotado) {
        this.emAdocao = adotado;
    }

        public int getFkInstituicao() {
            return fkInstituicao;
        }

        public void setFkInstituicao(int fkInstituicao) {
            this.fkInstituicao = fkInstituicao;
        }
    }
