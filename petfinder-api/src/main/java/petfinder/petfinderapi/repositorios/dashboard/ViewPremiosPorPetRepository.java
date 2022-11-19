package petfinder.petfinderapi.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewPremiosPorPet;

public interface ViewPremiosPorPetRepository extends JpaRepository<ViewPremiosPorPet, Integer> {

    @Query("SELECT AVG(v.qtdPremios) FROM ViewPremiosPorPet v WHERE v.instituicaoId = ?1")
    public Double findPremiosPorPetByInstituicaoId(Integer id);

    @Query("SELECT count(v.petId) FROM ViewPremiosPorPet v WHERE v.instituicaoId = ?1 AND v.qtdPremios = 0")
    public Integer findPetsSemPremiosByInstituicaoId(Integer id);

}
