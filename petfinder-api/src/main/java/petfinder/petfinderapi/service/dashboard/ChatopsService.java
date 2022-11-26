package petfinder.petfinderapi.service.dashboard;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.DemandaRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.repositorios.dashboard.ViewDemandasPorSemanaRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewDemandasUltimos6MesesRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewDemandasUltimos7DiasRepository;
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

    @Autowired
    private ViewDemandasUltimos7DiasRepository viewDemandas7Dias;

    @Autowired
    private ViewDemandasUltimos6MesesRepository viewDemandas6Meses;
    
    // methods
    public DtoChatopsResponse getChatopsDashboard(int id) {

        // validate
        Usuario usuario = validateChatops(id);

        // building values
        DtoChatopsResponse res =  new DtoChatopsResponse();

        // cards
        res.setEmEspera(demandaRepo.countEmEsperaByInstituicaoId(usuario.getInstituicao().getId()));
        res.setConcluidos(demandaRepo.countConcluidoByInstituicaoId(usuario.getInstituicao().getId()));

        // charts
        Integer suaEquipe = demandaRepo.findSuaEquipe(usuario.getInstituicao().getId(), usuario.getId());
        Integer voce = demandaRepo.findVoce(usuario.getId());
        Integer semSucesso = demandaRepo.findSemSucesso(usuario.getInstituicao().getId());

        res.getChartDemandasPorSemana().add(List.of("Sua equipe (com sucesso)", String.valueOf(suaEquipe)));
        res.getChartDemandasPorSemana().add(List.of("Você (com sucesso)", String.valueOf(voce)));
        res.getChartDemandasPorSemana().add(List.of("Sem sucesso", String.valueOf(semSucesso)));

        List<DateHole> pagamentos7Dias = viewDemandas7Dias.findPagamentosByInstituicaoId(usuario.getInstituicao().getId());
        List<DateHole> apadrinhamentos7Dias = viewDemandas7Dias.findAdocoesByInstituicaoId(usuario.getInstituicao().getId());
        List<DateHole> pagamentos6Mes = viewDemandas6Meses.findPagamentosByInstituicaoId(usuario.getInstituicao().getId());
        List<DateHole> apadrinhamentos6Mes = viewDemandas6Meses.findAdocoesByInstituicaoId(usuario.getInstituicao().getId());

        // building weekly charts
        for(Date date = new Date(); new Date().getDate() - date.getDate() < 7; date.setDate(date.getDate() - 1)) {
            String actual = Conversor.dateToDayMonthString(date);
            res.getChartDemandasMaisFrequentesSemana().add(DashboardUtils.addDateHole(actual, apadrinhamentos7Dias, pagamentos7Dias));
        }

        // building monthly charts
        for(Date date = new Date(); new Date().getMonth() - date.getMonth() < 6; date.setMonth(date.getMonth() - 1)) {
            String actual = Conversor.dateToYearMonthString(date);
            res.getChartDemandasMaisFrequentesMes().add(DashboardUtils.addDateHole(actual, apadrinhamentos6Mes, pagamentos6Mes));
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
            throw new InvalidFieldException("id", "Usuário inválido. O usuário precisa ser 'chatops'");
        }

        // valid admin
        return usuario;
    }

}
