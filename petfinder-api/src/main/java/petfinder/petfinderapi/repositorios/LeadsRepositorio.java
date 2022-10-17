package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Leads;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface LeadsRepositorio extends JpaRepository<Leads, Integer> {

    @Query(value="select count(data_cadastro) from Leads l where l.tipo != 'sysadm' and l.data_cadastro like CONCAT(?1,'-',?2,'%')", nativeQuery=true)
    public int countLeadPorMes(String ano, String mes);

    @Query(value="select count(data_cadastro) from Leads l where l.tipo = 'user' and l.data_cadastro like CONCAT(?1,'-',?2,'%')", nativeQuery=true)
    public int countLeadUsuarioPorMes(String ano, String mes);

    @Query(value="select count(data_cadastro) from Leads l where l.tipo = 'adm' or l.tipo = 'chatops' and l.data_cadastro like CONCAT(?1,'-',?2,'%')", nativeQuery=true)
    public int countLeadInstituicaoPorMes(String ano, String mes);

    @Query(value = "SELECT id FROM leads WHERE data_cadastro BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE() AND tipo != 'user' AND tipo != 'sysadmin'", nativeQuery = true)
    public List getUltimoLeadInstituicaoMês();

    @Query(value = "SELECT id FROM Demanda WHERE data_fechamento BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE() AND colaborador_id = ?1 AND status = 'CONCLUIDO'", nativeQuery = true)
    public List getUltimoLeadInstituicaoAtivaMês(Object idInstituicao);

}
