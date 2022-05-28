package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.DemandaHist;

import java.util.List;

public interface DemandaHistRepository extends JpaRepository<DemandaHist, Integer> {
    List<DemandaHist> findAllByDemandaId(int idDemanda);
}
