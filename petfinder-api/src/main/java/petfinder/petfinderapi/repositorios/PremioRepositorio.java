package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.entidades.Premio;

import java.util.List;

public interface PremioRepositorio extends JpaRepository<Premio, Integer> {

    List<Premio> findAll();

    List<Premio> findById(int id);

    @Query("SELECT p FROM Premio p WHERE p.fkPet.id = ?1")
    List<Premio> findByPetId(int id);

    void deleteById(int id);
}
