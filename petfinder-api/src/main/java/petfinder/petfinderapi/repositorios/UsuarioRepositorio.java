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

    @Query("SELECT COUNT(DISTINCT d.usuario.id) FROM Demanda d WHERE d.categoria = 'PAGAMENTO'")
    public Integer countPadrinhos();

    @Query("SELECT new petfinder.petfinderapi.resposta.ColaboradorSimples(u) FROM Usuario u WHERE u.instituicao.id = ?1 AND u.nivelAcesso = ?2")
    public List<ColaboradorSimples> findColaboradorByInstituicaoAndCategoria(int id, String categoria);

    @Query("SELECT new petfinder.petfinderapi.resposta.UsuarioSemSenha(u) FROM Usuario u WHERE u.nivelAcesso = ?1")
    public List<UsuarioSemSenha> findUsuarioByNivelAcesso(String nivelAcesso);

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.nivelAcesso <> 'sysadm'")
    public Integer findAllUsuario();

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.nivelAcesso = 'sysadm'")
    public Integer findAllUsuarioSysAdmin();

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.nivelAcesso = 'adm'")
    public Integer countAdmin();

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.nivelAcesso = 'user'")
    public Integer countUsuario();

}
