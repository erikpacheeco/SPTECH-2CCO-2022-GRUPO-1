package petfinder.petfinderapi.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class UsuarioHasInteresse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int fkCaracteristica;
    
    @NotNull
    private int fkUsuario;

    // método
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getFkCaracteristica() {
        return fkCaracteristica;
    }
    public void setFkCaracteristica(int fkCaracteristica) {
        this.fkCaracteristica = fkCaracteristica;
    }
    public int getFkUsuario() {
        return fkUsuario;
    }
    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
