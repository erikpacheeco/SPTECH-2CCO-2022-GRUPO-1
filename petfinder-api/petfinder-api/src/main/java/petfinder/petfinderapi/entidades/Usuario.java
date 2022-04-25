package petfinder.petfinderapi.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario {

    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nome;

    @Email
    @NotNull
    private String email;

    @NotNull
    @NotBlank
    private String senha;

    private String nivelAcesso;
    private Integer fkEndereco;
    private Integer fkInstituicao;

    // atributo temporario até definirmos o processo de logoff
    private boolean logado;

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
    public Integer getFkEndereco() {
        return fkEndereco;
    }
    public void setFkEndereco(Integer fkEndereco) {
        this.fkEndereco = fkEndereco;
    }
    public Integer getFkInstituicao() {
        return fkInstituicao;
    }
    public void setFkInstituicao(Integer fkInstituicao) {
        this.fkInstituicao = fkInstituicao;
    }
    public boolean isLogado() {
        return logado;
    }
    public void setLogado(boolean logado) {
        this.logado = logado;
    }
}