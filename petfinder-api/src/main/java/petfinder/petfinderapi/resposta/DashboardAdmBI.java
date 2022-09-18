package petfinder.petfinderapi.resposta;

public class DashboardAdmBI {

    private Integer qtdPadrinhoInstituicao;
    private Integer qtdResgatePendenteInstituicao;
    private Integer qtdPetAdotado;
    private Integer qtdPremioPorPetInstituicao;
    private Integer qtdPetSemPremioInstiuicao;

    public DashboardAdmBI(Integer qtdPadrinhoInstituicao, Integer qtdResgatePendenteInstituicao, Integer qtdPetAdotado, Integer qtdPremioPorPetInstituicao, Integer qtdPetSemPremioInstiuicao) {
        this.qtdPadrinhoInstituicao = qtdPadrinhoInstituicao;
        this.qtdResgatePendenteInstituicao = qtdResgatePendenteInstituicao;
        this.qtdPetAdotado = qtdPetAdotado;
        this.qtdPremioPorPetInstituicao = qtdPremioPorPetInstituicao;
        this.qtdPetSemPremioInstiuicao = qtdPetSemPremioInstiuicao;
    }

    public Integer getQtdPadrinhoInstituicao() {
        return qtdPadrinhoInstituicao;
    }

    public void setQtdPadrinhoInstituicao(Integer qtdPadrinhoInstituicao) {
        this.qtdPadrinhoInstituicao = qtdPadrinhoInstituicao;
    }

    public Integer getQtdResgatePendenteInstituicao() {
        return qtdResgatePendenteInstituicao;
    }

    public void setQtdResgatePendenteInstituicao(Integer qtdResgatePendenteInstituicao) {
        this.qtdResgatePendenteInstituicao = qtdResgatePendenteInstituicao;
    }

    public Integer getQtdPetAdotado() {
        return qtdPetAdotado;
    }

    public void setQtdPetAdotado(Integer qtdPetAdotado) {
        this.qtdPetAdotado = qtdPetAdotado;
    }

    public Integer getQtdPremioPorPetInstituicao() {
        return qtdPremioPorPetInstituicao;
    }

    public void setQtdPremioPorPetInstituicao(Integer qtdPremioPorPetInstituicao) {
        this.qtdPremioPorPetInstituicao = qtdPremioPorPetInstituicao;
    }

    public Integer getQtdPetSemPremioInstiuicao() {
        return qtdPetSemPremioInstiuicao;
    }

    public void setQtdPetSemPremioInstiuicao(Integer qtdPetSemPremioInstiuicao) {
        this.qtdPetSemPremioInstiuicao = qtdPetSemPremioInstiuicao;
    }
}
