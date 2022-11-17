package petfinder.petfinderapi.entidades.dashboard;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "view_demandas_ultimos_6_meses")
public class ViewDemandasUltimos6Meses {
    
    // attributes
    @Id
    private String id;
    private Integer instituicaoId;
    private Integer qtdDemandas;
    private String categoria;
    private Integer ano;
    private Integer mes;

    // getters and setters
    public String getStringDate() {
        return this.ano + "/" + this.mes;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
