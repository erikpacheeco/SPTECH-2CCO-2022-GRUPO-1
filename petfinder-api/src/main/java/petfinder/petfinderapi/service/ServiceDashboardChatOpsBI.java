package petfinder.petfinderapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.DemandaRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.resposta.DashboardChatOpsBI;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;

import java.util.Optional;

@Service
public class ServiceDashboardChatOpsBI {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private DemandaRepositorio demandaRepositorio;

    public DashboardChatOpsBI getDashboardChatOpsBI(int idUsuario){
        Optional<Usuario> usuario = usuarioRepositorio.findById(idUsuario);

        if (usuario.isEmpty()){
            throw new EntityNotFoundException(idUsuario);
        }

        Integer qtdDemandaAbertaInstituicao = demandaRepositorio.findAllDemandaAbertaInstiuicao(usuario.get().getInstituicao().getId());
        Integer qtdDemandaConcluidaColaboradorInstiuicao = demandaRepositorio.countAllDemandaConcluidaColaborador(usuario.get().getId());

        return new DashboardChatOpsBI(qtdDemandaAbertaInstituicao, qtdDemandaConcluidaColaboradorInstiuicao);

    }

}
