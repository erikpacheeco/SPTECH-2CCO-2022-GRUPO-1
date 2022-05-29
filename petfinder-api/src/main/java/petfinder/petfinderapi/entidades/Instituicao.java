package petfinder.petfinderapi.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 8, max= 13)
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
