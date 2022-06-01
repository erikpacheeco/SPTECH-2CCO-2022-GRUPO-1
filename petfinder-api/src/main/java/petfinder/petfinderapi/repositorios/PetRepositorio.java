package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.entidades.Usuario;

import java.util.List;

public interface PetRepositorio extends JpaRepository<Pet, Integer> {

    List<Pet> findAll();

    List<Pet> findByFkInstituicaoId(int id);

    void deleteById(int id);

    @Query("SELECT u FROM Pet u WHERE u.especie = ?1")
    List<Pet> findByEspecieIgnoreCase(String especie);

}
