package petfinder.usuario.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nome;

    @Email
    private String email;

    @NotNull
    @NotBlank
    private String senha;

    private String nivelAcesso;

    @NotNull
    private int fkEndereco;

    @NotNull
    private int fkInstituicao;

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
        this.nivelAcesso = nivelAcesso;
    }

    public int getFkEndereco() {
        return fkEndereco;
    }
    public void setFkEndereco(int fkEndereco) {
        this.fkEndereco = fkEndereco;
    }

    public int getFkInstituicao() {
        return fkInstituicao;
    }
    public void setFkInstituicao(int fkInstituicao) {
        this.fkInstituicao = fkInstituicao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", nivelAcesso=" + nivelAcesso +
                ", fkEndereco=" + fkEndereco +
                ", fkInstituicao=" + fkInstituicao +
                '}';
    }
}
