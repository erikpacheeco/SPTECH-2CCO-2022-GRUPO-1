package petfinder.petfinderapi.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
// import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Pet {

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

    @Column(length = 50_000_000)
    @JsonIgnore
    private byte[] fotoPerfil;

    private Boolean adotado = false;

    @NotNull
    @ManyToOne
    private Instituicao fkInstituicao;

    public Pet() {
    }

    public Pet(String nome, String dataNasc, String especie, String raca, String porte, String sexo, String descricao, Boolean adotado, Instituicao fkInstituicao) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.especie = especie;
        this.raca = raca;
        this.porte = porte;
        this.sexo = sexo;
        this.descricao = descricao;
        this.adotado = adotado;
        this.fkInstituicao = fkInstituicao;
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

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }
    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Boolean getAdotado() {
        return adotado;
    }
    public void setAdotado(Boolean adotado) {
        this.adotado = adotado;
    }

    public Instituicao getFkInstituicao() {
        return fkInstituicao;
    }
    public void setFkInstituicao(Instituicao fkInstituicao) {
        this.fkInstituicao = fkInstituicao;
    }
}
