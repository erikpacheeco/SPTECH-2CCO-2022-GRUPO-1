package petfinder.petfinderapi.repositorios.dashboard;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewClientes;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;

public interface ViewClientesRepository extends JpaRepository<ViewClientes, String> {
    
    @Query("SELECT v FROM ViewClientes v")
    public List<DateHole> findAllClientes();

}
