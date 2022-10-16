package petfinder.petfinderapi.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.resposta.ColaboradorSimples;
import petfinder.petfinderapi.resposta.UsuarioSemSenha;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1 AND u.senha = ?2")
    List<Usuario> findByEmailESenha(String email, String senha);

    @Query("SELECT u FROM Usuario u WHERE u.instituicao.id = ?1 AND u.nivelAcesso = ?2")
    List<Usuario> findByInstituicaoAndNivelAcesso(Integer instituicao_id, String nivelAcesso);

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    List<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1 AND u.email <> ?2")
    public List<Usuario> findByActualEmailAndNewEmail(String emailNovo, String emailAtual);

    @Query("SELECT u FROM Usuario u WHERE u.instituicao.id = ?1")
    Usuario findByInstituicaoId(int id);

    @Query("SELECT new petfinder.petfinderapi.resposta.ColaboradorSimples(u) FROM Usuario u WHERE u.instituicao.id = ?1")
    public List<ColaboradorSimples> findColaboradorById(Integer id);

    @Query("SELECT DISTINCT new petfinder.petfinderapi.resposta.UsuarioSemSenha(d.usuario) FROM Demanda d WHERE d.instituicao.id = ?1 AND d.categoria = 'PAGAMENTO'")
    public List<UsuarioSemSenha> getPadrinhos(Integer id);

    @Query("SELECT new petfinder.petfinderapi.resposta.ColaboradorSimples(u) FROM Usuario u WHERE u.instituicao.id = ?1 AND u.nivelAcesso = ?2")
    public List<ColaboradorSimples> findColaboradorByInstituicaoAndCategoria(int id, String categoria);

    @Query("SELECT new petfinder.petfinderapi.resposta.UsuarioSemSenha(u) FROM Usuario u WHERE u.nivelAcesso = ?1")
    public List<UsuarioSemSenha> findUsuarioByNivelAcesso(String nivelAcesso);

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.nivelAcesso <> 'sysadm'")
    public Integer findAllUsuario();

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.nivelAcesso = 'adm'")
    public Integer findAllUsuarioAdmin();

    @Query(value="select count(usuario_id) FROM Demanda d WHERE d.instituicao_id = ?1 AND d.categoria = 'PAGAMENTO' and d.data_fechamento like CONCAT(?2,'-',?3,'%')", nativeQuery=true)
    Integer countPadrinhosMes(int instituicao, String ano, String mes);

    @Query(value="select count(usuario_id) FROM Demanda d WHERE d.instituicao_id = ?1 AND d.categoria = 'PAGAMENTO' and d.data_fechamento like CONCAT(?2,'-',?3,'%')", nativeQuery=true)
    Integer countDemandasPagamentoMes(int instituicao, String ano, String mes);

    @Query(value="select count(usuario_id) FROM Demanda d WHERE d.instituicao_id = ?1 AND d.categoria = 'ADOCAO' and d.data_fechamento like CONCAT(?2,'-',?3,'%')", nativeQuery=true)
    Integer countDemandasAdocaoMes(int instituicao, String ano, String mes);

    @Query(value="select count(usuario_id) FROM Demanda d WHERE d.instituicao_id = ?1 AND d.colaborador_id = ?2 AND d.categoria = 'PAGAMENTO' and d.data_fechamento like CONCAT(?3,'-',?4,'%')", nativeQuery=true)
    Integer countDemandasPagamentoMesUsuario(int instituicao, int usuario, String ano, String mes);

    @Query(value="select count(usuario_id) FROM Demanda d WHERE d.instituicao_id = ?1 AND d.colaborador_id = ?2 AND d.categoria = 'ADOCAO' and d.data_fechamento like CONCAT(?3,'-',?4,'%')", nativeQuery=true)
    Integer countDemandasAdocaoMesUsuario(int instituicao, int usuario, String ano, String mes);

    @Query(value = "SELECT COUNT(data_fechamento) FROM Demanda d WHERE data_fechamento like CONCAT(?1,'-',?2,'-',?3,'%') AND status = 'CONCLUIDO'", nativeQuery = true)
    public int countPadrinhosUltimaSemana(String ano, String mes, String dia);

    @Query(value = "SELECT COUNT(data_fechamento) FROM Demanda d WHERE data_fechamento like CONCAT(?1,'-',?2,'-',?3,'%') AND categoria = 'PAGAMENTO'", nativeQuery = true)
    public int countDemandaPagamentoUltimaSemana(String ano, String mes, String dia);

    @Query(value = "SELECT COUNT(data_fechamento) FROM Demanda d WHERE data_fechamento like CONCAT(?1,'-',?2,'-',?3,'%') AND categoria = 'ADOCAO'", nativeQuery = true)
    public int countDemandaAdocaoUltimaSemana(String ano, String mes, String dia);

    @Query(value = "SELECT COUNT(data_fechamento) FROM Demanda d WHERE colaborador_id = ?1 AND data_fechamento like CONCAT(?2,'-',?3,'-',?4,'%') AND categoria = 'PAGAMENTO'", nativeQuery = true)
    public int countDemandaPagamentoUltimaSemanaUsuario(int idUsuario, String ano, String mes, String dia);

    @Query(value = "SELECT COUNT(data_fechamento) FROM Demanda d WHERE colaborador_id = ?1 AND data_fechamento like CONCAT(?2,'-',?3,'-',?4,'%') AND categoria = 'ADOCAO'", nativeQuery = true)
    public int countDemandaAdocaoUltimaSemanaUsuario(int idUsuario, String ano, String mes, String dia);

}
