package petfinder.petfinderapi.service.dashboard;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.DemandaRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.repositorios.dashboard.ViewDemandasPorSemanaRepository;
import petfinder.petfinderapi.resposta.dashboard.DtoChatopsResponse;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;
import petfinder.petfinderapi.service.dashboard.util.DashboardUtils;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;
import petfinder.petfinderapi.service.exceptions.InvalidFieldException;
import petfinder.petfinderapi.utilitarios.Conversor;

@Service
public class ChatopsService {

    // repositories

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private DemandaRepositorio demandaRepo;

    @Autowired
    private ViewDemandasPorSemanaRepository viewDemandasPorSemanaRepo;
    
    // methods
    public DtoChatopsResponse getAdminDashboard(int id) {

        // validate
        Usuario usuario = validateChatops(id);

        // building values
        DtoChatopsResponse res =  new DtoChatopsResponse();

        // cards
        res.setEmEspera(demandaRepo.countEmEsperaByInstituicaoId(usuario.getInstituicao().getId()));
        res.setConcluidos(demandaRepo.countConcluidoByInstituicaoId(usuario.getInstituicao().getId()));

        // building weekly charts
        for(Date date = new Date(); new Date().getDate() - date.getDate() < 7; date.setDate(date.getDate() - 1)) {
            String actual = Conversor.dateToDayMonthString(date);
            List<DateHole> demandaPorSemana = viewDemandasPorSemanaRepo.findDemandasPorSemana(usuario.getInstituicao().getId());
            res.getChartDemandasPorSemana().add(DashboardUtils.addDateHole(actual, demandaPorSemana));
            
        }
        

        return res;
    }

    // validate admin id
    private Usuario validateChatops(Integer adminId) {
        // 404 user not found
        Usuario usuario = usuarioRepo.findById(adminId).orElseThrow(() -> 
            new EntityNotFoundException(adminId)
        );
        
        // 400 invalid user
        if (!usuario.getNivelAcesso().equalsIgnoreCase("chatops")) {
            throw new InvalidFieldException("id", "Usuário inválido. O usuário precisa ser chatops");
        }

        // valid admin
        return usuario;
    }

}
