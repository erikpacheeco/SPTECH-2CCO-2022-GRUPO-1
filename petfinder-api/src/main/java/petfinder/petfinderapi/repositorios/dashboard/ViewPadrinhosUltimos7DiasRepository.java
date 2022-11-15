package petfinder.petfinderapi.repositorios.dashboard;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewPadrinhosUltimos7Dias;

public interface ViewPadrinhosUltimos7DiasRepository extends JpaRepository<ViewPadrinhosUltimos7Dias, Date> {

    @Query("SELECT v FROM ViewPadrinhosUltimos7Dias v WHERE v.instituicaoId = ?1")
    List<ViewPadrinhosUltimos7Dias> findByInstituicaoId(Integer instituicaoId);
    
}
