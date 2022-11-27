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
@Table(name = "view_padrinhos_ultimos_7_dias")
public class ViewPadrinhosUltimos7Dias implements DateHole {
    
    // attributes
    @Id
    private String id;
    private Integer instituicaoId;
    private Integer qtdPadrinhos;
    private Date data;

    // methods
    @Override
    public String getStringDate() {
        return Conversor.dateToDayMonthString(this.data);
    }
    @Override
    public String getValue() {
        return String.valueOf(this.getQtdPadrinhos());
    }

    // getters and setters
    public Integer getInstituicaoId() {
        return instituicaoId;
    }
    public void setInstituicaoId(Integer instituicaoId) {
        this.instituicaoId = instituicaoId;
    }
    public Integer getQtdPadrinhos() {
        return qtdPadrinhos;
    }
    public void setQtdPadrinhos(Integer qtdPadrinhos) {
        this.qtdPadrinhos = qtdPadrinhos;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
}
