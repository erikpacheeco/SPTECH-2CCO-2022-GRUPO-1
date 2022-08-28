package sptech.petfinderapimsg.entities;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class DemandaHist {

    // attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private Date data;
    @NotNull
    @NotBlank
    private String status;
    @NotNull
    @ManyToOne
    private Demanda demanda;

    // constructors
    public DemandaHist() {
    }
    public DemandaHist(Demanda demanda) {
        this.status = demanda.getStatus();
        // flexibilidade de data no update
        this.data = demanda.getDataAbertura();
        this.demanda = demanda;
    }

    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Demanda getFkDemanda() {
        return demanda;
    }
    public void setFkDemanda(Demanda fkDemanda) {
        this.demanda = fkDemanda;
    }
}
