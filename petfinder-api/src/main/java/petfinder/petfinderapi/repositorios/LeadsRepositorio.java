package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.Leads;

public interface LeadsRepositorio extends JpaRepository<Leads, Integer> {
}
