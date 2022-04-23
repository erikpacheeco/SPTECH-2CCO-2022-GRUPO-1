package petfinder.usuario.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})", message = "Informe um telefone válido com ou sem DDD")
    private String telefone;

    @NotNull
    @NotEmpty
    private String chavePix;

    private String termoAdocao;

    @OneToOne
    @JoinColumn(name = "endereco", referencedColumnName = "id")
    private Endereco endereco;


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

    public String getChavePix() {
        return chavePix;
    }
    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
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

    @Override
    public String toString() {
        return "Instituicao [chavePix=" + chavePix + ", endereco=" + endereco + ", id=" + id + ", nome=" + nome
                + ", telefone=" + telefone + ", termoAdocao=" + termoAdocao + "]";
    }

}
