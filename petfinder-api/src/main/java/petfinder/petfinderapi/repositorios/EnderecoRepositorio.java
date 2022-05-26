package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import petfinder.petfinderapi.entidades.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Integer> {
    
}
