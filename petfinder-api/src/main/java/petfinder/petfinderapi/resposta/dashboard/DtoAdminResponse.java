package petfinder.petfinderapi.resposta.dashboard;

import java.util.ArrayList;
import java.util.List;

public class DtoAdminResponse {

    // attributes
    private Integer padrinhos;
    private Integer petsAdotados;
    private Integer premiosPorPet;
    private Integer petsSemPremio;
    private List<List<String>> chartPadrinhosPorSemana;
    private List<List<String>> chartPadrinhosPorMes;
    private List<List<String>> chartCategoriasPorSemana;
    private List<List<String>> chartCategoriasPorMes;
    private List<List<String>> chartPremiosAdicionadosPorSemana;
    private List<List<String>> chartPremiosAdicionadosPorMes;

    // constructor
    public DtoAdminResponse() {
        this.padrinhos = 0;
        this.petsAdotados = 0;
        this.premiosPorPet = 0;
        this.petsSemPremio = 0;
        chartPadrinhosPorSemana = new ArrayList<List<String>>();
        chartPadrinhosPorMes = new ArrayList<List<String>>();
        chartCategoriasPorSemana = new ArrayList<List<String>>();
        chartCategoriasPorMes = new ArrayList<List<String>>();
        chartPremiosAdicionadosPorSemana = new ArrayList<List<String>>();
        chartPremiosAdicionadosPorMes = new ArrayList<List<String>>();
        chartPadrinhosPorSemana.add(List.of("Dia", "Padrinhos"));
        chartPadrinhosPorMes.add(List.of("Mês", "Padrinhos"));
        chartCategoriasPorSemana.add(List.of("Dia", "Adoção", "Pagamento"));
        chartCategoriasPorMes.add(List.of("Mês", "Adoção", "Pagamento"));
        chartPremiosAdicionadosPorSemana.add(List.of("Dia", "Prêmios"));
        chartPremiosAdicionadosPorMes.add(List.of("Mês", "Prêmios")); 
    }

    // ====================================
    // THIS CONSTRUCTOR WILL BE DELETED
    // ====================================
    public DtoAdminResponse(Integer qtdPadrinhoInstituicao, Integer qtdResgatePendenteInstituicao, Integer qtdPetAdotado, Integer qtdPremioPorPetInstituicao, Integer qtdPetSemPremioInstiuicao) {
    }

    // getters and setters
    public Integer getPadrinhos() {
        return padrinhos;
    }
    public void setPadrinhos(Integer padrinhos) {
        this.padrinhos = padrinhos;
    }
    public Integer getPetsAdotados() {
        return petsAdotados;
    }
    public void setPetsAdotados(Integer petsAdotados) {
        this.petsAdotados = petsAdotados;
    }
    public Integer getPremiosPorPet() {
        return premiosPorPet;
    }
    public void setPremiosPorPet(Integer premiosPorPet) {
        this.premiosPorPet = premiosPorPet;
    }
    public Integer getPetsSemPremio() {
        return petsSemPremio;
    }
    public void setPetsSemPremio(Integer petsSemPremio) {
        this.petsSemPremio = petsSemPremio;
    }
    public List<List<String>> getChartPadrinhosPorSemana() {
        return chartPadrinhosPorSemana;
    }
    public void setChartPadrinhosPorSemana(List<List<String>> chartPadrinhosPorSemana) {
        this.chartPadrinhosPorSemana = chartPadrinhosPorSemana;
    }
    public List<List<String>> getChartPadrinhosPorMes() {
        return chartPadrinhosPorMes;
    }
    public void setChartPadrinhosPorMes(List<List<String>> chartPadrinhosPorMes) {
        this.chartPadrinhosPorMes = chartPadrinhosPorMes;
    }
    public List<List<String>> getChartCategoriasPorSemana() {
        return chartCategoriasPorSemana;
    }
    public void setChartCategoriasPorSemana(List<List<String>> chartCategoriasPorSemana) {
        this.chartCategoriasPorSemana = chartCategoriasPorSemana;
    }
    public List<List<String>> getChartCategoriasPorMes() {
        return chartCategoriasPorMes;
    }
    public void setChartCategoriasPorMes(List<List<String>> chartCategoriasPorMes) {
        this.chartCategoriasPorMes = chartCategoriasPorMes;
    }
    public List<List<String>> getChartPremiosAdicionadosPorSemana() {
        return chartPremiosAdicionadosPorSemana;
    }
    public void setChartPremiosAdicionadosPorSemana(List<List<String>> chartPremiosAdicionadosPorSemana) {
        this.chartPremiosAdicionadosPorSemana = chartPremiosAdicionadosPorSemana;
    }
    public List<List<String>> getChartPremiosAdicionadosPorMes() {
        return chartPremiosAdicionadosPorMes;
    }
    public void setChartPremiosAdicionadosPorMes(List<List<String>> chartPremiosAdicionadosPorMes) {
        this.chartPremiosAdicionadosPorMes = chartPremiosAdicionadosPorMes;
    }   
}
