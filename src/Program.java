import search.*;
import structure.*;


import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        System.out.println("Start Program!");

        // Create subway
        Subway subway = new Subway();

        // Add stations and your lines

        // E1
        List<String> lines1 = new ArrayList<>();
        lines1.add("BLUE");
        subway.addStation("E1", lines1);

        // E2
        List<String> lines2 = new ArrayList<>();
        lines2.add("BLUE");
        lines2.add("YELLOW");
        subway.addStation("E2", lines2);

        // E3
        List<String> lines3 = new ArrayList<>();
        lines3.add("BLUE");
        lines3.add("RED");
        subway.addStation("E3", lines3);

        // E4
        List<String> lines4 = new ArrayList<>();
        lines4.add("BLUE");
        lines4.add("GREEN");
        subway.addStation("E4", lines4);

        // E5
        List<String> lines5 = new ArrayList<>();
        lines5.add("BLUE");
        lines5.add("YELLOW");
        subway.addStation("E5", lines5);

        // E6
        List<String> lines6 = new ArrayList<>();
        lines6.add("BLUE");
        subway.addStation("E6", lines6);

        // E7
        List<String> lines7 = new ArrayList<>();
        lines7.add("YELLOW");
        subway.addStation("E7", lines7);

        // E8
        List<String> lines8 = new ArrayList<>();
        lines8.add("YELLOW");
        lines8.add("GREEN");
        subway.addStation("E8", lines8);

        // E9
        List<String> lines9 = new ArrayList<>();
        lines9.add("YELLOW");
        lines9.add("RED");
        subway.addStation("E9", lines9);

        // E10
        List<String> lines10 = new ArrayList<>();
        lines10.add("YELLOW");
        subway.addStation("E10", lines10);

        // E11
        List<String> lines11 = new ArrayList<>();
        lines11.add("RED");
        subway.addStation("E11", lines11);

        // E12
        List<String> lines12 = new ArrayList<>();
        lines12.add("GREEN");
        subway.addStation("E12", lines12);

        // E13
        List<String> lines13 = new ArrayList<>();
        lines13.add("GREEN");
        lines13.add("RED");
        subway.addStation("E13", lines13);

        // E14
        List<String> lines14 = new ArrayList<>();
        lines14.add("GREEN");
        subway.addStation("E14", lines14);

        // View Stations
        for (Station s : subway.getStations()) {
            System.out.println(s.getName());
            System.out.println(s.getLines());
        };
        System.out.println();

        // Add connections
        subway.addNearStation("E1", "E2", 10);

        subway.addNearStation("E2", "E1", 10);
        subway.addNearStation("E2", "E3", 8.5);
        subway.addNearStation("E2", "E9", 10);
        subway.addNearStation("E2", "E10", 3.5);

        subway.addNearStation("E3", "E2", 8.5);
        subway.addNearStation("E3", "E4", 6.3);
        subway.addNearStation("E3", "E9", 9.4);
        subway.addNearStation("E3", "E13", 18.7);


        subway.addNearStation("E4", "E3", 6.3);
        subway.addNearStation("E4", "E5", 13);
        subway.addNearStation("E4", "E8", 15.3);
        subway.addNearStation("E4", "E13", 12.8);

        subway.addNearStation("E5", "E4", 13);
        subway.addNearStation("E5", "E6", 3);
        subway.addNearStation("E5", "E7", 2.4);
        subway.addNearStation("E5", "E8", 30);

        subway.addNearStation("E6", "E5", 3);

        subway.addNearStation("E7", "E5", 2.4);

        subway.addNearStation("E8", "E4", 15.3);
        subway.addNearStation("E8", "E5", 30);
        subway.addNearStation("E8", "E9", 9.6);
        subway.addNearStation("E8", "E12", 6.4);

        subway.addNearStation("E9", "E2", 10);
        subway.addNearStation("E9", "E3", 9.4);
        subway.addNearStation("E9", "E8", 9.6);
        subway.addNearStation("E9", "E11", 12.2);

        subway.addNearStation("E10", "E2", 3.5);

        subway.addNearStation("E11", "E9", 12.2);

        subway.addNearStation("E12", "E8", 6.4);

        subway.addNearStation("E13", "E4", 12.8);
        subway.addNearStation("E13", "E3", 18.7);
        subway.addNearStation("E13", "E14", 5.1);

        subway.addNearStation("E14", "E13", 5.1);


        System.out.println("---CONNECTIONS---");
        for (int i = 0; i < subway.getNumOfStations(); i++) {
            System.out.print(subway.getStations().get(i).getName() + ":{ ");

            for (int j = 0; j < subway.getNumOfStations(); j++) {
                if (subway.getRealDistance()[i][j] != null) {
                    System.out.print(subway.getStations().get(j).getName() + " ");
                }
            }
            System.out.print("}");
            System.out.println();
        }
        System.out.println();

        // Estimate: E1 for...
        subway.setEstimates("E1", "E2", 10);
        subway.setEstimates("E1", "E3",18.5);
        subway.setEstimates("E1", "E4", 24.8);
        subway.setEstimates("E1", "E5", 36.4);
        subway.setEstimates("E1", "E6", 38.8);
        subway.setEstimates("E1", "E7", 35.8);
        subway.setEstimates("E1", "E8", 25.4);
        subway.setEstimates("E1", "E9", 17.6);
        subway.setEstimates("E1", "E10", 9.1);
        subway.setEstimates("E1", "E11", 16.7);
        subway.setEstimates("E1", "E12", 27.3);
        subway.setEstimates("E1", "E13", 27.6);
        subway.setEstimates("E1", "E14", 29.8);

        // Estimate: E2 for...
        subway.setEstimates("E2", "E3",8.5 );
        subway.setEstimates("E2", "E4", 14.8);
        subway.setEstimates("E2", "E5", 26.6);
        subway.setEstimates("E2", "E6", 29.1);
        subway.setEstimates("E2", "E7", 26.1);
        subway.setEstimates("E2", "E8", 17.3);
        subway.setEstimates("E2", "E9", 10);
        subway.setEstimates("E2", "E10", 3.5);
        subway.setEstimates("E2", "E11", 15.5);
        subway.setEstimates("E2", "E12", 20.9);
        subway.setEstimates("E2", "E13", 19.1);
        subway.setEstimates("E2", "E14", 21.8);

        // Estimate: E3 for...
        subway.setEstimates("E3", "E4",6.3);
        subway.setEstimates("E3", "E5", 18.2);
        subway.setEstimates("E3", "E6", 20.6);
        subway.setEstimates("E3", "E7", 17.6);
        subway.setEstimates("E3", "E8", 13.6);
        subway.setEstimates("E3", "E9", 9.4);
        subway.setEstimates("E3", "E10", 10.3);
        subway.setEstimates("E3", "E11", 19.5);
        subway.setEstimates("E3", "E12", 19.1);
        subway.setEstimates("E3", "E13", 12.1);
        subway.setEstimates("E3", "E14", 16.6);

        // Estimate: E4 for...
        subway.setEstimates("E4", "E5", 12);
        subway.setEstimates("E4", "E6",14.4);
        subway.setEstimates("E4", "E7", 11.5);
        subway.setEstimates("E4", "E8",12.4);
        subway.setEstimates("E4", "E9", 12.6);
        subway.setEstimates("E4", "E10", 16.7);
        subway.setEstimates("E4", "E11", 23.6);
        subway.setEstimates("E4", "E12",18.6);
        subway.setEstimates("E4", "E13", 10.6);
        subway.setEstimates("E4", "E14", 15.4);

        // Estimate: E5 for...
        subway.setEstimates("E5", "E6",3 );
        subway.setEstimates("E5", "E7", 2.4);
        subway.setEstimates("E5", "E8", 19.4);
        subway.setEstimates("E5", "E9", 23.3);
        subway.setEstimates("E5", "E10", 28.2);
        subway.setEstimates("E5", "E11", 34.2);
        subway.setEstimates("E5", "E12", 24.8);
        subway.setEstimates("E5", "E13", 14.5);
        subway.setEstimates("E5", "E14",17.9);

        // Estimate: E6 for...
        subway.setEstimates("E6", "E7", 3.3);
        subway.setEstimates("E6", "E8", 22.3);
        subway.setEstimates("E6", "E9", 25.7);
        subway.setEstimates("E6", "E10", 30.3);
        subway.setEstimates("E6", "E11", 36.7);
        subway.setEstimates("E6", "E12", 27.6);
        subway.setEstimates("E6", "E13", 15.2);
        subway.setEstimates("E6", "E14", 18.2);

        // Estimate: E7 for...
        subway.setEstimates("E7", "E8", 20);
        subway.setEstimates("E7", "E9", 23);
        subway.setEstimates("E7", "E10", 27.3);
        subway.setEstimates("E7", "E11", 34.2);
        subway.setEstimates("E7", "E12", 25.7);
        subway.setEstimates("E7", "E13", 12.4);
        subway.setEstimates("E7", "E14", 15.6);

        // Estimate: E8 for...
        subway.setEstimates("E8", "E9", 8.2);
        subway.setEstimates("E8", "E10", 20.3);
        subway.setEstimates("E8", "E11", 16.1);
        subway.setEstimates("E8", "E12",6.4);
        subway.setEstimates("E8", "E13", 22.7);
        subway.setEstimates("E8", "E14", 27.6);

        // Estimate: E9 for...
        subway.setEstimates("E9", "E10", 13.5);
        subway.setEstimates("E9", "E11", 11.2);
        subway.setEstimates("E9", "E12", 10.9);
        subway.setEstimates("E9", "E13", 21.1);
        subway.setEstimates("E9", "E14", 26.6);

        // Estimate: E10 for...
        subway.setEstimates("E10", "E11", 17.6);
        subway.setEstimates("E10", "E12", 24.2);
        subway.setEstimates("E10", "E13", 18.7);
        subway.setEstimates("E10", "E14", 21.2);

        // Estimate: E11 for...
        subway.setEstimates("E11", "E12", 14.2);
        subway.setEstimates("E11", "E13", 31.5);
        subway.setEstimates("E11", "E14", 35.5);

        // Estimate: E12 for...
        subway.setEstimates("E12", "E13", 28.8);
        subway.setEstimates("E12", "E14", 33.6);

        // Estimate: E13 for E14
        subway.setEstimates("E13", "E14", 5.1);

        System.out.println("---ESTIMATES---");
        int numStation;
        for (int i = 0; i < subway.getNumOfStations(); i++) {
            numStation = i+1;
            System.out.print("E"+numStation);

            for (int j = 0; j < subway.getNumOfStations(); j++) {
                if(subway.getEstimates()[i][j] == null){
                    if(i == j){
                        System.out.print("   -  ");
                    }else {
                        System.out.print("     ");
                    }
                } else {
                    System.out.print(subway.getEstimates()[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("---search.DFS & search.BFS---");
        DFS searchDepth = new DFS(subway, "E2");
        System.out.println("search.DFS: "+searchDepth.getPath());

        BFS searchBreadth = new BFS(subway, "E2");
        System.out.println("search.BFS: "+searchBreadth.getPath()+"\n");

        System.out.println("****************************** A ******************************");
        search.AStar searchAStar = new search.AStar("E6","BLUE", "E13", "RED", subway); // REQUEST
//        search.AStar searchAStar = new search.AStar("E1","BLUE", "E6", "BLUE", subway); // LINE BLUE
//        search.AStar searchAStar = new search.AStar("E11","RED", "E13", "RED", subway);// LINE RED
//        search.AStar searchAStar = new search.AStar("E12","GREEN", "E14", "GREEN", subway);// LINE GREEN
//        search.AStar searchAStar = new search.AStar("E10","YELLOW", "E7", "YELLOW", subway);// LINE YELLOW, fail: find a better way
//
//        AStar searchAStar = new AStar("E1","BLUE", "E14","GREEN", subway);
//        search.AStar searchAStar = new search.AStar("E2","BLUE", "E14","GREEN", subway);
//        search.AStar searchAStar = new search.AStar("E2","YELLOW", "E14","GREEN", subway);

        // SEE PATH AND TIME A*
        System.out.println("Signal: "+searchAStar.getPath());
        System.out.println("TRAVEL TIME: "+ String.format("%.2f", searchAStar.getCostPath()) + "h");
    }
}
