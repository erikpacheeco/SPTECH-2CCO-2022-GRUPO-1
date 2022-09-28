package petfinder.petfinderapi.requisicao;

import petfinder.petfinderapi.entidades.Convertible;
import petfinder.petfinderapi.entidades.Endereco;

public class DtoEnderecoRequest implements Convertible<Endereco>{
    
    // attributes
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    // constructor
    public DtoEnderecoRequest() {}

    // methods
    public Endereco convert() {
        Endereco endereco = new Endereco();
        endereco.setBairro(this.getBairro());
        endereco.setCep(this.getCep());
        endereco.setCidade(this.getCidade());
        endereco.setComplemento(this.getComplemento());
        endereco.setNum(this.getNumero());
        endereco.setRua(this.getRua());
        endereco.setUf(this.getUf());
        return endereco;
    }

    // getters and setters
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "DtoEnderecoRequest [bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade + ", complemento="
                + complemento + ", numero=" + numero + ", rua=" + rua + ", uf=" + uf + "]";
    }
}
