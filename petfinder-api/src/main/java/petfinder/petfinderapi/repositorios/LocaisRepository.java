package petfinder.petfinderapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import petfinder.petfinderapi.entidades.Locais;

public interface LocaisRepository extends JpaRepository<Locais, Integer> {  
}
