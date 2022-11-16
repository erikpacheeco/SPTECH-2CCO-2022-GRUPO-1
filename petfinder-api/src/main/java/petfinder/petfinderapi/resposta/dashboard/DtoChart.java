package petfinder.petfinderapi.resposta.dashboard;

import java.util.ArrayList;
import java.util.List;

public class DtoChart {

    // attributes
    private List<List<String>> values;

    // constructor
    public DtoChart() {
        this.values = new ArrayList<List<String>>();
    }
    public DtoChart(List<String> titles) {
        this();
        values.add(titles);
    }

    // getters and setters
    public List<List<String>> getValues() {
        return values;
    }
    public void setValues(List<List<String>> values) {
        this.values = values;
    }
}
