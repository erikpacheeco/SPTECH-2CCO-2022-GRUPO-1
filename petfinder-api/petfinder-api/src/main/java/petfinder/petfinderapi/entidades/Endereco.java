package petfinder.petfinderapi.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Endereco {

    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotEmpty
    private String rua;

    @NotNull
    @NotEmpty
    private String num;

    private String complemento;

    @NotNull
    @NotEmpty
    private String bairro;

    @NotNull
    @NotEmpty
    private String cidade;

    @NotNull
    @NotEmpty
    private String uf;

    @NotNull
    @NotEmpty
    @Size(max = 8, min = 8, message = "Informe somente os números")
    private String cep;

    private String latitude;
    private String longitude;

//    METODOS

    // @Deprecated
    // public Boolean validar(){

    //     Boolean boolRua = Objects.isNull(this.rua) || this.rua.isEmpty() || this.rua.isBlank();
    //     Boolean boolNum = Objects.isNull(this.num) || this.num.isEmpty() || this.num.isBlank();
    //     Boolean boolBairro = Objects.isNull(this.bairro) || this.bairro.isEmpty() || this.bairro.isBlank();
    //     Boolean boolCidade = Objects.isNull(this.cidade) || this.cidade.isEmpty() || this.cidade.isBlank();
    //     Boolean boolUf = Objects.isNull(this.uf) || this.uf.isEmpty() || this.uf.isBlank();
    //     Boolean boolCep = Objects.isNull(this.cep) || this.cep.isEmpty() || this.cep.isBlank();

    //     if(!(boolRua || boolNum || boolBairro || boolCidade || boolUf || boolCep)){
    //         return true;
    //     }
    //     return false;
    // }


//    GETTERS E SETTERS

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
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

    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "rua='" + rua + '\'' +
                ", num='" + num + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
