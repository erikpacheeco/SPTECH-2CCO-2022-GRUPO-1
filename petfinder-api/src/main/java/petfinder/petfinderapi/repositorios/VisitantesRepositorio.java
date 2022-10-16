package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Visitantes;

import java.util.List;

public interface VisitantesRepositorio extends JpaRepository<Visitantes, Integer> {

    @Query(value="select count(data_visita) from Visitantes v where v.data_visita like CONCAT(?1,'-',?2,'%')", nativeQuery=true)
    public int countVisitantesPorMes(String ano, String mes);

}
