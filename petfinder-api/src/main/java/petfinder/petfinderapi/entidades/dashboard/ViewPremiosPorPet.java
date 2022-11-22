package petfinder.petfinderapi.entidades.dashboard;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "view_premios_por_pet")
public class ViewPremiosPorPet {
    
    // attributes
    @Id
    private Integer petId;
    private Integer instituicaoId;
    private Integer qtdPremios;

    // getters and setters
    public Integer getPetId() {
        return petId;
    }
    public void setPetId(Integer petId) {
        this.petId = petId;
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
}
