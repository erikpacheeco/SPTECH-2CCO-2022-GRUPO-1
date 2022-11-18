package petfinder.petfinderapi.repositorios.dashboard;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewPremiosMes;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;

public interface ViewPremiosMesRepository extends JpaRepository<ViewPremiosMes, String> {
    
    @Query("SELECT v FROM ViewPremiosMes v WHERE v.instituicaoId = ?1")
    public List<DateHole> findByInstituicaoId(Integer id);

}
