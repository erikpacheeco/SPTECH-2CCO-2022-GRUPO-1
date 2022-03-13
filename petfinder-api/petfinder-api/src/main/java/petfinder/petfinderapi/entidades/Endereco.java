package petfinder.petfinderapi.entidades;

import java.util.Objects;

public class Endereco implements Validavel{
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String rua,
                    String numero,
                    String complemento,
                    String bairro,
                    String cidade,
                    String estado,
                    String cep) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }
//    METODOS

    public Boolean validar(){

        Boolean boolRua = Objects.isNull(this.rua) || this.rua.isEmpty() || this.rua.isBlank();
        Boolean boolNum = Objects.isNull(this.numero) || this.numero.isEmpty() || this.numero.isBlank();
        Boolean boolBairro = Objects.isNull(this.bairro) || this.bairro.isEmpty() || this.bairro.isBlank();
        Boolean boolCidade = Objects.isNull(this.cidade) || this.cidade.isEmpty() || this.cidade.isBlank();
        Boolean boolEstado = Objects.isNull(this.estado) || this.estado.isEmpty() || this.estado.isBlank();
        Boolean boolCep = Objects.isNull(this.cep) || this.cep.isEmpty() || this.cep.isBlank();

        if(!(boolRua || boolNum || boolBairro || boolCidade || boolEstado || boolCep)){
            return true;
        }
        return false;
    }


//    GETTERS E SETTERS

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

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
