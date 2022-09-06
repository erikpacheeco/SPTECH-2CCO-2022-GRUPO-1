package petfinder.petfinderapi.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pet {

    // attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    private Date dataNasc;
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
    // @Column(length = 50_000_000)
    // @JsonIgnore
    // private byte[] fotoPerfil;
    private String caminhoImagem;
    private Boolean doente;
    private Boolean adotado;
    @NotNull
    @ManyToOne
    private Instituicao instituicao;
    @OneToMany(mappedBy = "pet")
    private List<PetHasCaracteristica> petHasCaracteristica;

    // constructors
    public Pet() {}
    public Pet(String nome, Date dataNasc, String especie, String raca, String porte, String sexo, String descricao, Boolean doente, Boolean adotado, Instituicao instituicao) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.especie = especie;
        this.raca = raca;
        this.porte = porte;
        this.sexo = sexo;
        this.descricao = descricao;
        this.doente = doente;
        this.adotado = adotado;
        this.instituicao = instituicao;
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
    public Date getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(Date dataNasc) {
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
    // public byte[] getFotoPerfil() {
    //     return fotoPerfil;
    // }
    // public void setFotoPerfil(byte[] fotoPerfil) {
    //     this.fotoPerfil = fotoPerfil;
    // }
    // public void setFotoPerfil() {
    //     this.fotoPerfil = null;
    // }
    public Boolean getDoente() {
        return doente;
    }
    public void setDoente(Boolean doente) {
        this.doente = doente;
    }
    public Boolean getAdotado() {
        return adotado;
    }
    public void setAdotado(Boolean adotado) {
        this.adotado = adotado;
    }
    public Instituicao getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
    public String getCaminhoImagem() {
        return caminhoImagem;
    }
    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
    public List<PetHasCaracteristica> getPetHasCaracteristica() {
        return petHasCaracteristica;
    }
    public void setPetHasCaracteristica(List<PetHasCaracteristica> petHasCaracteristica) {
        this.petHasCaracteristica = petHasCaracteristica;
    }

    @Override
    public String toString() {
        return "Pet [adotado=" + adotado + ", caminhoImagem=" + caminhoImagem + ", dataNasc=" + dataNasc
                + ", descricao=" + descricao + ", doente=" + doente + ", especie=" + especie + ", id=" + id
                + ", instituicao=" + instituicao + ", nome=" + nome + ", petHasCaracteristica=" + petHasCaracteristica
                + ", porte=" + porte + ", raca=" + raca + ", sexo=" + sexo + "]";
    }
}
