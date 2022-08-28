package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.UsuarioHasInteresse;
import java.util.List;

public interface UsuarioHasInteresseRepositorio extends JpaRepository<UsuarioHasInteresse, Integer> {

    @Query("SELECT u FROM UsuarioHasInteresse u WHERE u.usuario.id = ?1")
    List<UsuarioHasInteresse> findByUsuario(Integer usuario);

    @Query("SELECT u FROM UsuarioHasInteresse u WHERE u.caracteristica.id = ?1 AND u.usuario.id = ?2")
    List<UsuarioHasInteresse> findByCaracteisticaAndUsuario(Integer caracteristica, Integer usuario);

}
