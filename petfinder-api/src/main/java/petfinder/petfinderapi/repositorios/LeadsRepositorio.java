package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Leads;

import java.util.List;

public interface LeadsRepositorio extends JpaRepository<Leads, Integer> {

    @Query(value="select data_cadastro from Leads l where l.tipo != 'sysadm' and l.data_cadastro like CONCAT(?1,'-',?2,'%')", nativeQuery=true)
    public List getLeadPorMes(String ano, String mes);

    @Query(value="select data_cadastro from Leads l where l.tipo = 'user' and l.data_cadastro like CONCAT(?1,'-',?2,'%')", nativeQuery=true)
    public List getLeadUsuarioPorMes(String ano, String mes);

    @Query(value="select data_cadastro from Leads l where l.tipo = 'adm' or l.tipo = 'chatops' and l.data_cadastro like CONCAT(?1,'-',?2,'%')", nativeQuery=true)
    public List getLeadInstituicaoPorMes(String ano, String mes);

    @Query(value = "SELECT * FROM leads WHERE data_cadastro BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE() AND tipo != 'user' AND tipo != 'sysadmin'", nativeQuery = true)
    public List getUltimoMês();

}
