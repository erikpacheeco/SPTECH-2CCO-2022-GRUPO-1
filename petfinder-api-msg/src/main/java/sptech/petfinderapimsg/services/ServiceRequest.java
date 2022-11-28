package sptech.petfinderapimsg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.petfinderapimsg.controllers.util.HeaderConfig;
import sptech.petfinderapimsg.entities.Requests;
import sptech.petfinderapimsg.repositories.RequestsRepositorio;

@Service
public class ServiceRequest {
    
    @Autowired
    private RequestsRepositorio repository;

    public void saveRequest() {
        repository.save(new Requests(HeaderConfig.getCurentRequest().toString()));
    }

}
