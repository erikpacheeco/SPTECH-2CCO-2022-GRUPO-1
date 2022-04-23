package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import petfinder.petfinderapi.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    
}
