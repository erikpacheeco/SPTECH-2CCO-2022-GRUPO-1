package petfinder.petfinderapi.requisicao;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PetRequest {
    
    // attributes
    @NotNull
    private Integer instituicaoId;
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    private Boolean doente;
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
    private String caminhoImagem;
    private List<Integer> caracteristicas;

    // getters and setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Date getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
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
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getCaminhoImagem() {
        return caminhoImagem;
    }
    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
    public List<Integer> getCaracteristicas() {
        return caracteristicas;
    }
    public void setCaracteristicas(List<Integer> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    public Integer getInstituicaoId() {
        return instituicaoId;
    }
    public void setInstituicaoId(Integer instituicaoId) {
        this.instituicaoId = instituicaoId;
    }
    public Boolean getDoente() {
        return doente;
    }
    public void setDoente(Boolean doente) {
        this.doente = doente;
    }
}
