package structure;

import java.util.ArrayList;
import java.util.List;

public class Subway {

    private List<Station> stations;
    private Double[][] estimates;
    private Double[][] realDistance;
    private  int numStations;

    public Subway(){
        stations = new ArrayList<>();
    }

    public void addStation(String name, List<String> lines){
        this.stations.add(new Station(name, this.numStations, lines));
        this.numStations++;
    }

    public void addNearStation(String StationA, String StationB, double realDistance){
        if(this.realDistance == null){
            this.realDistance = new Double[numStations][numStations];
        }
        this.realDistance[this.searchIndexStation(StationA)][this.searchIndexStation(StationB)] = realDistance;
    }

    public void setEstimates(String stationA, String stationB, double estimate) {
        if(this.estimates == null){
            this.estimates = new Double[numStations][numStations];
        }
        this.estimates[this.searchIndexStation(stationA)][this.searchIndexStation(stationB)] = estimate;
    }

    public List<Station> getStations(){
        return this.stations;
    }
    public int getNumOfStations() {
        return numStations;
    }

    public Double[][] getEstimates() {
        return estimates;
    }

    public Double[][] getRealDistance() {
        return realDistance;
    }

    public int searchIndexStation(String nameStation){
        int index = -1;
        for (Station s : stations) {
            if(nameStation.equals(s.getName())){
                index = s.getNum();
                break;
            }
        }
        return index;
    }
}
