package petfinder.petfinderapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.repositorios.DemandaRepositorio;
import petfinder.petfinderapi.resposta.DtoDemanda;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;
import petfinder.petfinderapi.service.exceptions.NoContentException;

@Service
public class DemandaService {
    
    @Autowired
    private DemandaRepositorio demandaRepository;

    public DtoDemanda getDemandaById(Integer id) {
        Optional<DtoDemanda> demanda = demandaRepository.findDtoDemandaById(id); 

        if(demanda.isPresent()) {
            // 200 ok
            return demanda.get();
        }

        // 404 not found
        throw new EntityNotFoundException(id);
    }

    public List<DtoDemanda> getDemandas() {
        
        List<DtoDemanda> demandas = demandaRepository.findDemandas();

        // 200 ok
        if (demandas.size() > 0) {
            return demandas;
        }

        // 204 no content
        throw new NoContentException("Demanda");
    }
}
