package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Caracteristica;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.entidades.UsuarioHasInteresse;

import java.util.List;

public interface CaracteristicaRepositorio extends JpaRepository<Caracteristica, Integer> {

    List<Caracteristica> findAll();

    List<Caracteristica> findById(int id);

    void deleteById(int id);

    @Query("SELECT c FROM Caracteristica c WHERE c.caracteristicas = ?1")
    Caracteristica findByCaracteristicas(String caracteristicas);

    // @Query("SELECT c FROM Caracteristica c JOIN UsuarioHasInteresse u ON c.id = u.fkCaracteristica WHERE u.fkUsuario = ?1")
    // List<Caracteristica> findInteressesByUserId(Integer id);

    @Query("SELECT u FROM UsuarioHasInteresse u WHERE u.fkUsuario.id = ?1")
    List<UsuarioHasInteresse> findInteressesByUserId(Integer id);
}
