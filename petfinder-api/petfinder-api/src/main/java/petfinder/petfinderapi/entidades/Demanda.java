package petfinder.petfinderapi.entidades;

import petfinder.petfinderapi.enums.EnumCategoria;
import petfinder.petfinderapi.enums.EnumStatusDemanda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Demanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;
    @NotNull
    private EnumCategoria categoria;
    @NotNull
    private String dataAbertura;
    private String dataFechamento;
    @NotNull
    private EnumStatusDemanda status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(EnumCategoria categoria) {
        this.categoria = categoria;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(String dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public EnumStatusDemanda getStatus() {
        return status;
    }

    public void setStatus(EnumStatusDemanda status) {
        this.status = status;
    }
}
