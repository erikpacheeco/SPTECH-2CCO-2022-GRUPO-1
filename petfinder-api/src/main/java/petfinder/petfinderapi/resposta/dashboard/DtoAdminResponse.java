package petfinder.petfinderapi.resposta.dashboard;

import java.util.List;

public class DtoAdminResponse {

    // attributes
    private Integer padrinhos;
    private Integer petsAdotados;
    private Integer premiosPorPet;
    private Integer petsSemPremio;
    private DtoChart chartPadrinhosPorSemana;
    private DtoChart chartPadrinhosPorMes;
    private DtoChart chartCategoriasPorSemana;
    private DtoChart chartCategoriasPorMes;
    private DtoChart chartPremiosAdicionadosPorSemana;
    private DtoChart chartPremiosAdicionadosPorMes;

    // constructor
    public DtoAdminResponse() {
        this.padrinhos = 0;
        this.petsAdotados = 0;
        this.premiosPorPet = 0;
        this.petsSemPremio = 0;
        chartPadrinhosPorSemana = new DtoChart(List.of("Dia", "Adoção", "Pagamento"));
        chartPadrinhosPorMes = new DtoChart(List.of("Mês", "Adoção", "Pagamento"));
        chartCategoriasPorSemana = new DtoChart(List.of("Dia", "Adoção", "Pagamento"));
        chartCategoriasPorMes = new DtoChart(List.of("Mês", "Adoção", "Pagamento"));
        chartPremiosAdicionadosPorSemana = new DtoChart(List.of("Dia", "Prêmios"));
        chartPremiosAdicionadosPorMes = new DtoChart(List.of("Mês", "Prêmios"));
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
    public void setPetsAdotados(Integer petAdotados) {
        this.petsAdotados = petAdotados;
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
    public DtoChart getChartPadrinhosPorSemana() {
        return chartPadrinhosPorSemana;
    }
    public void setChartPadrinhosPorSemana(DtoChart chartPadrinhosPorSemana) {
        this.chartPadrinhosPorSemana = chartPadrinhosPorSemana;
    }
    public DtoChart getChartPadrinhosPorMes() {
        return chartPadrinhosPorMes;
    }
    public void setChartPadrinhosPorMes(DtoChart chartPadrinhosPorMes) {
        this.chartPadrinhosPorMes = chartPadrinhosPorMes;
    }
    public DtoChart getChartCategoriasPorSemana() {
        return chartCategoriasPorSemana;
    }
    public void setChartCategoriasPorSemana(DtoChart chartCategoriasPorSemana) {
        this.chartCategoriasPorSemana = chartCategoriasPorSemana;
    }
    public DtoChart getChartCategoriasPorMes() {
        return chartCategoriasPorMes;
    }
    public void setChartCategoriasPorMes(DtoChart chartCategoriasPorMes) {
        this.chartCategoriasPorMes = chartCategoriasPorMes;
    }
    public DtoChart getChartPremiosAdicionadosPorSemana() {
        return chartPremiosAdicionadosPorSemana;
    }
    public void setChartPremiosAdicionadosPorSemana(DtoChart chartPremiosAdicionadosPorSemana) {
        this.chartPremiosAdicionadosPorSemana = chartPremiosAdicionadosPorSemana;
    }
    public DtoChart getChartPremiosAdicionadosPorMes() {
        return chartPremiosAdicionadosPorMes;
    }
    public void setChartPremiosAdicionadosPorMes(DtoChart chartPremiosAdicionadosPorMes) {
        this.chartPremiosAdicionadosPorMes = chartPremiosAdicionadosPorMes;
    }
}
