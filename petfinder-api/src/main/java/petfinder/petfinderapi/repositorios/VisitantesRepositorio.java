package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Visitantes;

import java.util.List;

public interface VisitantesRepositorio extends JpaRepository<Visitantes, Integer> {

    @Query("select count(dataVisita) from Visitantes v where v.dataVisita like CONCAT(?1,'-',?2,'%')")
    public int countVisitantesPorMes(String ano, String mes);

}
