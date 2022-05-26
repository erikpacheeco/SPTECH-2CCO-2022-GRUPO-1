package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.Pet;

import java.util.List;

public interface PetRepositorio extends JpaRepository<Pet, Integer> {

    List<Pet> findAll();

    List<Pet> findById(int id);

    List<Pet> findByFkInstituicao(int id);

    void deleteById(int id);
}
