package search;

import structure.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class AStar {
    private final int indexSource;
    private final int indexTarget;

    private String currentLine;
    private final String lineTarget;

    private final Subway subway;

    private final Queue<StationCandidate> openedCandidates;
    private final boolean[] opened;
    private final boolean[] closed;

    private double costPath;
    final private StringBuilder path;

    private int numIteration;

    public AStar(String source, String lineSource, String target, String lineTarget, Subway subway) {
        this.indexSource = subway.searchIndexStation(source);
        this.indexTarget =  subway.searchIndexStation(target);
        this.currentLine = lineSource;
        this.lineTarget = lineTarget;
        this.subway = subway;

        this.openedCandidates = new LinkedList<>();
        this.closed = new boolean[subway.getNumOfStations()];
        this.opened = new boolean[subway.getNumOfStations()];
        this.path = new StringBuilder();
        this.search();
    }

    private void search() {
        boolean target = false;
        int currentIndex = this.indexSource;
        StationCandidate currentStation = new StationCandidate(this.subway.getStations().get(currentIndex)); // START
        currentStation.setCurrentLine(this.currentLine);
        List<StationCandidate> candidates = new LinkedList<>(); //aux

        this.openedCandidates.add(currentStation); // for first iteration

        this.viewFrontier();
        while (!this.openedCandidates.isEmpty() && !target){
            currentStation = this.openedCandidates.remove(); // first candidate in frontier with smaller time til target
            currentIndex = currentStation.getNum();

            if(currentIndex == this.indexTarget){
                if(currentStation.getCurrentLine().equals(this.lineTarget)){
                    this.evaluation(currentStation);
                    this.costPath = currentStation.getTravelTime(); // increment cost

                    this.describePathTo(currentStation);
                    this.path.append(currentStation.getName());//-----------------------------THIS.PATH
                    break;
                }else{ // being target, change line
                    this.checkTransshipment(currentStation);
                    this.evaluation(currentStation);
                    this.costPath = currentStation.getTravelTime(); // increment cost

                    this.describePathTo(currentStation);
                    this.path.append(currentStation.getName());//-----------------------------THIS.PATH
                    break;
                }
            }

            if(currentIndex != -1 && !this.closed[currentIndex]){
                this.closed[currentIndex] = true; // mark structure.Station visited
                this.evaluation(currentStation);
                this.costPath = currentStation.getTravelTime(); // increment cost
                this.currentLine = currentStation.getCurrentLine();

                // INSERT - return true if target
                target = this.generate(currentStation, candidates);

                if(!target) {
                    this.expand(candidates); // expand frontier and return list of candidates empty for new iteration

                }
            }
            this.viewFrontier();
        }
    }

    private void viewFrontier() {
        // VIEW FRONTIER
        for (StationCandidate candidate: this.openedCandidates) {
            System.out.println("ITERATION "+this.numIteration+ ": " + candidate); // ----------------------------- ITERATION
        }
        this.numIteration++;
        System.out.println();
    }

    private boolean generate(StationCandidate currentStation, List<StationCandidate> candidates) {
        boolean target = false;
        int currentIndex = currentStation.getNum();

        // Add station candidates for expand and set your predecessor
        for (int j = 0; j < this.subway.getNumOfStations(); j++) {
            if(this.subway.getRealDistance()[currentIndex][j]!= null && !this.closed[j]) {
                StationCandidate currentCandidate = new StationCandidate(this.subway.getStations().get(j));
                currentCandidate.setPredStation(currentStation);
                this.checkTransshipment(currentCandidate);

                if(j != this.indexTarget){

                    if(!this.opened[j]){
                        candidates.add(currentCandidate);
                        this.opened[j] = true;
                    }else{
                        this.closed[j] = true;
                    }
                    // if is target check to be in target line
                }else if(currentCandidate.getCurrentLine().equals(this.lineTarget)){
                    this.evaluation(currentCandidate);
                    this.costPath = currentCandidate.getTravelTime();
                    this.currentLine = currentCandidate.getCurrentLine();

                    this.describePathTo(currentCandidate);
                    this.path.append(currentCandidate.getName());//-----------------------------THIS.PATH

                    target = true;
                    break;
                }else{ // being target station, if your lines's include target line
                    if(currentCandidate.getLines().contains(this.lineTarget)){
                        currentCandidate.setCurrentLine(this.lineTarget); // replace currentLine
                        this.evaluation(currentCandidate);
                        this.costPath = currentCandidate.getTravelTime();
                        this.currentLine = currentCandidate.getCurrentLine();

                        this.describePathTo(currentCandidate);
                        this.path.append(currentCandidate.getName());//-----------------------------THIS.PATH

                        target = true;
                        break;
                    }
                }
            }
        }
        return target;
    }

    private void expand(List<StationCandidate> candidates){

        for (StationCandidate candidate: candidates) {
            this.evaluation(candidate); // call Evaluation-function and assign time in structure.Station Candidate
        }
        this.sortCandidates(candidates); //sorted candidates


    }

    private void sortCandidates(List<StationCandidate> candidates){

        candidates.addAll(this.openedCandidates); //add to frontier

        Collections.sort(candidates); //sort candidates

        this.openedCandidates.removeAll(openedCandidates);
        this.openedCandidates.addAll(candidates);

        candidates.removeAll(candidates);
    }

    private void evaluation(StationCandidate candidate) {
        if(candidate.getPredStation() != null) { //source
            double realDistance = 0;
            double estimate = 0;
            double time;
            // get realDistance
            if (this.subway.getRealDistance()[candidate.getNum()][this.indexSource] != null) {
                realDistance = this.subway.getRealDistance()[candidate.getNum()][this.indexSource];
            } else if (this.subway.getRealDistance()[this.indexSource][candidate.getNum()] != null) {
                realDistance = this.subway.getRealDistance()[this.indexSource][candidate.getNum()]; // opposite way
            }
            // get estimate
            if (this.subway.getEstimates()[candidate.getNum()][this.indexTarget] != null) {
                estimate = this.subway.getEstimates()[candidate.getNum()][this.indexTarget];
            } else if (this.subway.getEstimates()[this.indexTarget][candidate.getNum()] != null) {
                estimate = this.subway.getEstimates()[this.indexTarget][candidate.getNum()]; // opposite way
            }
            time = (this.costPath + ((realDistance + estimate) / 30) +(candidate.getTransshipment() / 60));

            candidate.setRealDistance(realDistance);
            candidate.setEstimate(estimate);
            candidate.setTravelTime(time);
        }
    }

    private void checkTransshipment(StationCandidate candidate) {
        boolean transship = true;
        int transshipmentAux;

        for (String line : candidate.getLines()) {
            if(line.equals(this.currentLine)){
                candidate.setCurrentLine(line);
                transship = false;
                break;
            }
        }

        if(transship) { // exchange line need
            transshipmentAux = 4;
            candidate.setTransshipment(transshipmentAux);

            for (String line : candidate.getLines()) {
                if (candidate.getPredStation().getLines().contains(line)) { // if the predecessor's lines contain candidate's line (INTERSECTION)
                    candidate.setCurrentLine(line);
                }
            }
        }
    }

    private void describePathTo(StationCandidate candidateTarget) {
        StationCandidate pred = candidateTarget.getPredStation();

        if(pred != null) {
            this.describePathTo(pred); // regain path
            this.path.append(pred.getName()+"-> ");
        }
    }

    public StringBuilder getPath() {
        return path;
    }

    public double getCostPath() {
        return costPath;
    }
}