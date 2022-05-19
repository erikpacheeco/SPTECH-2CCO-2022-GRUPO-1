package petfinder.petfinderapi.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Instituicao {

    // ATRIBUTOS    
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

    private String termoAdocao;

    // @OneToOne
    // @JoinColumn(name = "endereco", referencedColumnName = "id")
    // private Endereco endereco;

    @NotNull
    @OneToOne
    private Endereco endereco;

    // GETTERS E SETTERS

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
