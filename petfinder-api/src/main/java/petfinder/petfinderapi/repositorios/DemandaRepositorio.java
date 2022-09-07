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
}
