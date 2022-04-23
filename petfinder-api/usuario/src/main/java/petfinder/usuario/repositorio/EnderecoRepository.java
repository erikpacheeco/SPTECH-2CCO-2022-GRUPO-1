package petfinder.usuario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.usuario.entidade.Endereco;
import petfinder.usuario.entidade.Usuario;

import java.util.List;

public interface EnderecoRepository
        extends JpaRepository<Endereco, Integer> {

    List<Usuario> findById(int id);
}
