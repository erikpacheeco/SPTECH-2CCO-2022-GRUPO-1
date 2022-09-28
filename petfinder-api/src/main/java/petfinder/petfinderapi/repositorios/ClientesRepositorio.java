package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import petfinder.petfinderapi.entidades.Clientes;

public interface ClientesRepositorio extends JpaRepository<Clientes, Integer> {
}
