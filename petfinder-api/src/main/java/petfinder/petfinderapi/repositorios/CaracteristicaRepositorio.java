package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Caracteristica;
import petfinder.petfinderapi.entidades.Pet;

import java.util.List;

public interface CaracteristicaRepositorio extends JpaRepository<Caracteristica, Integer> {

    List<Caracteristica> findAll();

    List<Caracteristica> findById(int id);

    void deleteById(int id);
}
