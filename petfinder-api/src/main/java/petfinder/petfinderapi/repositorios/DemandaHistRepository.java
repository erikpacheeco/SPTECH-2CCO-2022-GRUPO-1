package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.DemandaHist;
import petfinder.petfinderapi.resposta.DtoDemandaHist;
import java.util.List;

public interface DemandaHistRepository extends JpaRepository<DemandaHist, Integer> {
    List<DemandaHist> findAllByDemandaId(int idDemanda);

    @Query("SELECT new petfinder.petfinderapi.resposta.DtoDemandaHist(d) FROM DemandaHist d WHERE d.demanda.id = ?1")
    List<DtoDemandaHist> findAllHist(int id);
}
