package petfinder.petfinderapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import petfinder.petfinderapi.entidades.Demanda;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.DemandaRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.requisicao.DtoPatchDemanda;
import petfinder.petfinderapi.resposta.DtoDemanda;
import petfinder.petfinderapi.resposta.DtoDemandaChats;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;
import petfinder.petfinderapi.service.exceptions.NoContentException;

@Service
public class DemandaService {
    
    @Autowired
    private DemandaRepositorio demandaRepository;

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    public DtoDemanda patchDemandaStatus(int id, DtoPatchDemanda dto) {

        Optional<Demanda> demanda = demandaRepository.findById(id);

        if (demanda.isPresent()) {
            return new DtoDemanda(demanda.get());
        }

        // 404 not found
        throw new EntityNotFoundException(id);
    }

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

    // get chats based on userId
    public DtoDemandaChats getChats(Integer id) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);
        List<DtoDemanda> demandas = null;

        if (usuario.isPresent()) {
            if (usuario.get().getNivelAcesso().equalsIgnoreCase("user")) {
                demandas = getUserChats(id);
            } else if (usuario.get().getNivelAcesso().equalsIgnoreCase("sysadm")) {
                demandas = getSysadmChats(id);
            } else {
                demandas = getColabChats(id);
            }

            // 200 ok
            return new DtoDemandaChats(demandas);
        }

        // 404 not found
        throw new EntityNotFoundException(id);
    }

    // return list of user chats
    private List<DtoDemanda> getUserChats(Integer id) {
        return demandaRepository.findUserDemandas(id);
    }

    // return list of sysadm chats
    private List<DtoDemanda> getSysadmChats(Integer id) {
        return null;
    }

    // return list of colab chats
    private List<DtoDemanda> getColabChats(Integer id) {
        return demandaRepository.findColabDemandas(id);
    }
}
