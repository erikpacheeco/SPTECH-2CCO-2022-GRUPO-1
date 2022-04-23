package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.PetHasCaracteristica;

public interface PetHasCaracteristicaRepositorio extends JpaRepository<PetHasCaracteristica, Integer> {
    
}
