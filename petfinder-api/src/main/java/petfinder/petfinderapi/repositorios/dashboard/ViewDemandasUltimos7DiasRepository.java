package petfinder.petfinderapi.repositorios.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewDemandasUltimos7Dias;

public interface ViewDemandasUltimos7DiasRepository extends JpaRepository<ViewDemandasUltimos7Dias, String> {
    
    @Query("SELECT v FROM ViewDemandasUltimos7Dias v WHERE v.instituicaoId = ?1 AND v.categoria = 'pagamento'")
    public List<ViewDemandasUltimos7Dias> findPagamentosByInstituicaoId(Integer id);

    @Query("SELECT v FROM ViewDemandasUltimos7Dias v WHERE v.instituicaoId = ?1 AND v.categoria = 'adocao'")
    public List<ViewDemandasUltimos7Dias> findAdocoesByInstituicaoId(Integer id);

}
