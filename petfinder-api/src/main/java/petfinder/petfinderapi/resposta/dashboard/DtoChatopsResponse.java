package petfinder.petfinderapi.resposta.dashboard;

import java.util.ArrayList;
import java.util.List;

public class DtoChatopsResponse {

    // attributes
    private Integer emEspera;
    private Integer concluidos;
    private List<List<String>> chartDemandasPorSemana;
    private List<List<String>> chartDemandasMaisFrequentesSemana;
    private List<List<String>> chartDemandasMaisFrequentesMes;

    // constructor
    public DtoChatopsResponse() {
        chartDemandasPorSemana = new ArrayList<List<String>>();
        chartDemandasMaisFrequentesSemana = new ArrayList<List<String>>();
        chartDemandasMaisFrequentesMes = new ArrayList<List<String>>();
        chartDemandasPorSemana.add(List.of("", "Demanda"));
        chartDemandasMaisFrequentesSemana.add(List.of("Dia", "Adoção", "Contribuições"));
        chartDemandasMaisFrequentesMes.add(List.of("Mês", "Adoção", "Contribuições"));
    }

    // getters and setters
    public Integer getEmEspera() {
        return emEspera;
    }
    public void setEmEspera(Integer emEspera) {
        this.emEspera = emEspera;
    }
    public Integer getConcluidos() {
        return concluidos;
    }
    public void setConcluidos(Integer concluidos) {
        this.concluidos = concluidos;
    }
    public List<List<String>> getChartDemandasPorSemana() {
        return chartDemandasPorSemana;
    }
    public void setChartDemandasPorSemana(List<List<String>> chartDemandasPorSemana) {
        this.chartDemandasPorSemana = chartDemandasPorSemana;
    }
    public List<List<String>> getChartDemandasMaisFrequentesSemana() {
        return chartDemandasMaisFrequentesSemana;
    }
    public void setChartDemandasMaisFrequentesSemana(List<List<String>> chartDemandasMaisFrequentesSemana) {
        this.chartDemandasMaisFrequentesSemana = chartDemandasMaisFrequentesSemana;
    }
    public List<List<String>> getChartDemandasMaisFrequentesMes() {
        return chartDemandasMaisFrequentesMes;
    }
    public void setChartDemandasMaisFrequentesMes(List<List<String>> chartDemandasMaisFrequentesMes) {
        this.chartDemandasMaisFrequentesMes = chartDemandasMaisFrequentesMes;
    }
}
