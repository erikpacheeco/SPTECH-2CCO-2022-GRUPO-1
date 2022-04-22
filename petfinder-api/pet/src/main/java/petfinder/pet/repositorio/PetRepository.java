package petfinder.pet.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.pet.entidade.Pet;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    List<Pet> findAll();

    List<Pet> findById(int id);

    List<Pet> findByIdInstituicao(int id);

    void deleteById(int id);
}
