package petfinder.petfinderapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.*;
import petfinder.petfinderapi.resposta.DashboardAdmBI;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;

import java.util.Optional;

@Service
public class ServiceDashboardAdmBI {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private DemandaRepositorio demandaRepositorio;

    @Autowired
    private PetRepositorio petRepositorio;

    @Autowired
    private PremioRepositorio premioRepositorio;

    public DashboardAdmBI getDashboardAdminBI(int idUsuario){

        Optional<Usuario> usuario = usuarioRepositorio.findById(idUsuario);

        if (usuario.isEmpty()){
            throw new EntityNotFoundException(idUsuario);
        }

        Integer qtdPadrinhoInstituicao = demandaRepositorio.findAllPadrinhoInstituicao(usuario.get().getInstituicao().getId());
        Integer qtdResgatePendenteInstituicao = demandaRepositorio.findAllResgatePendentes(usuario.get().getInstituicao().getId());
        Integer qtdPetAdotado = petRepositorio.findAllAdotadoInstituicao(usuario.get().getInstituicao().getId());
        Integer qtdPremioPorPetInstituicao = premioRepositorio.findPremioPorPetInstituicao(usuario.get().getInstituicao().getId()) /
                petRepositorio.findAllPetInstituicao(usuario.get().getInstituicao().getId());
        Integer qtdPetSemPremioInstiuicao = petRepositorio.findAllPetInstituicao(usuario.get().getInstituicao().getId()) -
                premioRepositorio.countPetSemPremioInstituicao(usuario.get().getInstituicao().getId());

        return new DashboardAdmBI(qtdPadrinhoInstituicao, qtdResgatePendenteInstituicao, qtdPetAdotado,
                qtdPremioPorPetInstituicao, qtdPetSemPremioInstiuicao);

    }

}
