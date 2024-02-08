package razvan.wordpuzzlefx.WordPuzzleUTILS;

import java.util.*;

public class PathFinder<T> {
    public List<T> findShortestPath(Graph<T> graph, T start, T end) {
        Queue<T> toVisitQueue = new LinkedList<>();
        Set<T> visitedNodes = new HashSet<>();
        Map<T, List<T>> paths = new HashMap<>();
        paths.put(start, List.of(start));
        toVisitQueue.add(start);
        visitedNodes.add(start);

        while (!toVisitQueue.isEmpty()) {
            System.out.println();
            System.out.println();
            System.out.println("toVisitQueue: " + toVisitQueue);
            T node = toVisitQueue.remove();
            System.out.println("node: '" + node + "' was removed from toVisitQueue");
            if (node.equals(end)) {
                System.out.println("found the end node: '" + end + "'");
                return paths.get(node);
            }
            List<T> neighbours = graph.getNeighbours(node);
            System.out.println("neighbours: " + neighbours + " for node: '" + node + "'");
            for (T neighbour : neighbours) {
                System.out.println();
                System.out.println("neighbour: " + neighbour);
                if (visitedNodes.contains(neighbour)) {
                    System.out.println("visitedNodes.contains(neighbour): " + visitedNodes.contains(neighbour));
                    continue;
                }
                visitedNodes.add(neighbour);
                toVisitQueue.add(neighbour);
                System.out.println("added '" + neighbour + "' to toVisitQueue");
                System.out.println("toVisitQueue: " + toVisitQueue);
                System.out.println("added '" + neighbour + "' to visitedNodes");
                System.out.println("visitedNodes: " + visitedNodes);
                List<T> previousPath = new ArrayList<>(paths.get(node));
                previousPath.add(neighbour);
                System.out.println("previousPath: " + previousPath);
                paths.put(neighbour, previousPath);
            }
        }
        return Collections.emptyList();
    }
}
