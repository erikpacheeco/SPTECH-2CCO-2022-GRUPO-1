package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.entidades.Premio;

import java.util.List;

public interface PremioRepositorio extends JpaRepository<Premio, Integer> {

    List<Premio> findAll();

    List<Premio> findById(int id);

    @Query("SELECT p FROM Premio p WHERE p.pet.id = ?1")
    List<Premio> findByPetId(int id);

    void deleteById(int id);

    @Query("SELECT count(p) FROM Premio p WHERE p.pet.instituicao.id = ?1")
    public Integer findPremioPorPetInstituicao(int idInstuicao);

    @Query("SELECT count(distinct p.pet.id) FROM Premio p WHERE p.pet.instituicao.id = ?1")
    public Integer countPetSemPremioInstituicao(int idInstituicao);

}
