package sptech.petfinderapimsg.res;

import sptech.petfinderapimsg.entities.Usuario;

public class DtoUsuarioResponse {
    
    // attributes
    private Integer id;
    private String nome;

    // constructor
    public DtoUsuarioResponse() {}
    public DtoUsuarioResponse(Usuario u) {
        this.id = u.getId();
        this.nome = u.getNome();
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
}
