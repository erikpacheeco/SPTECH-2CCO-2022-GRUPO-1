package petfinder.petfinderapi.repositorios.dashboard;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewPremiosUltimos7Dias;

public interface ViewPremiosUltimos7DiasRepository extends JpaRepository<ViewPremiosUltimos7Dias, String> {

    @Query("SELECT v FROM ViewPremiosUltimos7Dias v WHERE v.instituicaoId = ?1")
    public List<ViewPremiosUltimos7Dias> findSemByInstituicaoId(Integer id);

}
