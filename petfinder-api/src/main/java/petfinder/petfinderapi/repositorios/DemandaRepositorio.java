package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Demanda;
import petfinder.petfinderapi.resposta.DtoDemanda;

import java.util.List;
import java.util.Optional;

public interface DemandaRepositorio extends JpaRepository<Demanda, Integer> {


    List<Demanda> findAllByUsuarioId(Integer id);

//    List<Demanda> findAllByColaboradorId(Integer id); saber se são iguais
    // Traz todas as demandas dos colaboradores sem filtro por status
    @Query("SELECT d FROM Demanda d WHERE d.colaborador.id = ?1")
    List<Demanda> findAllDemandaColaborador(int idUsuario);

    @Query("SELECT d from Demanda d WHERE d.usuario.id = ?1 AND d.status = ?2")
    List<Demanda> findAllByUsuarioIdAndStatus(int id, String status);

    @Query("SELECT d from Demanda d WHERE d.instituicao.id = ?1 AND d.status = ?2")
    List<Demanda> findAllByInstituicaoIdAndStatus(int instituicao, String aberto);

    @Query("SELECT d from Demanda d WHERE d.instituicao.id = ?1 AND d.status = ?2")
    List<Demanda> findAllByInstituicaoAndStatus(int instituicao, String aberto);

    List<Demanda> findAllByInstituicaoId(int instituicao);

    // Traz todas demadas abertas do usuário
    @Query("SELECT d from Demanda d WHERE d.usuario.id = ?1 AND d.status = 'ABERTO'")
    List<Demanda> findAllStatusAbertaUsuario(int idUsuario);

    // Traz todas demadas em andamento do usuário
    @Query("SELECT d from Demanda d WHERE d.usuario.id = ?1 AND d.status NOT IN ('ABERTO', 'CONCLUIDO','CANCELADO')")
    List<Demanda> findAllStatusEmAndamentoUsuario(int idUsuario);

    // Traz todas demadas concluidas do usuário
    @Query("SELECT d from Demanda d WHERE d.usuario.id = ?1 AND d.status IN ('CONCLUIDO','CANCELADO')")
    List<Demanda> findAllStatusConcluidoUsuario(int idUsuario);



    // Traz todas demadas abertas da instituição
    @Query("SELECT d FROM Demanda d WHERE d.status = 'ABERTO' AND d.instituicao.id = ?1")
    List<Demanda> findAllStatusAbertaInstituicao(Integer idInstituicao);

    // Traz todas demadas em andamento da instituição de acordo com um colaborador
    @Query("SELECT d FROM Demanda d WHERE d.colaborador.id = ?1 AND d.status NOT IN ('ABERTO', 'CONCLUIDO','CANCELADO')")
    List<Demanda> findAllStatusEmAndamentoColaborador(Integer idUsuario);

    // Traz todas demadas concluidas da instituição de acordo com um colaborador
    @Query("SELECT d FROM Demanda d WHERE d.colaborador.id = ?1 AND d.status IN ('CONCLUIDO','CANCELADO')")
    List<Demanda> findAllStatusConcluidoColaborador(Integer idUsuario);

    Integer countByStatus(String status);
    Integer countByStatusAndCategoria(String status, String categoria);

    @Query("SELECT new petfinder.petfinderapi.resposta.DtoDemanda(d) FROM Demanda d WHERE d.id = ?1")
    public Optional<DtoDemanda> findDtoDemandaById(Integer id);

    @Query("SELECT new petfinder.petfinderapi.resposta.DtoDemanda(d) FROM Demanda d")
    public List<DtoDemanda> findDemandas();

    @Query("SELECT new petfinder.petfinderapi.resposta.DtoDemanda(d) FROM Demanda d WHERE d.usuario.id = ?1")
    public List<DtoDemanda> findUserDemandas(Integer id);

    @Query("SELECT new petfinder.petfinderapi.resposta.DtoDemanda(d) FROM Demanda d WHERE d.colaborador.id = ?1 OR d.status = 'ABERTO'")
    List<DtoDemanda> findColabDemandas(Integer id);

    @Query("SELECT COUNT(d) FROM Demanda d WHERE d.categoria = 'PAGAMENTO' AND d.status  = 'ABERTO'")
    public Integer findAllPadrinho();

    @Query("SELECT COUNT(d) FROM Demanda d WHERE d.categoria = 'PAGAMENTO' AND d.status  = 'ABERTO' AND d.instituicao.id = ?1")
    public Integer findAllPadrinhoInstituicao(int idIntituicao);

    @Query("SELECT COUNT(d) FROM Demanda d WHERE d.categoria = 'RESGATE' AND d.status  = 'ABERTO' AND d.instituicao.id = ?1")
    public Integer findAllResgatePendentes(int idInstituicao);

    @Query("SELECT COUNT(d) FROM Demanda d WHERE d.status  = 'ABERTO' AND d.instituicao.id = ?1")
    public Integer findAllDemandaAbertaInstiuicao(int idInstituicao);

    @Query("SELECT COUNT(d) FROM Demanda d WHERE d.status  = 'CONCLUIDO' AND d.colaborador.id = ?1")
    public Integer countAllDemandaConcluidaColaborador(int idUsuario);

