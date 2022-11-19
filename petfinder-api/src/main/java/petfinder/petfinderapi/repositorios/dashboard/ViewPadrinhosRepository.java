package petfinder.petfinderapi.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.dashboard.ViewPadrinhos;

public interface ViewPadrinhosRepository extends JpaRepository<ViewPadrinhos, String> {

    @Query("SELECT sum(v.qtdPadrinhos) FROM ViewPadrinhos v WHERE v.instituicaoId = ?1")
    public Integer getCountPadrinhosByInstituicao(int instituicaoId);
    
}
