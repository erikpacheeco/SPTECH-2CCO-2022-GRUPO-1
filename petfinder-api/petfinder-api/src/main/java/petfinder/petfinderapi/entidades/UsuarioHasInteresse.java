package petfinder.petfinderapi.entidades;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class UsuarioHasInteresse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Caracteristica fkCaracteristica;

    @ManyToOne
    private Usuario fkUsuario;

    // método
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Caracteristica getFkCaracteristica() {
        return fkCaracteristica;
    }
    public void setFkCaracteristica(Caracteristica fkCaracteristica) {
        this.fkCaracteristica = fkCaracteristica;
    }
    public Usuario getFkUsuario() {
        return fkUsuario;
    }
    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