    @Query("SELECT COUNT(d) FROM Demanda d where d.status = 'CONCLUIDO' AND d.dataFechamento LIKE '2022-08-%' AND d.instituicao.id = ?1")
    public Integer countAllApadrinhamentos(int idInstituicao);

    @Query(value="select count(status) FROM Demanda d WHERE d.status = 'CONCLUIDO' and d.data_fechamento like CONCAT(?1,'-',?2,'%')", nativeQuery=true)
    Integer countDemandasConcluidasMes(String ano, String mes);

    @Query(value="select count(status) FROM Demanda d WHERE d.colaborador_id = ?1 AND d.status = 'CONCLUIDO' and d.data_fechamento like CONCAT(?2,'-',?3,'%')", nativeQuery=true)
    Integer countDemandasConcluidasMesColaborador(int idColaborador, String ano, String mes);

    @Query(value="select count(status) FROM Demanda d WHERE d.instituicao_id = ?1 AND d.status = 'CONCLUIDO' and d.data_fechamento like CONCAT(?2,'-',?3,'%')", nativeQuery=true)
    Integer countDemandasConcluidasMesInstituicao(int idInstituicao, String ano, String mes);

    @Query(value="select count(status) FROM Demanda d WHERE d.instituicao_id = ?1 AND d.status = 'CANCELADO' and d.data_fechamento like CONCAT(?2,'-',?3,'%')", nativeQuery=true)
    Integer countDemandasCanceladasMesInstituicao(int idInstituicao, String ano, String mes);

    @Query(value = "SELECT COUNT(data_fechamento) FROM Demanda d WHERE d.instituicao_id = ?1 AND data_fechamento like CONCAT(?2,'-',?3,'-',?4,'%') AND categoria = 'PAGAMENTO'", nativeQuery = true)
    public int countDemandaPagamentoUltimaSemana(int idInstituicao, String ano, String mes, String dia);

    @Query(value = "SELECT COUNT(data_fechamento) FROM Demanda d WHERE d.instituicao_id = ?1 AND data_fechamento like CONCAT(?2,'-',?3,'-',?4,'%') AND categoria = 'ADOCAO'", nativeQuery = true)
    public int countDemandaAdocaoUltimaSemana(int idInstituicao, String ano, String mes, String dia);

    @Query(value="select count(usuario_id) FROM Demanda d WHERE d.instituicao_id = ?1 AND d.categoria = 'ADOCAO' and d.data_fechamento like CONCAT(?2,'-',?3,'%')", nativeQuery=true)
    Integer countDemandasAdocaoMes(int instituicao, String ano, String mes);

    @Query(value="select count(usuario_id) FROM Demanda d WHERE d.instituicao_id = ?1 AND d.categoria = 'PAGAMENTO' and d.data_fechamento like CONCAT(?2,'-',?3,'%')", nativeQuery=true)
    Integer countDemandasPagamentoMes(int instituicao, String ano, String mes);

    @Query(value = "SELECT COUNT(data_fechamento) FROM Demanda d WHERE data_fechamento like CONCAT(?1,'-',?2,'-',?3,'%') AND status = 'CONCLUIDO'", nativeQuery = true)
    public int countPadrinhosUltimaSemana(String ano, String mes, String dia);

    @Query(value="select count(usuario_id) FROM Demanda d WHERE d.instituicao_id = ?1 AND d.categoria = 'PAGAMENTO' and d.data_fechamento like CONCAT(?2,'-',?3,'%')", nativeQuery=true)
    Integer countPadrinhosMes(int instituicao, String ano, String mes);

    @Query(value="select count(usuario_id) FROM Demanda d WHERE d.instituicao_id = ?1 AND d.colaborador_id = ?2 AND d.categoria = 'PAGAMENTO' and d.data_fechamento like CONCAT(?3,'-',?4,'%')", nativeQuery=true)
    Integer countDemandasPagamentoMesUsuario(int instituicao, int usuario, String ano, String mes);

    @Query(value="select count(usuario_id) FROM Demanda d WHERE d.instituicao_id = ?1 AND d.colaborador_id = ?2 AND d.categoria = 'ADOCAO' and d.data_fechamento like CONCAT(?3,'-',?4,'%')", nativeQuery=true)
    Integer countDemandasAdocaoMesUsuario(int instituicao, int usuario, String ano, String mes);

    @Query(value = "SELECT COUNT(data_fechamento) FROM Demanda d WHERE colaborador_id = ?1 AND data_fechamento like CONCAT(?2,'-',?3,'-',?4,'%') AND categoria = 'PAGAMENTO'", nativeQuery = true)
    public int countDemandaPagamentoUltimaSemanaUsuario(int idUsuario, String ano, String mes, String dia);

    @Query(value = "SELECT COUNT(data_fechamento) FROM Demanda d WHERE colaborador_id = ?1 AND data_fechamento like CONCAT(?2,'-',?3,'-',?4,'%') AND categoria = 'ADOCAO'", nativeQuery = true)
    public int countDemandaAdocaoUltimaSemanaUsuario(int idUsuario, String ano, String mes, String dia);

}
