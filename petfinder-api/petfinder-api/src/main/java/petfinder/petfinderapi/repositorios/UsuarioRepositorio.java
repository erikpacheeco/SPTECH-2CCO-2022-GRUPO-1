package petfinder.petfinderapi.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    
    @Query("SELECT u FROM Usuario u WHERE u.email = ?1 AND u.email = ?2")
    List<Usuario> findByEmailESenha(String email, String senha);
    /*
    @Query("SELECT u FROM Usuario u WHERE u.instituicao_id = ?1 AND u.nivel_acesso = ?2")
    List<Usuario> findByInstituicaoAndNivelAcesso(Integer instituicao, String nivelAcesso);
    */
    public List<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1 AND u.email <> ?2")
    public List<Usuario> findByActualEmailAndNewEmail(String emailNovo, String emailAtual);
}
