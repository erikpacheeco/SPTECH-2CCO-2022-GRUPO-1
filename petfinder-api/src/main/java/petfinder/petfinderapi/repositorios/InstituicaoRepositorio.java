package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.Instituicao;

public interface InstituicaoRepositorio extends JpaRepository<Instituicao, Integer>{
    
    public Boolean existsById(int id);
    
}
