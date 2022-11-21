package petfinder.petfinderapi.repositorios.dashboard;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewUsuariosCadastradosUltimos6Meses;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;

public interface ViewUsuariosCadastradosUltimos6MesesRepository extends JpaRepository<ViewUsuariosCadastradosUltimos6Meses, String> {
    
    @Query("SELECT v FROM ViewUsuariosCadastradosUltimos6Meses v")
    public List<DateHole> findAllCadastros();

}
