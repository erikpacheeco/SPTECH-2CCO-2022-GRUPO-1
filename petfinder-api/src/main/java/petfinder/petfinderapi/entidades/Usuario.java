package petfinder.petfinderapi.entidades;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario {

    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String nome;
    @Email
    @NotNull
    private String email;
    @NotNull
    @NotBlank
    private String senha;
    private String nivelAcesso;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    @ManyToOne(cascade = CascadeType.ALL)
    private Instituicao instituicao;

    // atributo temporario até definirmos o processo de logoff
    private boolean logado;

    public Usuario() {}

    public Usuario(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.email;
        this.senha = usuario.getSenha();
        this.nivelAcesso = usuario.getNivelAcesso();
        this.endereco = usuario.getEndereco();
        this.instituicao = usuario.getInstituicao();
        this.logado = usuario.isLogado();
    }

    // getters e setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getNivelAcesso() {
        return nivelAcesso;
    }
    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso.toLowerCase();
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public Instituicao getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
    public boolean isLogado() {
        return logado;
    }
    public void setLogado(boolean logado) {
        this.logado = logado;
    }
}