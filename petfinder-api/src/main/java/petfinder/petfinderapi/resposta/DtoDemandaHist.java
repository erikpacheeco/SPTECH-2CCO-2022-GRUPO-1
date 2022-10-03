package petfinder.petfinderapi.resposta;

import java.util.Date;
import petfinder.petfinderapi.entidades.DemandaHist;

public class DtoDemandaHist {
    
    // attributes
    private Integer id;
    private String status;
    private Date data;

    // constructor
    public DtoDemandaHist() {}
    public DtoDemandaHist(DemandaHist entity) {
        this.id = entity.getId();
        this.status = entity.getStatus();
        this.data = entity.getData();
    }

    // getters and setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
}
