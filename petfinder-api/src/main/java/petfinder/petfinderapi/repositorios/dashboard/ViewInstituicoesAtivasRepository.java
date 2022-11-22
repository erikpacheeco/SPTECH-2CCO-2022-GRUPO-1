package petfinder.petfinderapi.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewInstituicoesAtivas;

public interface ViewInstituicoesAtivasRepository extends JpaRepository<ViewInstituicoesAtivas, Integer> {
    
    @Query("SELECT COUNT(v) FROM ViewInstituicoesAtivas v")
    public Integer countAtivas();

}
