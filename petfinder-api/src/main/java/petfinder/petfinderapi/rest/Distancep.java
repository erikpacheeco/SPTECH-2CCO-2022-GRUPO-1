package petfinder.petfinderapi.rest;

import java.util.ArrayList;
import java.util.List;

public class Distancep {

    // attributes
    private Double distance;
    private List<CepInfo> cepsInfo;

    // constructors
    public Distancep(Double distance) {
        this();
        this.distance = distance;
    }
    public Distancep() {
        this.cepsInfo = new ArrayList<CepInfo>();
    }

    // getters and setters
    public Double getDistance() {
        return distance;
    }
    public void setDistance(Double distance) {
        this.distance = distance;
    }
    public List<CepInfo> getCepsInfo() {
        return cepsInfo;
    }
    public void setCepsInfo(List<CepInfo> cepsInfo) {
        this.cepsInfo = cepsInfo;
    }
}
