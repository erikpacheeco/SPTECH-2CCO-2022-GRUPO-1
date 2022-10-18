package petfinder.petfinderapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.DemandaRepositorio;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.resposta.DashboardSysadmBI;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;

import java.util.Optional;

@Service
public class ServiceDashboardSysadmBI {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private InstituicaoRepositorio instituicaoRepositorio;

    @Autowired
    private PetRepositorio petRepositorio;

    @Autowired
    private DemandaRepositorio demandaRepositorio;

    public DashboardSysadmBI getDashboardSysadminBI(int idUsuario){

        Optional<Usuario> usuario = usuarioRepositorio.findById(idUsuario);

        if (usuario.isEmpty()){
            throw new EntityNotFoundException(idUsuario);
        }

        Integer qtdInstituicao = instituicaoRepositorio.findQtdInstituicao();
        Integer qtdUsuario = usuarioRepositorio.findAllUsuario();
        Integer qtdAnimal = petRepositorio.findAllPet();
        Integer qtdPadrinho = demandaRepositorio.findAllPadrinho();
        Integer qtdAdmin = usuarioRepositorio.findAllUsuarioSysAdmin();

        return new DashboardSysadmBI(qtdInstituicao, qtdUsuario, qtdAnimal, qtdPadrinho, qtdAdmin);

    }

}
