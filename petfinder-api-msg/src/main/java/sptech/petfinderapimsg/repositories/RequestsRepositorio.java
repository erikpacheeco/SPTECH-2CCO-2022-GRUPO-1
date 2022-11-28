package sptech.petfinderapimsg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sptech.petfinderapimsg.entities.Requests;

public interface RequestsRepositorio extends JpaRepository<Requests, Integer> {
    
}
