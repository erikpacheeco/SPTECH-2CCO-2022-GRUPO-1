package sptech.petfinderapimsg.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.petfinderapimsg.entities.Mensagem;
import sptech.petfinderapimsg.res.DtoMessageResponse;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer>{

    @Query("SELECT new sptech.petfinderapimsg.res.DtoMessageResponse(m) FROM Mensagem m WHERE m.demanda.id = ?1")
    List<DtoMessageResponse> findMessagesByDemandaId(Integer id);
    
}
