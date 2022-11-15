package petfinder.petfinderapi.resposta.dashboard;

import java.util.ArrayList;
import java.util.List;

public class DtoChart {

    // attributes
    private List<String> titles;
    private List<List<Double>> values;

    // constructor
    public DtoChart() {
        this.titles = new ArrayList<String>();
        this.values = new ArrayList<List<Double>>();
    }
    public DtoChart(List<String> titles) {
        this();
        this.titles = titles;
    }

    // getters and setters
    public List<String> getTitles() {
        return titles;
    }
    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
    public List<List<Double>> getValues() {
        return values;
    }
    public void setValues(List<List<Double>> values) {
        this.values = values;
    }
}
