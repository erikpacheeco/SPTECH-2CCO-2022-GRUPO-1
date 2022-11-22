package petfinder.petfinderapi.service.dashboard;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.entidades.dashboard.ViewUsuariosCadastradosUltimos6Meses;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.repositorios.dashboard.ViewClientesRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewInstituicoesAtivasRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewInstituicoesCadastradasRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPadrinhosRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewUsuariosCadastradosUltimos6MesesRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewVisitantesRepository;
import petfinder.petfinderapi.resposta.dashboard.DtoSysadminResponse;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;
import petfinder.petfinderapi.service.dashboard.util.DashboardUtils;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;
import petfinder.petfinderapi.service.exceptions.InvalidFieldException;
import petfinder.petfinderapi.utilitarios.Conversor;

@Service
public class SysadminService {

    // repositories

    @Autowired
    private InstituicaoRepositorio instituicaoRepo;

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private PetRepositorio petRepo;

    @Autowired
    private ViewPadrinhosRepository padrinhosRepo;

    @Autowired
    private ViewVisitantesRepository visitantesRepo;

    @Autowired
    private ViewUsuariosCadastradosUltimos6MesesRepository usuariosCadastradosRepo;

    @Autowired
    private ViewClientesRepository clientesRepo;

    @Autowired
    private ViewInstituicoesCadastradasRepository instituicoesCadastradas;

    @Autowired
    private ViewInstituicoesAtivasRepository instituicoesAtivas;

    // methods

    public DtoSysadminResponse getSysadminDashboard(int id) {

        // validate
        validateSysadmin(id);

        // building values
        DtoSysadminResponse res = new DtoSysadminResponse();

        // cards
        res.setAdministradores(usuarioRepo.countAdmin());
        res.setInstituicoes(instituicaoRepo.findQtdInstituicao());
        res.setPadrinhos(padrinhosRepo.countPadrinhos());
        res.setPets(petRepo.countPets());
        res.setUsuarios(usuarioRepo.countUsuario());

        // chart
        List<DateHole> visitantes = visitantesRepo.findAllVisitantes();
        List<DateHole> cadastrados = usuariosCadastradosRepo.findAllCadastros();
        List<DateHole> cadastrados2 = usuariosCadastradosRepo.findAllCadastros();
        List<DateHole> clientes = clientesRepo.findAllClientes();

        // PieChart
        Integer ativas = instituicoesAtivas.countAtivas();
        Integer cadastradas = instituicoesCadastradas.countCadastradas();

        res.getChartLeadsClientesInstituicao().add(List.of("Ativas", String.valueOf(ativas)));
        res.getChartLeadsClientesInstituicao().add(List.of("Inativas", String.valueOf(cadastradas - ativas)));

        // building monthly charts
        for(Date date = new Date(); new Date().getMonth() - date.getMonth() < 6; date.setMonth(date.getMonth() - 1)) {
            String actual = Conversor.dateToYearMonthString(date);
            res.getChartVisitantesUsuarios().add(DashboardUtils.addDateHole(actual, visitantes, cadastrados));
            res.getChartLeadsClientes().add(DashboardUtils.addDateHole(actual, cadastrados2, clientes));
        }

        return res; 
    }

    // validate admin id
    private Usuario validateSysadmin(Integer sysadminId) {

        // 404 user not found
        Usuario usuario = usuarioRepo.findById(sysadminId).orElseThrow(() -> 
            new EntityNotFoundException(sysadminId)
        );
        
        // 400 invalid user
        if (!usuario.getNivelAcesso().equalsIgnoreCase("sysadm")) {
            throw new InvalidFieldException("id", "Usuário inválido. O usuário precisa ser 'sysadm'");
        }

        // valid admin
        return usuario;
    }
    
}
