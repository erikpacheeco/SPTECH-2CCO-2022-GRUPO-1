package petfinder.petfinderapi.entidades.dashboard;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;

@Entity
@Immutable
@Table(name = "view_visitantes")
public class ViewVisitantes implements DateHole{

    // attributes
    @Id
    private String id;
    private Integer qtdVisitas;
    private Integer ano;
    private Integer mes;

    // methods
    @Override
    public String getValue() {
        return String.valueOf(qtdVisitas);
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
    public Integer getQtdVisitas() {
        return qtdVisitas;
    }
    public void setQtdVisitas(Integer qtdVisitas) {
        this.qtdVisitas = qtdVisitas;
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
