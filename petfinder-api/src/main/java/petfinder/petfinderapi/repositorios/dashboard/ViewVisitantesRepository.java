package petfinder.petfinderapi.repositorios.dashboard;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewVisitantes;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;

public interface ViewVisitantesRepository extends JpaRepository<ViewVisitantes, String> {
    
    @Query("SELECT v FROM ViewVisitantes v")
    public List<DateHole> findAllVisitantes();

}
