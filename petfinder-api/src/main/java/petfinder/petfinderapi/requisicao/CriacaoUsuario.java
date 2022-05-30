package petfinder.petfinderapi.requisicao;

import java.util.ArrayList;
import java.util.List;
import petfinder.petfinderapi.entidades.Caracteristica;
import petfinder.petfinderapi.entidades.Usuario;

public class CriacaoUsuario {
    
    // attributes
    private Usuario usuario;
    private List<Caracteristica> interesses;

    // constructors
    public CriacaoUsuario() {
        this.interesses = new ArrayList<Caracteristica>();
    }

    public CriacaoUsuario(Usuario usuario) {
        this();
        this.usuario = usuario;
    }

    // getters and setters
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<Caracteristica> getInteresses() {
        return interesses;
    }
    public void setInteresses(List<Caracteristica> interesses) {
        this.interesses = interesses;
    }
}
