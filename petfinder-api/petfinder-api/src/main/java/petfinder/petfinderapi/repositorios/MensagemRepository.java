package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
}
