package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.Demanda;

import java.util.List;

public interface DemandaRepositorio extends JpaRepository<Demanda, Integer> {

    List<Demanda> findAllByFkUsuario(int id);
    List<Demanda> findAllByFkUsuarioAndStatus(int id, String demanda);
    
}
