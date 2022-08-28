package sptech.petfinderapimsg.entities;

import javax.persistence.*;

@Entity
public class UsuarioHasInteresse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Caracteristica caracteristica;
    @ManyToOne
    private Usuario usuario;

    // método
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Caracteristica getCaracteristica() {
        return caracteristica;
    }
    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
