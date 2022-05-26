package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.Caracteristica;

import java.util.List;

public interface CaracteristicaRepositorio extends JpaRepository<Caracteristica, Integer> {

    List<Caracteristica> findAll();

    List<Caracteristica> findById(int id);

    void deleteById(int id);
}
