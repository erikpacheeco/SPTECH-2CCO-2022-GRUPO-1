package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.DemandaHist;

public interface DemandaHistRepository extends JpaRepository<DemandaHist, Integer> {
}
