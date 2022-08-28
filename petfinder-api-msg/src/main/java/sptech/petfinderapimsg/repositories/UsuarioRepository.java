package sptech.petfinderapimsg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.petfinderapimsg.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
}
