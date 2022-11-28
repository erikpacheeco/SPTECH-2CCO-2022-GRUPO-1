package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.Requests;

public interface RequestsRepositorio extends JpaRepository<Requests, Integer> {
    
}
