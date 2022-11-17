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
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.repositorios.dashboard.ViewDemandasUltimos6MesesRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewDemandasUltimos7DiasRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPadrinhosRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPadrinhosUltimos6MesesRepository;
import petfinder.petfinderapi.repositorios.dashboard.ViewPadrinhosUltimos7DiasRepository;
import petfinder.petfinderapi.resposta.dashboard.DtoAdminResponse;
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
    private ViewDemandasUltimos6MesesRepository viewDemandasUltimos6Meses;

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
        List<ViewPadrinhosUltimos7Dias> chartPadrinhosSem = viewPadrinhosUltimos7DiasRepo.findByInstituicaoId(usuario.getInstituicao().getId());
        List<ViewPadrinhosUltimos6Meses> chartPadrinhosMes = viewPadrinhosUltimos6MesesRepo.findByInstituicaoId(usuario.getInstituicao().getId());
        List<ViewDemandasUltimos7Dias> chartCategoriasPagamentoSem = viewDemandasUltimos7DiasRepo.findPagamentosByInstituicaoId(usuario.getInstituicao().getId());
        List<ViewDemandasUltimos7Dias> chartCategoriasAdocoesSem = viewDemandasUltimos7DiasRepo.findAdocoesByInstituicaoId(usuario.getInstituicao().getId());
        List<ViewDemandasUltimos6Meses> chartDemandasPagamentosMes = viewDemandasUltimos6Meses.findPagamentosByInstituicaoId(usuario.getInstituicao().getId());
        List<ViewDemandasUltimos6Meses> chartDemandasAdocoesMes = viewDemandasUltimos6Meses.findAdocoesByInstituicaoId(usuario.getInstituicao().getId());

        // building
        DtoAdminResponse res = new DtoAdminResponse();
        res.setPadrinhos(qtdPadrinhos);
        res.setPetsAdotados(petsAdotados);

        // building weekly charts
        for(Date date = new Date(); new Date().getDate() - date.getDate() < 7; date.setDate(date.getDate() - 1)) {
            String actual = Conversor.dateToDayMonthString(date);
            res.getChartPadrinhosPorSemana().add(findPadrinhoPorSemByDate(actual, chartPadrinhosSem));
            res.getChartCategoriasPorSemana().add(findCategoriaPorSemByDate(actual, chartCategoriasAdocoesSem, chartCategoriasPagamentoSem));
        }

        // building monthly charts
        for(Date date = new Date(); new Date().getMonth() - date.getMonth() < 6; date.setMonth(date.getMonth() - 1)) {
            res.getChartPadrinhosPorMes().add(findPadrinhoPorMesByDate(Conversor.dateToYearMonthString(date), chartPadrinhosMes));
            res.getChartCategoriasPorMes().add(findCategoriasPorMesByDate(Conversor.dateToYearMonthString(date), chartDemandasPagamentosMes, chartDemandasAdocoesMes));
        }

        // 200 ok
        return res;
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

    // padrinho por semana
    private List<String> findPadrinhoPorSemByDate(String actual, List<ViewPadrinhosUltimos7Dias> chart) {
        for(int i = 0; i < chart.size(); i++) {
            if(Conversor.dateToDayMonthString(chart.get(i).getData()).equals(actual)) {
                return 
                    List.of(
                        actual,
                        String.valueOf(chart.remove(i).getQtdPadrinhos())
                    );
            }
        }

        return List.of(actual, "0");
    }

    // padrinho por mes
    private List<String> findPadrinhoPorMesByDate(String actual, List<ViewPadrinhosUltimos6Meses> chart) {
        for(ViewPadrinhosUltimos6Meses v : chart) {
            if(v.getStringDate().equals(actual)) {
                return 
                    List.of(
                        actual,
                        String.valueOf(v.getQtdPadrinhos())
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
