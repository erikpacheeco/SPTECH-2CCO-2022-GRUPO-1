package petfinder.petfinderapi.resposta;

import java.util.Objects;

import petfinder.petfinderapi.entidades.Usuario;

public class DtoUsuarioSimples {
    
    // attributes
    private int id;
    private String nome;

    // constructors
    public DtoUsuarioSimples() {}
    public DtoUsuarioSimples(Usuario u) {
        this.id = u.getId();
        this.nome = u.getNome();
    }

    // getters and setters
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
}
