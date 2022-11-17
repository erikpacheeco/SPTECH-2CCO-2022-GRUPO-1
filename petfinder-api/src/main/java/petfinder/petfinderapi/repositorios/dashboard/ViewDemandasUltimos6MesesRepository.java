package petfinder.petfinderapi.repositorios.dashboard;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewDemandasUltimos6Meses;

public interface ViewDemandasUltimos6MesesRepository extends JpaRepository<ViewDemandasUltimos6Meses, String> {
    
    @Query("SELECT v FROM ViewDemandasUltimos6Meses v WHERE v.instituicaoId = ?1 AND v.categoria = 'pagamento'")
    public List<ViewDemandasUltimos6Meses> findPagamentosByInstituicaoId(Integer id);

    @Query("SELECT v FROM ViewDemandasUltimos6Meses v WHERE v.instituicaoId = ?1 AND v.categoria = 'adocao'")
    public List<ViewDemandasUltimos6Meses> findAdocoesByInstituicaoId(Integer id);

}
