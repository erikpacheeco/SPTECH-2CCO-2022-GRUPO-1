package petfinder.petfinderapi.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewInstituicoesCadastradas;

public interface ViewInstituicoesCadastradasRepository extends JpaRepository<ViewInstituicoesCadastradas, Integer> {
    
    @Query("SELECT COUNT(v) FROM ViewInstituicoesCadastradas v")
    public Integer countCadastradas();
    
}
