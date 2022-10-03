package petfinder.petfinderapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Demanda;
import petfinder.petfinderapi.entidades.DemandaHist;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.DemandaHistRepository;
import petfinder.petfinderapi.repositorios.DemandaRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.requisicao.DtoPatchDemanda;
import petfinder.petfinderapi.resposta.DtoDemanda;
import petfinder.petfinderapi.resposta.DtoDemandaChats;
import petfinder.petfinderapi.resposta.DtoDemandaHist;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;
import petfinder.petfinderapi.service.exceptions.InvalidFieldException;
import petfinder.petfinderapi.service.exceptions.NoContentException;

@Service
public class DemandaService {
    
    @Autowired
    private DemandaRepositorio demandaRepository;

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private DemandaHistRepository histRepository;

    public DtoDemanda patchDemandaStatus(int id, DtoPatchDemanda dto) {

        Demanda demanda = demandaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id)); // 404 not found
        Usuario usuario = usuarioRepository.findById(dto.getUserId()).orElseThrow(() -> new EntityNotFoundException(dto.getUserId())); // 404 not found

        if (dto.getAction().equalsIgnoreCase("accept") || dto.getAction().equalsIgnoreCase("decline")) {
            // user
            if (usuario.getNivelAcesso().equalsIgnoreCase("user")) {
                // nivelAcesso: user
                demanda = patchDemandaUser(dto.getAction(), demanda, usuario);
            } else if (usuario.getNivelAcesso().equalsIgnoreCase("sysadm")) {
                // nivelAcesso: sysadm
                demanda = patchDemandaSysadm(dto.getAction(), demanda, usuario);
            } else {   
                // nivelAcesso: colaborador
                demanda = patchDemandaColab(dto.getAction(), demanda, usuario);
            }

            // saving demand history 
            histRepository.save(new DemandaHist(demanda));
            return new DtoDemanda(demanda);
        }

        // 400 bad request
        throw new InvalidFieldException("action", "o campo 'action' deve ser preenchido com 'accept' ou 'decline'");
    }

    // user
    private Demanda patchDemandaUser(String action, Demanda demanda, Usuario usuario) {

        if(demanda.getUsuario().getId() != usuario.getId()) {
            // 400 bad request
            throw new InvalidFieldException("userId", "O usuário " + usuario.getId() + " não está associado à demanda.");
        } else if(action.equalsIgnoreCase("decline") && !demanda.getStatus().equalsIgnoreCase("CANCELADO")) {
            demanda.setStatus("CANCELADO");
        } else if(demanda.getCategoria().equalsIgnoreCase("PAGAMENTO")) {
            // pagamento
            if(demanda.getStatus().equalsIgnoreCase("EM_ANDAMENTO")) {
                demanda.setStatus("PGTO_REALIZADO_USER");
            } else if (demanda.getStatus().equalsIgnoreCase("PGTO_REALIZADO_INST")) {
                demanda.setStatus("CONCLUIDO");
            } else {
                // 400 bad request
                throw new InvalidFieldException("action", "de acordo com o status atual, usuário ainda não pode realizar uma ação");
            }
        } else if(demanda.getCategoria().equalsIgnoreCase("ADOCAO")) {
            // adocao
            if(demanda.getStatus().equalsIgnoreCase("EM_ANDAMENTO") || demanda.getStatus().equalsIgnoreCase("DOCUMENTO_INVALIDO")) {
                demanda.setStatus("AGUARDANDO_VALIDACAO_DOCUMENTO");
            } else {
                // 400 bad request
                throw new InvalidFieldException("action", "de acordo com o status atual, usuário ainda não pode realizar uma ação");
            }
        } else {
            // 400 bad request
            throw new InvalidFieldException("action", "de acordo com o status atual, usuário ainda não pode realizar uma ação");
        }

        // returning updated demanda
        return demandaRepository.save(demanda);
    }

    // colab
    private Demanda patchDemandaColab(String action, Demanda demanda, Usuario usuario) {

        if (demanda.getStatus().equalsIgnoreCase("ABERTO") && action.equalsIgnoreCase("accept")) {
            demanda.setStatus("EM_ANDAMENTO");
            demanda.setColaborador(usuario);
        } else if (demanda.getColaborador().getId() != usuario.getId()) {

            // 400 bad request
            throw new InvalidFieldException("userId", "O colaborador " + usuario.getId() + " não está associado à essa demanda.");
        }else if(demanda.getCategoria().equalsIgnoreCase("PAGAMENTO")) {
            // pagamento
            if (demanda.getStatus().equalsIgnoreCase("PGTO_REALIZADO_USER")) {
                demanda.setStatus(action.equalsIgnoreCase("accept") ? "PGTO_REALIZADO_INST" : "EM_ANDAMENTO");
            } else {

                // 400 bad request
                throw new InvalidFieldException("action", "de acordo com o status atual, usuário ainda não pode realizar uma ação");
            }
        } else if(demanda.getCategoria().equalsIgnoreCase("ADOCAO")) {
            // adocao
            if (demanda.getStatus().equalsIgnoreCase("AGUARDANDO_VALIDACAO_DOCUMENTO")) {
                demanda.setStatus(action.equalsIgnoreCase("accept") ? "DOCUMENTO_VALIDO" : "DOCUMENTO_INVALIDO");
            } else {

                // 400 bad request
                throw new InvalidFieldException("action", "de acordo com o status atual, usuário ainda não pode realizar uma ação");
            }
        } else {

            // 400 bad request
            throw new InvalidFieldException("action", "ação inválida para o status atual, usuário solicitador ou categoria da demanda");
        }

        // returning updated demanda
        return demandaRepository.save(demanda);
    }

    // sysadm
    private Demanda patchDemandaSysadm(String action, Demanda demanda, Usuario usuario) {
        throw new InvalidFieldException("action", "ação inválida para o status atual, usuário solicitador ou categoria da demanda");
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

    public List<DtoDemandaHist> getHistDemandas(int id) {
        List<DtoDemandaHist> demandas = histRepository.findAllHist(id);

        // 204
        if(demandas.isEmpty()) {
            throw new NoContentException("histórico de demanda");
        }

        // 200
        return demandas;
    }
}
