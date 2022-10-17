package petfinder.petfinderapi.resposta;

import petfinder.petfinderapi.entidades.Demanda;

import java.util.List;

public class TipoDemandaDto {

    // attributes
    private List<Demanda> demandaAdocaoSemana;
    private List<Demanda> demandaPagamentoSemana;
    private List<Demanda> demandaAdocaoMes;
    private List<Demanda> demandaPagamentoMes;

    // constructor
    public TipoDemandaDto() {}

    // getter and setter
    public List<Demanda> getDemandaAdocaoSemana() {
        return demandaAdocaoSemana;
    }
    public void setDemandaAdocaoSemana(List<Demanda> demandaAdocaoSemana) {
        this.demandaAdocaoSemana = demandaAdocaoSemana;
    }
    public List<Demanda> getDemandaPagamentoSemana() {
        return demandaPagamentoSemana;
    }
    public void setDemandaPagamentoSemana(List<Demanda> demandaPagamentoSemana) {
        this.demandaPagamentoSemana = demandaPagamentoSemana;
    }
    public List<Demanda> getDemandaAdocaoMes() {
        return demandaAdocaoMes;
    }
    public void setDemandaAdocaoMes(List<Demanda> demandaAdocaoMes) {
        this.demandaAdocaoMes = demandaAdocaoMes;
    }
    public List<Demanda> getDemandaPagamentoMes() {
        return demandaPagamentoMes;
    }
    public void setDemandaPagamentoMes(List<Demanda> demandaPagamentoMes) {
        this.demandaPagamentoMes = demandaPagamentoMes;
    }
}
