package razvan.wordpuzzlefx.WordPuzzleUTILS.ConsoleAPP;

import razvan.wordpuzzlefx.WordPuzzleUTILS.ConsoleAPP.Graph;
import razvan.wordpuzzlefx.WordPuzzleUTILS.ConsoleAPP.PathFinder;
import razvan.wordpuzzlefx.WordPuzzleUTILS.ConsoleAPP.PlayMode;
import razvan.wordpuzzlefx.WordPuzzleUTILS.ConsoleAPP.WordsDictionary;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AutomaticMode extends PlayMode {
    private final PathFinder<String> pathFinder;


    public AutomaticMode(WordsDictionary dictionary, Graph<String> graph, Scanner scanner) throws IOException {
        super(dictionary, graph, scanner);
        pathFinder = new PathFinder<>();
    }

    @Override
    void play() {
        System.out.println("<<Automatic Mode>>");
        System.out.println("Choose the starting word::");
        String startWord = scanner.next().toLowerCase();
        System.out.println("Choose the ending word::");
        String end = scanner.next().toLowerCase();
        List<String> path = pathFinder.findShortestPath(graph, startWord, end);
        if (path.isEmpty()) {
            System.out.println("Not found");
        } else {
            System.out.println("Path found: " + String.join(", ", path));
        }
        restartGame();
    }
}