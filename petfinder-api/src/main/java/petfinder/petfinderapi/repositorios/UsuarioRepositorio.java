package petfinder.petfinderapi.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.resposta.ColaboradorSimples;

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
    public List<ColaboradorSimples> findColaboradorById(int id);

}
