package search;

import structure.*;
import java.util.Stack;

public class DFS {

    private boolean[] visited;
    private Stack<Integer> stack;
    private StringBuilder path = new StringBuilder();

    public DFS(Subway subway, String checkin) {
        this.visited = new boolean[subway.getNumOfStations()];
        this.stack = new Stack<>();
        this.search(subway, checkin);
    }


    private void search(Subway subway, String check){
        int indexAux = subway.searchIndexStation(check);
        this.stack.push(indexAux);

        do {
            indexAux = this.stack.pop();

            if (indexAux != -1 &&!this.visited[indexAux]) {
                this.visited[indexAux] = true;
                this.path.append(subway.getStations().get(indexAux).getName()).append("-> ");

                for (int j = 0; j < subway.getNumOfStations(); j++) {
                    if (subway.getRealDistance()[indexAux][j] != null && !this.visited[j]) {
                        this.stack.push(j); // adjcent
                    }
                }

            }
        } while (!this.stack.isEmpty());
    }

    public StringBuilder getPath() {
        return path;
    }
}

