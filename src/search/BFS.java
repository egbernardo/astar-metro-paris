package search;

import structure.*;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    private boolean[] visited;
    private Queue<Integer> queue;
    private StringBuilder path = new StringBuilder();


    public BFS(Subway subway, String checkin) {
        this.visited = new boolean[subway.getNumOfStations()];
        this.queue = new LinkedList<>();
        this.search(subway, checkin);
    }

    private void search(Subway subway, String check){
        int indexAux = subway.searchIndexStation(check);
        this.queue.add(indexAux);

        do{
           indexAux = this.queue.remove();

            if(indexAux != -1 && !this.visited[indexAux]){
                this.visited[indexAux] = true;
                this.path.append(subway.getStations().get(indexAux).getName()).append("-> ");

                for (int j = 0; j < subway.getNumOfStations(); j++) {
                    if (subway.getRealDistance()[indexAux][j] != null && !this.visited[j]) {
                        this.queue.add(j);
                    }
                }
            }

        } while (!this.queue.isEmpty());
    }

    public StringBuilder getPath() {
        return path;
    }
}
