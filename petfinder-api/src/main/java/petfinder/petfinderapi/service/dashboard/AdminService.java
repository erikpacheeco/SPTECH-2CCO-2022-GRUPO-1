package petfinder.petfinderapi.service.dashboard;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.repositorios.dashboard.ViewDemandasUltimos6MesesRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewDemandasUltimos7DiasRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPadrinhosRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPadrinhosUltimos6MesesRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPadrinhosUltimos7DiasRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPremiosMesRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPremiosPorPetRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPremiosUltimos7DiasRepository;
import petfinder.petfinderapi.resposta.dashboard.DtoAdminResponse;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;
import petfinder.petfinderapi.service.dashboard.util.DashboardUtils;
import petfinder.petfinderapi.service.exceptions.EntityNotFoundException;
import petfinder.petfinderapi.service.exceptions.InvalidFieldException;
import petfinder.petfinderapi.utilitarios.Conversor;

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

    @Autowired
    private ViewPadrinhosUltimos6MesesRepository viewPadrinhosUltimos6MesesRepo;

    @Autowired
    private ViewDemandasUltimos7DiasRepository viewDemandasUltimos7DiasRepo;

    @Autowired
    private ViewDemandasUltimos6MesesRepository viewDemandasUltimos6MesesRepo;

    @Autowired
    private ViewPremiosUltimos7DiasRepository viewPremiosSemRepo;

    @Autowired
    private ViewPremiosMesRepository viewPremiosMesRepo;

    @Autowired
    private ViewPremiosPorPetRepository viewPremiosPorPetRepo;

    // methods
    public DtoAdminResponse getAdminDashboard(Integer id) throws InterruptedException {
        Usuario admin = validateAdmin(id);
        return getAdminValues(admin);
    }

    // build response
    private DtoAdminResponse getAdminValues(Usuario usuario) throws InterruptedException {
        // cards
        Integer qtdPadrinhos = viewPadrinhosRepo.getCountPadrinhosByInstituicao(usuario.getInstituicao().getId());
        Integer petsAdotados = petRepo.findAllAdotadoInstituicao(usuario.getInstituicao().getId());
        Double premiosPorPet = viewPremiosPorPetRepo.findPremiosPorPetByInstituicaoId(usuario.getInstituicao().getId());
        Integer qtdPetsSemPremios = viewPremiosPorPetRepo.findPetsSemPremiosByInstituicaoId(usuario.getInstituicao().getId());

        // padrinhos
        List<DateHole> chartPadrinhosSem = viewPadrinhosUltimos7DiasRepo.findByInstituicaoId(usuario.getInstituicao().getId());
        List<DateHole> chartPadrinhosMes = viewPadrinhosUltimos6MesesRepo.findByInstituicaoId(usuario.getInstituicao().getId());

        // categorias de demanda
        List<DateHole> chartCategoriasPagamentoSem = viewDemandasUltimos7DiasRepo.findPagamentosByInstituicaoId(usuario.getInstituicao().getId());
        List<DateHole> chartCategoriasAdocoesSem = viewDemandasUltimos7DiasRepo.findAdocoesByInstituicaoId(usuario.getInstituicao().getId());
        List<DateHole> chartDemandasPagamentosMes = viewDemandasUltimos6MesesRepo.findPagamentosByInstituicaoId(usuario.getInstituicao().getId());
        List<DateHole> chartDemandasAdocoesMes = viewDemandasUltimos6MesesRepo.findAdocoesByInstituicaoId(usuario.getInstituicao().getId());

        // premios
        List<DateHole> chartPremiosSem = viewPremiosSemRepo.findSemByInstituicaoId(usuario.getInstituicao().getId());
        List<DateHole> chartPremiosMes = viewPremiosMesRepo.findByInstituicaoId(usuario.getInstituicao().getId());

        // building
        DtoAdminResponse res = new DtoAdminResponse();
        res.setPadrinhos(qtdPadrinhos);
        res.setPetsAdotados(petsAdotados);
        res.setPremiosPorPet(premiosPorPet);
        res.setPetsSemPremio(qtdPetsSemPremios);

        // building weekly charts
        for(Date date = new Date(); DashboardUtils.isStopTimeSem(date); date.setDate(date.getDate() - 1)) {
            String actual = Conversor.dateToDayMonthString(date);
            res.getChartPadrinhosPorSemana().add(DashboardUtils.addDateHole(actual, chartPadrinhosSem));
            res.getChartCategoriasPorSemana().add(DashboardUtils.addDateHole(actual, chartCategoriasAdocoesSem, chartCategoriasPagamentoSem));
            res.getChartPremiosAdicionadosPorSemana().add(DashboardUtils.addDateHole(actual, chartPremiosSem));
        }

        // building monthly charts
        for(Date date = new Date(); new Date().getMonth() - date.getMonth() <= 6; date.setMonth(date.getMonth() - 1)) {
            String actualDate = Conversor.dateToYearMonthString(date);
            res.getChartPadrinhosPorMes().add(DashboardUtils.addDateHole(actualDate, chartPadrinhosMes));
            res.getChartCategoriasPorMes().add(DashboardUtils.addDateHole(Conversor.dateToYearMonthString(date), chartDemandasPagamentosMes, chartDemandasAdocoesMes));
            res.getChartPremiosAdicionadosPorMes().add(DashboardUtils.addDateHole(Conversor.dateToYearMonthString(date), chartPremiosMes));
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
