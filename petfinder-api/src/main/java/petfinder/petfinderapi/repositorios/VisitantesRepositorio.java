package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.Visitantes;

public interface VisitantesRepositorio extends JpaRepository<Visitantes, Integer> {
}
