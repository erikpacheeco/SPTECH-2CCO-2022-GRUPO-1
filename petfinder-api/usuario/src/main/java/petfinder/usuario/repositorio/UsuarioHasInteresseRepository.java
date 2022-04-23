package petfinder.usuario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.usuario.entidade.Usuario;
import petfinder.usuario.entidade.UsuarioHasInteresse;

import java.util.List;

public interface UsuarioHasInteresseRepository
            extends JpaRepository<UsuarioHasInteresse, Integer> {

    List<Usuario> findById(int id);
}
