package petfinder.petfinderapi.entidades.dashboard;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Immutable;
import petfinder.petfinderapi.service.dashboard.interfaces.DateHole;
import petfinder.petfinderapi.utilitarios.Conversor;

@Entity
@Immutable
public class ViewPadrinhos implements DateHole {
    
    // attributes
    @Id
    private String id;
    private Integer instituicaoId;
    private Integer qtdPadrinhos;
    private Date data;

    // constructor
    public ViewPadrinhos() {}

    // methods
    @Override
    public String getValue() {
        return String.valueOf(qtdPadrinhos);
    }

    @Override
    public String getStringDate() {
        return Conversor.dateToDayMonthString(data);
    }

    // getters and setters
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
    public Integer getInstituicaoId() {
        return instituicaoId;
    }
    public void setInstituicaoId(Integer instituicaoId) {
        this.instituicaoId = instituicaoId;
    }
}
