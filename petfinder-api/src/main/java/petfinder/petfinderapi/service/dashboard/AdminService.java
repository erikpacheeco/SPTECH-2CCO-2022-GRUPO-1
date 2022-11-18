package petfinder.petfinderapi.service.dashboard;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.entidades.dashboard.ViewDemandasUltimos6Meses;
import petfinder.petfinderapi.entidades.dashboard.ViewDemandasUltimos7Dias;
import petfinder.petfinderapi.entidades.dashboard.ViewPadrinhosUltimos6Meses;
import petfinder.petfinderapi.entidades.dashboard.ViewPadrinhosUltimos7Dias;
import petfinder.petfinderapi.entidades.dashboard.ViewPremiosMes;
import petfinder.petfinderapi.entidades.dashboard.ViewPremiosUltimos7Dias;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.repositorios.dashboard.ViewDemandasUltimos6MesesRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewDemandasUltimos7DiasRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPadrinhosRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPadrinhosUltimos6MesesRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPadrinhosUltimos7DiasRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPremiosMesRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPremiosUltimos7DiasRepository;
import petfinder.petfinderapi.resposta.dashboard.DtoAdminResponse;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;
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

    // methods
    public DtoAdminResponse getAdminDashboard(int id) {
        Usuario admin = validateAdmin(id);
        return getAdminValues(admin);
    }

    // build response
    private DtoAdminResponse getAdminValues(Usuario usuario) {
        // cards
        Integer qtdPadrinhos = viewPadrinhosRepo.getCountPadrinhosByInstituicao(usuario.getInstituicao().getId());
        Integer petsAdotados = petRepo.findAllAdotadoInstituicao(usuario.getInstituicao().getId());

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

        // building weekly charts
        for(Date date = new Date(); new Date().getDate() - date.getDate() < 7; date.setDate(date.getDate() - 1)) {
            String actual = Conversor.dateToDayMonthString(date);
            res.getChartPadrinhosPorSemana().add(addDateHole(actual, chartPadrinhosSem));
            res.getChartCategoriasPorSemana().add(addDateHole(actual, chartCategoriasAdocoesSem, chartCategoriasPagamentoSem));
            res.getChartPremiosAdicionadosPorSemana().add(addDateHole(actual, chartPremiosSem));
        }

        // building monthly charts
        for(Date date = new Date(); new Date().getMonth() - date.getMonth() < 6; date.setMonth(date.getMonth() - 1)) {
            String actualDate = Conversor.dateToYearMonthString(date);
            res.getChartPadrinhosPorMes().add(addDateHole(actualDate, chartPadrinhosMes));
            res.getChartCategoriasPorMes().add(addDateHole(Conversor.dateToYearMonthString(date), chartDemandasPagamentosMes, chartDemandasAdocoesMes));
            res.getChartPremiosAdicionadosPorMes().add(addDateHole(Conversor.dateToYearMonthString(date), chartPremiosMes));
        }

        // 200 ok
        return res;
    }

    // adding single value hole dates
    public static List<String> addDateHole(String actualDate, List<DateHole> list) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getStringDate().equals(actualDate)) {
                return 
                    List.of(
                        actualDate,
                        String.valueOf(list.remove(i).getValue())
                    );
            }
        }

        return List.of(actualDate, "0");
    }

    // adding double value void dates
    public static List<String> addDateHole(String actualDate, List<DateHole> list1, List<DateHole> list2) {
        List<String> values = new ArrayList<String>(List.of(actualDate, "0", "0"));

        for(int i = 0; i < list1.size(); i++) {
            if(list1.get(i).getStringDate().equals(actualDate)) {
                values.set(1, String.valueOf(list1.remove(i).getValue()));
            }
        }

        for(int i = 0; i < list2.size(); i++) {
            if(list2.get(i).getStringDate().equals(actualDate)) {
                values.set(2, String.valueOf(list2.remove(i).getValue()));
            }
        }

        return values;
    }

    // premios por semana
    public List<String> findPremiosSem(String actual, List<ViewPremiosUltimos7Dias> premios) {
        for(int i = 0; i < premios.size(); i++) {
            if(Conversor.dateToDayMonthString(premios.get(i).getData()).equals(actual)) {
                return 
                    List.of(
                        actual,
                        String.valueOf(premios.remove(i).getQtdPremios())
                    );
            }
        }

        return List.of(actual, "0");
    }

    // categorias por semana
    private List<String> findCategoriaPorSemByDate(String actual, List<ViewDemandasUltimos7Dias> adocoes, List<ViewDemandasUltimos7Dias> pagamentos) {
        List<String> values = new ArrayList<String>(List.of(actual, "0", "0"));

        for(int i = 0; i < adocoes.size(); i++) {
            if(Conversor.dateToDayMonthString(adocoes.get(i).getData()).equals(actual)) {
                values.set(1, String.valueOf(adocoes.remove(i).getQtdDemandas()));
            }
        }

        for(int i = 0; i < pagamentos.size(); i++) {
            if(Conversor.dateToDayMonthString(pagamentos.get(i).getData()).equals(actual)) {
                values.set(2, String.valueOf(pagamentos.remove(i).getQtdDemandas()));
            }
        }

        return values;
    }

    // categorias por mês
    private List<String> findCategoriasPorMesByDate(String actual, List<ViewDemandasUltimos6Meses> adocoes, List<ViewDemandasUltimos6Meses> pagamentos) {
        List<String> values = new ArrayList<String>(List.of(actual, "0", "0"));

        for(int i = 0; i < adocoes.size(); i++) {
            if(adocoes.get(i).getStringDate().equals(actual)) {
                values.set(1, String.valueOf(adocoes.remove(i).getQtdDemandas()));
            }
        }

        for(int i = 0; i < pagamentos.size(); i++) {
            if(pagamentos.get(i).getStringDate().equals(actual)) {
                values.set(2, String.valueOf(pagamentos.remove(i).getQtdDemandas()));
            }
        }

        return values;
    }

    // premios por mes
    private List<String> findPremiosMes(String actual, List<ViewPremiosMes> premios) {
        for(ViewPremiosMes v : premios) {
            if(v.getStringDate().equals(actual)) {
                return 
                    List.of(
                        actual,
                        String.valueOf(v.getQtdPremios())
                    );
            }
        }

        return List.of(actual, "0");
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
