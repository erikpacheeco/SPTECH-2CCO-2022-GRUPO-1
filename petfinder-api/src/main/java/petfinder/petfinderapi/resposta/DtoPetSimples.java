package petfinder.petfinderapi.resposta;

import petfinder.petfinderapi.entidades.Pet;

public class DtoPetSimples {
    
    // attributes
    private int id;
    private String nome;

    // constructor
    public DtoPetSimples() {}
    public DtoPetSimples(Pet p) {
        this.id = p.getId();
        this.nome = p.getNome();
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
