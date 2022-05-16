package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Demanda;

import java.util.List;

public interface DemandaRepositorio extends JpaRepository<Demanda, Integer> {

    List<Demanda> findAllByUsuario(int id);

    @Query("select d from Demanda d WHERE d.usuario.id = ?1 AND d.status = ?2")
    List<Demanda> findAllByUsuarioAndStatus(int id, String status);

    @Query("select d from Demanda d WHERE d.instituicao.id = ?1 AND d.status = ?2")
    List<Demanda> findAllByInstituicaoAndStatus(int instituicao, String aberto);

    List<Demanda> findAllByInstituicao(int instituicao);
}
