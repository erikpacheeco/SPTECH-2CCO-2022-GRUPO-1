package petfinder.petfinderapi.resposta.dashboard;

import java.util.ArrayList;
import java.util.List;

public class DtoSysadminResponse {

    // attributes
    private Integer instituicoes;
    private Integer usuarios;
    private Integer pets;
    private Integer padrinhos;
    private Integer administradores;
    private List<List<String>> chartVisitantesUsuarios;
    private List<List<String>> chartLeadsClientes;
    private List<List<String>> chartLeadsClientesInstituicao;

    // constructor
    public DtoSysadminResponse() {
        chartVisitantesUsuarios = new ArrayList<List<String>>();
        chartLeadsClientes = new ArrayList<List<String>>();
        chartLeadsClientesInstituicao = new ArrayList<List<String>>();
        chartVisitantesUsuarios.add(List.of("Mês", "Visitantes", "Usuários"));
        chartLeadsClientes.add(List.of("Mês", "Leads", "Clientes"));
        chartLeadsClientesInstituicao.add(List.of("Leads", "Clientes"));
    }

    // getters and setters
    public Integer getInstituicoes() {
        return instituicoes;
    }
    public void setInstituicoes(Integer instituicoes) {
        this.instituicoes = instituicoes;
    }
    public Integer getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(Integer usuarios) {
        this.usuarios = usuarios;
    }
    public Integer getPets() {
        return pets;
    }
    public void setPets(Integer pets) {
        this.pets = pets;
    }
    public Integer getPadrinhos() {
        return padrinhos;
    }
    public void setPadrinhos(Integer padrinhos) {
        this.padrinhos = padrinhos;
    }
    public Integer getAdministradores() {
        return administradores;
    }
    public void setAdministradores(Integer administradores) {
        this.administradores = administradores;
    }
    public List<List<String>> getChartVisitantesUsuarios() {
        return chartVisitantesUsuarios;
    }
    public void setChartVisitantesUsuarios(List<List<String>> chartVisitantesUsuarios) {
        this.chartVisitantesUsuarios = chartVisitantesUsuarios;
    }
    public List<List<String>> getChartLeadsClientes() {
        return chartLeadsClientes;
    }
    public void setChartLeadsClientes(List<List<String>> chartLeadsClientes) {
        this.chartLeadsClientes = chartLeadsClientes;
    }
    public List<List<String>> getChartLeadsClientesInstituicao() {
        return chartLeadsClientesInstituicao;
    }
    public void setChartLeadsClientesInstituicao(List<List<String>> chartLeadsClientesInstituicao) {
        this.chartLeadsClientesInstituicao = chartLeadsClientesInstituicao;
    }
    

}
