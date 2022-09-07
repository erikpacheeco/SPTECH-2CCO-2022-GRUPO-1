package petfinder.petfinderapi.resposta;

import java.util.ArrayList;
import java.util.List;

public class DtoDemandaChats {
    
    // attributes
    private List<DtoDemanda> abertas;
    private List<DtoDemanda> emAndamento;
    private List<DtoDemanda> fechadas;

    // constructor
    public DtoDemandaChats(List<DtoDemanda> demandas) {
        abertas = new ArrayList<DtoDemanda>();
        emAndamento = new ArrayList<DtoDemanda>();
        fechadas = new ArrayList<DtoDemanda>();

        for(DtoDemanda demanda : demandas) {
            String status = demanda.getStatus();
            if (status.equalsIgnoreCase("ABERTO")) {
                abertas.add(demanda);
            } else if (status.equalsIgnoreCase("CONCLUIDO") || status.equalsIgnoreCase("CANCELADO")){
                fechadas.add(demanda);
            } else {
                emAndamento.add(demanda);
            }
        }
    }

    // getters and setters
    public List<DtoDemanda> getAbertas() {
        return abertas;
    }

    public void setAbertas(List<DtoDemanda> abertas) {
        this.abertas = abertas;
    }

    public List<DtoDemanda> getEmAndamento() {
        return emAndamento;
    }

    public void setEmAndamento(List<DtoDemanda> emAndamento) {
        this.emAndamento = emAndamento;
    }

    public List<DtoDemanda> getFechadas() {
        return fechadas;
    }

    public void setFechadas(List<DtoDemanda> fechadas) {
        this.fechadas = fechadas;
    }
}
