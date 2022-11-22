package petfinder.petfinderapi.repositorios.dashboard;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewPadrinhosUltimos6Meses;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;

public interface ViewPadrinhosUltimos6MesesRepository extends JpaRepository<ViewPadrinhosUltimos6Meses, String> {
    
    @Query("SELECT v FROM ViewPadrinhosUltimos6Meses v WHERE v.instituicaoId = ?1")
    public List<DateHole> findByInstituicaoId(int id);

}
