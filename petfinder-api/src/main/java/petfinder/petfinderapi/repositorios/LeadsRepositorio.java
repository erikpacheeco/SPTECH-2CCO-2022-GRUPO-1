package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.Lead;

public interface LeadRepositorio extends JpaRepository<Lead, Integer> {
}
