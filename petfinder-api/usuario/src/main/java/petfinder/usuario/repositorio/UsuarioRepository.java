package petfinder.usuario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.usuario.entidade.Usuario;

import java.util.List;

public interface UsuarioRepository
        extends JpaRepository<Usuario, Integer> {

    //@Query("select a from usuario where a.email = ?1 and a.senha = ?2")
    //List<Usuario> findByEmailAndSenha(String email, String senha);

    //List<Usuario> findByNivelAcesso(int instituicao, String nivelAcesso);
}
