package razvan.wordpuzzlefx.app.domain;

import java.util.*;

public class MyGraph {

    protected Map<MyCircle, List<MyCircle>> adjacencyList;

    public MyGraph(Map<MyCircle, List<MyCircle>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public void addNode(MyCircle node) {
        if (adjacencyList.containsKey(node)) {
            return;
        }
        adjacencyList.put(node, new LinkedList<>());
    }

    public void addEdge(MyCircle a, MyCircle b) {
        //add the edge between the words
        adjacencyList.get(a).add(b);
        //add the edge between the words
        adjacencyList.get(b).add(a);
    }

    public List<MyCircle> getNeighbours(MyCircle node) {
        return adjacencyList.get(node);
    }

}
