package sptech.petfinderapimsg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.petfinderapimsg.entities.Demanda;

public interface DemandaRepository extends JpaRepository<Demanda, Integer>{
    
}
