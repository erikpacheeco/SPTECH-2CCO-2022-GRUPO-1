package petfinder.petfinderapi.resposta;

import petfinder.petfinderapi.entidades.Usuario;

public class SysadmSimples {
    
    // attributes
    private Integer id;
    private String nome;
    private String email;

    // constructor
    public SysadmSimples(Usuario u) {
        this.id = u.getId();
        this.nome = u.getNome();
        this.email = u.getEmail();
    }

    // getters and setters
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
}
