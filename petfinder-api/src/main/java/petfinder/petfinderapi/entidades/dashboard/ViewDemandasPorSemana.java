package petfinder.petfinderapi.entidades.dashboard;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;
import petfinder.petfinderapi.utilitarios.Conversor;

@Entity
@Immutable
@Table(name = "view_demandas_por_semana")
public class ViewDemandasPorSemana implements DateHole {
    
    // attributes
    @Id
    private String id;
    private Date fechamento;
    private Integer instituicaoId;
    private Integer qtdDemandas;

    // methods
    @Override
    public String getValue() {
        return String.valueOf(qtdDemandas);
    }
    @Override
    public String getStringDate() {
        return Conversor.dateToDayMonthString(fechamento);
    }

    // getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getFechamento() {
        return fechamento;
    }
    public void setFechamento(Date fechamento) {
        this.fechamento = fechamento;
    }
    public Integer getInstituicaoId() {
        return instituicaoId;
    }
    public void setInstituicaoId(Integer instituicaoId) {
        this.instituicaoId = instituicaoId;
    }
    public Integer getQtdDemandas() {
        return qtdDemandas;
    }
    public void setQtdDemandas(Integer qtdDemandas) {
        this.qtdDemandas = qtdDemandas;
    }
}
