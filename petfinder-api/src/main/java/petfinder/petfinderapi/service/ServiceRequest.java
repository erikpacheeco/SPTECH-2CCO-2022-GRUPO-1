package petfinder.petfinderapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.controladores.util.HeaderConfig;
import petfinder.petfinderapi.entidades.Requests;
import petfinder.petfinderapi.repositorios.RequestsRepositorio;

@Service
public class ServiceRequest {
    
    @Autowired
    private RequestsRepositorio repository;

    public void saveRequest() {
        repository.save(new Requests(HeaderConfig.getCurentRequest().toString()));
    }

}
