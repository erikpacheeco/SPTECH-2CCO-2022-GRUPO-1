package petfinder.petfinderapi.entidades.dashboard;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "view_premios_ultimos_6_meses")
public class ViewPremiosMes {
    
    // attributes
    @Id
    public String id;
    private Integer instituicaoId;
    private Integer qtdPremios;
    private Integer ano;
    private Integer mes;

    // methods
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
    public Integer getInstituicaoId() {
        return instituicaoId;
    }
    public void setInstituicaoId(Integer instituicaoId) {
        this.instituicaoId = instituicaoId;
    }
    public Integer getQtdPremios() {
        return qtdPremios;
    }
    public void setQtdPremios(Integer qtdPremios) {
        this.qtdPremios = qtdPremios;
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
