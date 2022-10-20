package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Leads;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface LeadsRepositorio extends JpaRepository<Leads, Integer> {

    @Query("select count(dataCadastro) from Leads l where l.tipo <> 'sysadm' and l.dataCadastro like CONCAT(?1,'-',?2,'%')")
    public int countLeadPorMes(String ano, String mes);

    @Query("select count(dataCadastro) from Leads l where l.tipo = 'user' and l.dataCadastro like CONCAT(?1,'-',?2,'%')")
    public int countLeadUsuarioPorMes(String ano, String mes);

    @Query("select count(dataCadastro) from Leads l where l.tipo = 'adm' or l.tipo = 'chatops' and l.dataCadastro like CONCAT(?1,'-',?2,'%')")
    public int countLeadInstituicaoPorMes(String ano, String mes);

    @Query("SELECT l.id FROM Leads l WHERE l.dataCadastro > (CURRENT_DATE - 30) AND l.tipo <> 'user' AND l.tipo <> 'sysadmin'")
    public List<Integer> getUltimoLeadInstituicaoMes();

    @Query("SELECT d.id FROM Demanda d WHERE d.dataFechamento > (CURRENT_DATE - 30) AND d.colaborador.id = ?1 AND d.status = 'CONCLUIDO'")
    public List<Integer> getUltimoLeadInstituicaoAtivaMes(int idInstituicao);

}
