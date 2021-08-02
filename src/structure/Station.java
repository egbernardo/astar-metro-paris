package structure;

import java.util.List;

public class Station {

    private String name;
    private int num; //index
    private List<String> lines;

    public Station(Station station) {
        this.name = station.getName();
        this.num = station.getNum();
        this.lines = station.getLines();
    }

    public Station(String name, int num, List<String> lines) {
        this.name = name;
        this.num = num;
        this.lines = lines;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public List<String> getLines() {
        return lines;
    }
}