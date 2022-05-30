package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Mensagem;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

    @Query("SELECT m FROM Mensagem m WHERE m.demanda.id = ?1 ORDER BY m.id DESC")
    List<Mensagem> findAllMensagemDemandaDecrescente(Integer idDemanda);

}
