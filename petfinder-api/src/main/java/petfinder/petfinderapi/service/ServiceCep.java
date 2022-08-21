package petfinder.petfinderapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.rest.ClienteCep;
import petfinder.petfinderapi.rest.DistanciaResposta;

@Service
public class ServiceCep {

    @Autowired
    private ClienteCep clienteCep;
    
    public Optional<DistanciaResposta> getDistanceBetweenTwoCeps(String cepOrigem, String cepDestino) {
        return Optional.of(clienteCep.getDistancia(cepOrigem, cepDestino));
    }

}
