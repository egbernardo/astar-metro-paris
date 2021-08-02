package structure;

public class StationCandidate extends Station implements Comparable<StationCandidate> {

    private StationCandidate predStation;
    private String currentLine;
    private double travelTime;
    private double realDistance;
    private double estimate;
    private double transshipment;


    public StationCandidate(Station station) {
        super(station);
    }

    public void setPredStation(StationCandidate currentStation) {
        this.predStation = currentStation;
    }

    public void setCurrentLine(String currentLine) {
        this.currentLine = currentLine;
    }

    public void setTravelTime(double travelTime) {
        this.travelTime = travelTime;
    }

    public void setRealDistance(double realDistance) {
        this.realDistance = realDistance;
    }

    public void setEstimate(double estimate) {
        this.estimate = estimate;
    }

    public void setTransshipment(int transshipment) {
        this.transshipment = transshipment;
    }

    public String getCurrentLine() {
        return this.currentLine;
    }

    public double getTravelTime() {
        return travelTime;
    }

    public double getTransshipment() {
        return transshipment;
    }

    public StationCandidate getPredStation() {
        return predStation;
    }

    @Override
    public int compareTo(StationCandidate candidate) {
        int compare = 0;

        if(candidate.travelTime < this.travelTime) compare = 1;

        else if(candidate.travelTime > this.travelTime) compare = -1;

        return compare;
    }

    @Override
    public String toString() {
        return " {" + super.getName() +
                " " + currentLine +
                ", " + travelTime +
                ", " + realDistance +
                ", " + estimate +
                " | Transship: " + transshipment +
                '}';
    }
}
