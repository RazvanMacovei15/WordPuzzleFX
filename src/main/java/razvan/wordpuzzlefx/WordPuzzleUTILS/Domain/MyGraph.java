package razvan.wordpuzzlefx.WordPuzzleUTILS.Domain;

import java.util.*;

public class MyGraph {

    protected Map<String, List<MyCircle>> adjacencyList;

    public MyGraph(Map<String, List<MyCircle>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public void addNode(String node) {
        if (adjacencyList.containsKey(node)) {
            return;
        }
        adjacencyList.put(node, new LinkedList<>());
    }

    public void addEdge(String a, String b) {
        //add the edge between the words
        adjacencyList.get(a).add(new MyCircle(b));
        //add the edge between the words
        adjacencyList.get(b).add(new MyCircle(a));
    }

    public List<MyCircle> getNeighbours(String node) {
        return adjacencyList.get(node);
    }

}
