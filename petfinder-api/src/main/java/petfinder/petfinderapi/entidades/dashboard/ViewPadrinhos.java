package petfinder.petfinderapi.entidades.dashboard;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class ViewPadrinhos {
    
    // attributes
    private Integer instituicaoId;
    private Integer qtdPadrinhos;
    @Id
    private Date data;

    // constructor
    public ViewPadrinhos() {}

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
