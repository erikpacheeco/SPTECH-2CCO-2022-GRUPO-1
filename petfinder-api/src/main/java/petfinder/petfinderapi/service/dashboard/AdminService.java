package petfinder.petfinderapi.service.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.entidades.dashboard.ViewPadrinhosUltimos7Dias;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.repositorios.dashboard.ViewPadrinhosRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPadrinhosUltimos7DiasRepository;
import petfinder.petfinderapi.resposta.dashboard.DtoAdminResponse;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;
import petfinder.petfinderapi.service.exceptions.InvalidFieldException;

@Service
public class AdminService {

    // repositories

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private PetRepositorio petRepo;

    @Autowired
    private ViewPadrinhosRepository viewPadrinhosRepo;

    @Autowired
    private ViewPadrinhosUltimos7DiasRepository viewPadrinhosUltimos7DiasRepo;

    // methods
    public DtoAdminResponse getAdminDashboard(int id) {
        Usuario admin = validateAdmin(id);
        return getAdminValues(admin);
    }

    // build response
    private DtoAdminResponse getAdminValues(Usuario usuario) {
        // getting values
        Integer qtdPadrinhos = viewPadrinhosRepo.getCountPadrinhosByInstituicao(usuario.getInstituicao().getId());
        Integer petsAdotados = petRepo.findAllAdotadoInstituicao(usuario.getInstituicao().getId());
        List<ViewPadrinhosUltimos7Dias> chart1 = viewPadrinhosUltimos7DiasRepo.findByInstituicaoId(usuario.getInstituicao().getId());

        // building
        DtoAdminResponse res = new DtoAdminResponse();
        res.setPadrinhos(qtdPadrinhos);
        res.setPetsAdotados(petsAdotados);

        for(ViewPadrinhosUltimos7Dias i : chart1) {
            res.getChartPadrinhosPorSemana().getValues().add(List.of());
        }

        // 200 ok
        return res;
    }

    // validate admin id
    private Usuario validateAdmin(Integer adminId) {
        // 404 user not found
        Usuario usuario = usuarioRepo.findById(adminId).orElseThrow(() -> 
            new EntityNotFoundException(adminId)
        );
        
        // 400 invalid user
        if (!usuario.getNivelAcesso().equalsIgnoreCase("adm")) {
            throw new InvalidFieldException("id", "Usuário inválido. O usuário precisa ser admin");
        }

        // valid admin
        return usuario;
    }
    
}
