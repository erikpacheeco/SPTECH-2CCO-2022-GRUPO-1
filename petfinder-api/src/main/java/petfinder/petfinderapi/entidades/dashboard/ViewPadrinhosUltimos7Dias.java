package petfinder.petfinderapi.entidades.dashboard;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "view_padrinhos_ultimos_7_dias")
public class ViewPadrinhosUltimos7Dias {
    
    // attributes
    private Integer instituicaoId;
    private Integer qtdPadrinhos;
    @Id
    private Date data;

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
