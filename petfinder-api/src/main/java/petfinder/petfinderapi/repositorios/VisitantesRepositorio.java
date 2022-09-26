package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.QtdVisitantes;

public interface QtdVisitantesRepositorio extends JpaRepository<QtdVisitantes, Integer> {
}
