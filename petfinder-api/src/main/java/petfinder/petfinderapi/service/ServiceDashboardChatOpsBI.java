package petfinder.petfinderapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.DemandaRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.resposta.dashboard.DtoChatopsResponse;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;

import java.util.Optional;

@Service
public class ServiceDashboardChatOpsBI {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private DemandaRepositorio demandaRepositorio;

}
