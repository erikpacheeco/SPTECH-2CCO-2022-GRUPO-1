package petfinder.petfinderapi.repositorios.dashboard;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewDemandasPorSemana;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;

public interface ViewDemandasPorSemanaRepository extends JpaRepository<ViewDemandasPorSemana, String>{
    
    @Query("SELECT v FROM ViewDemandasPorSemana v WHERE v.instituicaoId = ?1")
    List<DateHole>findDemandasPorSemana(Integer id);
    
}