package petfinder.petfinderapi.resposta;

public class DashboardSysadmBI {

    private Integer qtdInstituicao;
    private Integer qtdUsuario;
    private Integer qtdAnimal;
    private Integer qtdPadrinho;
    private Integer qtdAdmin;

    public DashboardSysadmBI(Integer qtdInstituicao, Integer qtdUsuario, Integer qtdAnimal, Integer qtdPadrinho, Integer qtdAdmin) {
        this.qtdInstituicao = qtdInstituicao;
        this.qtdUsuario = qtdUsuario;
        this.qtdAnimal = qtdAnimal;
        this.qtdPadrinho = qtdPadrinho;
        this.qtdAdmin = qtdAdmin;
    }

    public Integer getQtdInstituicao() {
        return qtdInstituicao;
    }

    public void setQtdInstituicao(Integer qtdInstituicao) {
        this.qtdInstituicao = qtdInstituicao;
    }

    public Integer getQtdUsuario() {
        return qtdUsuario;
    }

    public void setQtdUsuario(Integer qtdUsuario) {
        this.qtdUsuario = qtdUsuario;
    }

    public Integer getQtdAnimal() {
        return qtdAnimal;
    }

    public void setQtdAnimal(Integer qtdAnimal) {
        this.qtdAnimal = qtdAnimal;
    }

    public Integer getQtdPadrinho() {
        return qtdPadrinho;
    }

    public void setQtdPadrinho(Integer qtdPadrinho) {
        this.qtdPadrinho = qtdPadrinho;
    }

    public Integer getQtdAdmin() {
        return qtdAdmin;
    }

    public void setQtdAdmin(Integer qtdAdmin) {
        this.qtdAdmin = qtdAdmin;
    }
}
