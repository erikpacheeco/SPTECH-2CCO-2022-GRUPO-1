package sptech.petfinderapimsg.entities;

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
    @Size(min = 2, max = 2)
    private String uf;

    @NotNull
    @NotEmpty
    @Size(max = 8, min = 8, message = "Informe somente os números")
    private String cep;

    private String latitude;
    private String longitude;

    


//    METODOS

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
}
