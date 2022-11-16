package petfinder.petfinderapi.entidades.dashboard;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "view_padrinhos_ultimos_6_meses")
public class ViewPadrinhosUltimos6Meses {
    
    // attributes
    private Integer instituicaoId;
    @Id
    private Integer qtdPadrinhos;
    private Integer ano;
    private Integer mes;

    // methods
    public String getStringDate() {
        return String.valueOf(ano) + "/" + String.valueOf(mes);
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
