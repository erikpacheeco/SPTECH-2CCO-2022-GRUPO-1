package petfinder.petfinderapi.service.dashboard;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.entidades.dashboard.ViewPadrinhosUltimos6Meses;
import petfinder.petfinderapi.entidades.dashboard.ViewPadrinhosUltimos7Dias;
import petfinder.petfinderapi.repositorios.PetRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
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

        List<ViewPadrinhosUltimos6Meses> list = viewPadrinhosUltimos6MesesRepo.findAll();
        for (ViewPadrinhosUltimos6Meses v : list) {
            System.out.println(v.getMes());
        }

        // building
        DtoAdminResponse res = new DtoAdminResponse();
        res.setPadrinhos(qtdPadrinhos);
        res.setPetsAdotados(petsAdotados);

        // building padrinhos chart
        for(Date date = new Date(); new Date().getDate() - date.getDate() < 7; date.setDate(date.getDate() - 1)) {
            String actual = Conversor.dateToDayMonthString(date);
            res.getChartPadrinhosPorSemana().add(findPadrinhoPorSemByDate(actual, chartPadrinhosSem));
        }

        // building padrinhos chart
        for(Date date = new Date(); new Date().getMonth() - date.getMonth() < 6; date.setMonth(date.getMonth() - 1)) {
            res.getChartPadrinhosPorMes().add(findPadrinhoPorMesByDate(Conversor.dateToYearMonthString(date), chartPadrinhosMes));
        }

        // 200 ok
        return res;
    }

    private List<String> findPadrinhoPorSemByDate(String actual, List<ViewPadrinhosUltimos7Dias> chart) {
        for(ViewPadrinhosUltimos7Dias v : chart) {
            if(Conversor.dateToDayMonthString(v.getData()).equals(actual)) {
                return 
                    List.of(
                        Conversor.dateToDayMonthString(v.getData()),
                        String.valueOf(v.getQtdPadrinhos())
                    );
            }
        }

        return List.of(actual, "0");
    }

    private List<String> findPadrinhoPorMesByDate(String actual, List<ViewPadrinhosUltimos6Meses> chart) {
        for(ViewPadrinhosUltimos6Meses v : chart) {
            // System.out.println(v.getMes());
            // System.out.println("ano: " + v.getAno() + " mes: " + v.getMes() + " " + v.getStringDate() + " == " + actual);
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
