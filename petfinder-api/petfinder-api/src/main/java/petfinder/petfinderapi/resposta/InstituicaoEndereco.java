package petfinder.petfinderapi.resposta;

import petfinder.petfinderapi.entidades.Endereco;
import petfinder.petfinderapi.entidades.Instituicao;

public class InstituicaoEndereco {
    
    // attributes
    private int id;
    private String nome;
    private String telefone;
    private String termoAdocao;
    private Endereco endereco;

    // construtor
    public InstituicaoEndereco(int id, String nome, String telefone, String chavePix, String termoAdocao,
            Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.termoAdocao = termoAdocao;
        this.endereco = endereco;
    }
    public InstituicaoEndereco(Instituicao instituicao, Endereco endereco) {
        this.id = instituicao.getId();
        this.nome = instituicao.getNome();
        this.telefone = instituicao.getTelefone();
        this.termoAdocao = instituicao.getTermoAdocao();
        this.endereco = endereco;
    }

    // getters e setters
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
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getTermoAdocao() {
        return termoAdocao;
    }
    public void setTermoAdocao(String termoAdocao) {
        this.termoAdocao = termoAdocao;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
