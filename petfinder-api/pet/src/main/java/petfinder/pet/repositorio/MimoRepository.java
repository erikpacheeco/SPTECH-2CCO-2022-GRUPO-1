package petfinder.pet.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.pet.entidade.Mimo;

import java.util.List;

public interface MimoRepository extends JpaRepository<Mimo, Integer> {

    @Override
    List<Mimo> findAll();

    List<Mimo> findById(int id);

    List<Mimo> findByPetId(int id);
}
