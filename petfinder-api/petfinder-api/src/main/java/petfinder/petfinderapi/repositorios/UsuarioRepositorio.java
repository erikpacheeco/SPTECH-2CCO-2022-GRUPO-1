package petfinder.petfinderapi.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.petfinderapi.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    
    @Query("select a from Usuario a where a.email = ?1 and a.senha = ?2")
    List<Usuario> findByEmailESenha(String email, String senha);

    @Query("select a from Usuario a where a.fkInstituicao = ?1 and a.nivelAcesso = ?2")
    List<Usuario> findByNivelAcesso(int fkInstituicao, String nivelAcesso);

    public List<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1 AND u.email <> ?2")
    public List<Usuario> findByActualEmailAndNewEmal(String emailNovo, String emailAtual);
}
