package sptech.petfinderapimsg.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class DemandaHist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String data;
    @NotNull
    @NotBlank
    private String status;
    @NotNull
    @ManyToOne
    private Demanda demanda;

    public DemandaHist() {
    }

    public DemandaHist(Demanda fkDemanda) {
        this.status = fkDemanda.getStatus();
        // flexibilidade de data no update
        this.data = fkDemanda.getDataAbertura();
        this.demanda = fkDemanda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
