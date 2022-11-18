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
@Table(name = "view_demandas_ultimos_7_dias")
public class ViewDemandasUltimos7Dias implements DateHole {
    
    // attributes
    @Id
    private String id;
    private Integer instituicaoId;
    private Integer qtdDemandas;
    private String categoria;
    private Date data;

    // methods
    @Override
    public String getValue() {
        return String.valueOf(this.qtdDemandas);
    }
    @Override
    public String getStringDate() {
        return Conversor.dateToDayMonthString(this.data);
    }

    // getters and setters
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
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
}
