package razvan.wordpuzzlefx.app.domain;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;

import java.util.*;

public abstract class MyPathfinder {
    public abstract void highlightNeighbours(List<MyCircle> neighbours);

    public abstract void highlightNode(MyCircle node);

    public abstract void highlightStartNode(MyCircle node);

    public abstract void highlightEndNode(MyCircle node);

    protected abstract void highlightCurrentNode(MyCircle node);

    protected abstract void returnToBaseColor(MyCircle node, Paint color);

    protected abstract void returnNeighboursToBaseColor(List<MyCircle> neighbours);

    protected abstract void highlightPath(List<MyCircle> path, GraphicsContext gc, Set<MyCircle> pathSet, BorderPane canvasBorderPane);

    protected abstract void checkVisitedNodes(Set<MyCircle> visitedNodes);

    public List<MyCircle> findShortestPath(MyGraph myGraph, MyCircle start, MyCircle end, GraphicsContext gc, BorderPane canvasBorderPane) {
        //draw start and end circles
        highlightStartNode(start);
        Paint startColor = start.getCircleNode().getFill();
        highlightEndNode(end);
        Paint endColor = end.getCircleNode().getFill();

        //queue with nodes to visit
        Queue<MyCircle> toVisitQueue = new LinkedList<>();
        //set with visited nodes
        Set<MyCircle> visitedNodes = new HashSet<>();
        //map with paths
        Map<MyCircle, List<MyCircle>> paths = new HashMap<>();
        //start the shortest path search
        paths.put(start, List.of(start));
        //initialize the queue
        toVisitQueue.add(start);
        //initialize the set
        visitedNodes.add(start);
        start.setVisited(true);

        checkVisitedNodes(visitedNodes);
        highlightToVisitNodes(toVisitQueue);
        while (!toVisitQueue.isEmpty()) {

            //remove the first element from the queue
            MyCircle node = toVisitQueue.remove();
            Paint nodeColor = node.getCircleNode().getFill();

            //method to highlight the circle
            highlightCurrentNode(node);

            //if the node is the end node, return the path
            if (node.equals(end)) {
                Set<MyCircle> pathSet = new HashSet<>(paths.get(node));
                highlightPath(paths.get(node), gc, pathSet, canvasBorderPane);


                return paths.get(node);
            }
            //get the neighbours of the node
            List<MyCircle> neighbours = myGraph.getNeighbours(node);
            //method to highlight the neighbours of the node in javaFX
            highlightNeighbours(neighbours);

            for (MyCircle neighbour : neighbours) {
                //if the neighbour is already visited, continue
                if (visitedNodes.contains(neighbour)) {
                    continue;
                }
                //add the neighbour to the visited nodes set and to the queue
                visitedNodes.add(neighbour);
                neighbour.setVisited(true);
                toVisitQueue.add(neighbour);


                //method to highlight the circle in a yellow border and then go back to normal
                highlightNode(neighbour);


                //add the neighbour to the path
                List<MyCircle> previousPath = new ArrayList<>(paths.get(node));

                //add the neighbour to the path
                previousPath.add(neighbour);
                //put the neighbour and the path in the map
                paths.put(neighbour, previousPath);
                highlightToVisitNodes(toVisitQueue);
            }
            revertToVisitNodes(toVisitQueue);
            if(node.equals(start)){
                returnToBaseColor(node, startColor);
            }
            else{
                returnToBaseColor(node, nodeColor);
            }
            returnNeighboursToBaseColor(neighbours);
        }

        return Collections.emptyList();
    }

    public abstract void highlightToVisitNodes(Queue<MyCircle> toVisitQueue);
    public abstract void revertToVisitNodes(Queue<MyCircle> toVisitQueue);
}
