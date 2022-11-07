package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.PetInstituicao;

public interface PetInstituicaoRepositorio extends JpaRepository<PetInstituicao, Integer> {
    
}
