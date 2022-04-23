package petfinder.usuario.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UsuarioHasInteresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int fkCaracteristica;

    private int fkUsuario;

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

    @Override
    public String toString() {
        return "UsuarioHasInteresse{" +
                "id=" + id +
                ", fkCaracteristica=" + fkCaracteristica +
                ", fkUsuario=" + fkUsuario +
                '}';
    }
}
