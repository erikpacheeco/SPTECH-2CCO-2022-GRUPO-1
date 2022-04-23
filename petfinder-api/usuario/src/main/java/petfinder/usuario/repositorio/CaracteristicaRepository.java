package petfinder.usuario.repositorio;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.usuario.entidade.Caracteristica;

import java.util.List;

public interface CaracteristicaRepository
                        extends JpaRepository<Caracteristica, Integer> {

    List<Caracteristica> findById(int id);
}
