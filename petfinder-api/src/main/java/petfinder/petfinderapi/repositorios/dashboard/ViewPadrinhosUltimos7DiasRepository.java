package petfinder.petfinderapi.repositorios.dashboard;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewPadrinhosUltimos7Dias;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;

public interface ViewPadrinhosUltimos7DiasRepository extends JpaRepository<ViewPadrinhosUltimos7Dias, Date> {

    @Query("SELECT v FROM ViewPadrinhosUltimos7Dias v WHERE v.instituicaoId = ?1")
    List<DateHole> findByInstituicaoId(Integer instituicaoId);
    
}
