package petfinder.petfinderapi.resposta;
import petfinder.petfinderapi.entidades.Demanda;
import java.util.List;

public class DemandaUsuario {

    private List<Demanda> listaDemandaAberta;
    private List<Demanda> listaDemandaConcluida;
    private List<Demanda> listaDemandaEmAndamento;

    public DemandaUsuario(List<Demanda> listaDemandaAberta, List<Demanda> listaDemandaEmAndamento, List<Demanda> listaDemandaConcluida) {
        this.listaDemandaAberta = listaDemandaAberta;
        this.listaDemandaEmAndamento = listaDemandaEmAndamento;
        this.listaDemandaConcluida = listaDemandaConcluida;
    }

    public List<Demanda> getListaDemandaAberta() {
        return listaDemandaAberta;
    }

    public List<Demanda> getListaDemandaConcluida() {
        return listaDemandaConcluida;
    }

    public List<Demanda> getListaDemandaEmAndamento() {
        return listaDemandaEmAndamento;
    }

}
