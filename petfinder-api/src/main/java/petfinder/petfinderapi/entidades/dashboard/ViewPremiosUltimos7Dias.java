package petfinder.petfinderapi.entidades.dashboard;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "view_premios_ultimos_7_dias")
public class ViewPremiosUltimos7Dias {
    
    // attributes
    @Id
    private String id;
    private Integer instituicaoId;
    private Integer qtdPremios;
    private Date data;

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
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
}
