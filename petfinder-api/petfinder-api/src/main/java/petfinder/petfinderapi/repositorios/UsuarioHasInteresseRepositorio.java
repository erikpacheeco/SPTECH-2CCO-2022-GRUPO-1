package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import petfinder.petfinderapi.entidades.UsuarioHasInteresse;

public interface UsuarioHasInteresseRepositorio extends JpaRepository<UsuarioHasInteresse, Integer> {
    
}
