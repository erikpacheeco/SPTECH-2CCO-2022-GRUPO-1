package petfinder.usuario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petfinder.usuario.entidade.Endereco;
import petfinder.usuario.entidade.Usuario;

import java.util.List;

public interface UsuarioRepository
        extends JpaRepository<Usuario, Integer> {

    @Query("select a from Usuario a where a.email = ?1 and a.senha = ?2")
    List<Usuario> findByEmailESenha(String email, String senha);

    @Query("select a from Usuario a where a.fkInstituicao = ?1 and a.nivelAcesso = ?2")
    List<Usuario> findByNivelAcesso(int fkInstituicao, String nivelAcesso);

}
