package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Demanda;

import java.util.List;

public interface DemandaRepositorio extends JpaRepository<Demanda, Integer> {

    List<Demanda> findAllByFkUsuario(int id);

    @Query("select d from Demanda d WHERE d.fkUsuario = ?1 AND d.status = ?2")
    List<Demanda> findAllByFkUsuarioAndStatus(int id, String status);

    @Query("select d from Demanda d WHERE d.fkInstituicao = ?1 AND d.status = ?2")
    List<Demanda> findAllByFkInstituicaoAndStatus(int fkInstiuicao, String aberto);
}
