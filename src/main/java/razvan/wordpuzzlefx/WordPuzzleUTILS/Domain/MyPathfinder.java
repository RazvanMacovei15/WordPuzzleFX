package razvan.wordpuzzlefx.WordPuzzleUTILS.Domain;

import javafx.scene.layout.BorderPane;

import java.util.*;

public abstract class MyPathfinder {
    public abstract void highlightNeighbours(List<MyCircle> neighbours);

    public abstract void highlightNode(MyCircle node);

    public abstract void highlightStartNode(MyCircle node);

    public abstract void highlightEndNode(MyCircle node);

    protected abstract void highlightCurrentNode(MyCircle node);

    protected abstract void returnToBaseColor(MyCircle node);

    protected abstract void returnNeighboursToBaseColor(List<MyCircle> neighbours);

    protected abstract void highlightPath(List<MyCircle> path, BorderPane canvasBorderPane);

    public List<MyCircle> findShortestPath(MyGraph myGraph, MyCircle start, MyCircle end, BorderPane canvasBorderPane) {


        System.out.println("start thread");
        //draw start and end circles
        highlightStartNode(start);
        highlightEndNode(end);
        //queue with nodes to visit
        Queue<String> toVisitQueue = new LinkedList<>();
        //set with visited nodes
        Set<String> visitedNodes = new HashSet<>();
        //map with paths
        Map<String, List<MyCircle>> paths = new HashMap<>();
        //start the shortest path search
        paths.put(start.getWord(), List.of(start));
        //initialize the queue
        toVisitQueue.add(start.getWord());
        //initialize the set
        visitedNodes.add(start.getWord());
        while (!toVisitQueue.isEmpty()) {

            //remove the first element from the queue
            String node = toVisitQueue.remove();

            //method to highlight the circle
//            highlightCurrentNode(node);

            //if the node is the end node, return the path
            if (node.equals(end.getWord())) {

                highlightPath(paths.get(node), canvasBorderPane);

                System.out.println("found path " + paths.get(node));

                return paths.get(node);
            }
            //get the neighbours of the node
            List<MyCircle> neighbours = myGraph.getNeighbours(node);
            //method to highlight the neighbours of the node in javaFX
            highlightNeighbours(neighbours);

            for (MyCircle neighbour : neighbours) {
                //if the neighbour is already visited, continue
                if (visitedNodes.contains(neighbour.getWord())) {
                    continue;
                }
                //add the neighbour to the visited nodes set and to the queue
                visitedNodes.add(neighbour.getWord());
                toVisitQueue.add(neighbour.getWord());

                //method to highlight the circle in a yellow border and then go back to normal
                highlightNode(neighbour);

                //add the neighbour to the path
                List<MyCircle> previousPath = new ArrayList<>(paths.get(node));

                //add the neighbour to the path
                previousPath.add(neighbour);
                //put the neighbour and the path in the map
                paths.put(neighbour.getWord(), previousPath);
            }
//            returnToBaseColor(node);
            returnNeighboursToBaseColor(neighbours);
        }

        return Collections.emptyList();
    }
}
