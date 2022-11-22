package petfinder.petfinderapi.entidades.dashboard;

import javax.persistence.Entity;
import javax.persistence.Id;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;

@Entity
public class ViewClientes implements DateHole {
    
    // attributes
    @Id
    private String id;
    private Integer qtdClientes;
    private Integer ano;
    private Integer mes;

    // methods
    @Override
    public String getValue() {
        return String.valueOf(qtdClientes);
    }
    @Override
    public String getStringDate() {
        return ano + "/" + mes;
    }

    // getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getQtdClientes() {
        return qtdClientes;
    }
    public void setQtdClientes(Integer qtdClientes) {
        this.qtdClientes = qtdClientes;
    }
    public Integer getAno() {
        return ano;
    }
    public void setAno(Integer ano) {
        this.ano = ano;
    }
    public Integer getMes() {
        return mes;
    }
    public void setMes(Integer mes) {
        this.mes = mes;
    }
}
