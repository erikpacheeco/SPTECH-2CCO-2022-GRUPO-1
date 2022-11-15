package petfinder.petfinderapi.resposta.dashboard;

public class DashboardChatOpsBI {

    private Integer qtdDemandaAbertaInstituicao;
    private Integer qtdDemandaConcluidaColaboradorInstiuicao;

    public DashboardChatOpsBI(Integer qtdDemandaAbertaInstituicao, Integer qtdDemandaConcluidaColaboradorInstiuicao) {
        this.qtdDemandaAbertaInstituicao = qtdDemandaAbertaInstituicao;
        this.qtdDemandaConcluidaColaboradorInstiuicao = qtdDemandaConcluidaColaboradorInstiuicao;
    }

    public Integer getQtdDemandaAbertaInstituicao() {
        return qtdDemandaAbertaInstituicao;
    }

    public void setQtdDemandaAbertaInstituicao(Integer qtdDemandaAbertaInstituicao) {
        this.qtdDemandaAbertaInstituicao = qtdDemandaAbertaInstituicao;
    }

    public Integer getQtdDemandaConcluidaColaboradorInstiuicao() {
        return qtdDemandaConcluidaColaboradorInstiuicao;
    }

    public void setQtdDemandaConcluidaColaboradorInstiuicao(Integer qtdDemandaConcluidaColaboradorInstiuicao) {
        this.qtdDemandaConcluidaColaboradorInstiuicao = qtdDemandaConcluidaColaboradorInstiuicao;
    }
}
