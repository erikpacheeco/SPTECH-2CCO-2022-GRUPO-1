package petfinder.petfinderapi.requisicao;

import petfinder.petfinderapi.entidades.Convertible;
import petfinder.petfinderapi.entidades.Instituicao;

public class DtoInstituicaoRequest implements Convertible<Instituicao>{
    
    // attributes
    private String nome;
    private String telefone;
    private DtoEnderecoRequest endereco;

    // constructor
    public DtoInstituicaoRequest() {}

    // methods
    public Instituicao convert() {
        Instituicao entity = new Instituicao();
        entity.setNome(this.getNome());
        entity.setTelefone(this.getTelefone());
        entity.setEndereco(this.endereco.convert());
        return entity;
    };

    // getters and setters
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
    public DtoEnderecoRequest getEndereco() {
        return endereco;
    }
    public void setEndereco(DtoEnderecoRequest endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "DtoInstituicaoRequest [endereco=" + endereco + ", nome=" + nome + ", telefone=" + telefone + "]";
    }
}
