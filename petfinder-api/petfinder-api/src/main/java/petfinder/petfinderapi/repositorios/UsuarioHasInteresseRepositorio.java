package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.UsuarioHasInteresse;

import java.util.List;

public interface UsuarioHasInteresseRepositorio extends JpaRepository<UsuarioHasInteresse, Integer> {

    @Query("SELECT u FROM UsuarioHasInteresse u WHERE u.fkUsuario.id = ?1")
    List<UsuarioHasInteresse> findByFkUsuario(Integer usuario);

    @Query("SELECT u FROM UsuarioHasInteresse u WHERE u.fkCaracteristica.id = ?1 AND u.fkUsuario.id = ?2")
    List<UsuarioHasInteresse> findByFkCaracteisticaAndFkUsuario(Integer caracteristica, Integer usuario);

    //@Query("INSERT INTO UsuarioHasInteresse (fkCaracteristica.id, fkUsuario.id) VALUES (?1, ?2)")
    //UsuarioHasInteresse save(Integer caracteristica, Integer usuario);


}
