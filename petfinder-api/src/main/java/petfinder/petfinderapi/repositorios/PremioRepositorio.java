package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
// import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.entidades.Premio;

import java.util.List;

public interface PremioRepositorio extends JpaRepository<Premio, Integer> {

    List<Premio> findAll();

    List<Premio> findById(int id);

    List<Premio> findByFkPetId(int id);

    void deleteById(int id);
}
