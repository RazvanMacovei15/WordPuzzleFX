package razvan.wordpuzzlefx.WordPuzzleUTILS.ConsoleAPP;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph<T> {

    protected   Map<T, List<T>> adjacencyList;

    //constructor for the Graph<T>
    public Graph(Map<T, List<T>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public Map<T, List<T>> getAdjacencyList() {
        return adjacencyList;
    }

    public static Graph<String> buildGraphFromWordsDictionary(WordsDictionary wordsDictionary) {
        System.out.println();
        System.out.println();
        System.out.println("-------DEBUGGING GRAPH-------");

        Map<String, List<String>> nodesByWildcard = wordsDictionary.getWordsGroupedByWildcard();
        System.out.println("nodesByWildcard: " + nodesByWildcard);
        Graph<String> graph = new Graph<>(nodesByWildcard);
        //copy the map
        Map<String, List<String>> nodesByWildcardCopy = new HashMap<>(nodesByWildcard);
        //get the values of the map
        Collection<List<String>> wildcardValues = nodesByWildcardCopy.values();

        for (List<String> neighbouringWords : wildcardValues) {
            //iterate through the values
            for (String word : neighbouringWords) {
                //iterate through the words
                graph.addNode(word);
                System.out.print(word);
            }
            System.out.println();
            for (int i = 0; i < neighbouringWords.size() - 1; i++) {
                System.out.println();
                System.out.println();
                //iterate through the words
                String a = neighbouringWords.get(i);
                //iterate through the words
                for (int j = i + 1; j < neighbouringWords.size(); j++) {
                    //iterate through the words
                    String b = neighbouringWords.get(j);
                    //add the edge between the words
                    graph.addEdge(a, b);
                    System.out.println("added edge between '" + a + "' and '" + b + "'");
                }
                System.out.println("neighbours of " + a + " are " + graph.getNeighbours(a));
            }
        }
        System.out.println("-------DEBUGGING GRAPH-------");
        return graph;
    }

    public void addNode(T node) {
        if (adjacencyList.containsKey(node)) {
            return;
        }
        adjacencyList.put(node, new LinkedList<>());
    }

    public void addEdge(T a, T b) {
        //add the edge between the words
        adjacencyList.get(a).add(b);
        //add the edge between the words
        adjacencyList.get(b).add(a);
    }

    public List<T> getNeighbours(T node) {
        return adjacencyList.get(node);
    }

    public void countVertices() {
        System.out.println("Total number of vertices: " + adjacencyList.keySet().size());
    }

    public void countEdges(boolean bidirectional) {
        double count = 0;
        for (T v : adjacencyList.keySet()) {
            count += adjacencyList.get(v).size();
        }
        if (bidirectional) {
            count = count / 2;
        }
        System.out.println("total number of edges: " + count);
    }

    public void containsVertex(T node) {
        if (adjacencyList.containsKey(node)) {
            System.out.println("the graph contains '" + node + "' as a vertex");
        } else {
            System.out.println("the graph does not contain '" + node + "' as a vertex");
        }
    }

    public void containsEdge(T node1, T node2) {
        if (adjacencyList.get(node1).contains(node2)) {
            System.out.println("The graph has an edge between " + node1 + " and " + node2 + ".");
        } else {
            System.out.println("There is no edge between " + node1 + " and " + node2 + ".");
        }
    }

    public static void main(String[] args) throws IOException {
        buildGraphFromWordsDictionary(new WordsDictionary("src/main/java/razvan/wordpuzzlefx/WordPuzzleUTILS/dictionaryMedium.txt"));
    }
}
