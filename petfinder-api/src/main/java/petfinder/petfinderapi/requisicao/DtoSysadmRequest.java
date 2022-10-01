package petfinder.petfinderapi.requisicao;

import petfinder.petfinderapi.entidades.Convertible;
import petfinder.petfinderapi.entidades.Usuario;

public class DtoSysadmRequest implements Convertible<Usuario> {
    
    // attributes
    private String email;
    private String nome;
    private String senha;

    // constructor
    public DtoSysadmRequest() {}

    // methods
    public Usuario convert() {
        Usuario entity = new Usuario();
        entity.setEmail(this.email);
        entity.setNivelAcesso("sysadm");
        entity.setNome(this.nome);
        entity.setSenha(this.senha);
        entity.setId(null);
        return entity;
    }

    // getters and setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
