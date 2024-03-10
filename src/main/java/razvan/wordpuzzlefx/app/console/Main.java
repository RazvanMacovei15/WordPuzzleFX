package razvan.wordpuzzlefx.app.console;

import razvan.wordpuzzlefx.app.domain.WordsDictionary;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        WordsDictionary dictionary = new WordsDictionary("src/main/java/razvan/wordpuzzlefx/WordPuzzleUTILS/dictionaryShort.txt");

        Graph<String> graph = Graph.buildGraphFromWordsDictionary(dictionary);

        initializePlayModes(dictionary, graph, scanner);
    }

    public static <T> void initializePlayModes(WordsDictionary dictionary, Graph<String> graph, Scanner scanner) throws IOException {

        Map<Integer, PlayMode> playModes = new HashMap<>();
        playModes.put(0, new ExitMode());
        playModes.put(1, new AutomaticMode(dictionary, graph, scanner));
        playModes.put(2, new ManualMode(dictionary, graph, scanner));

        boolean keepPlaying = true;
        while (keepPlaying) {
            try {
                System.out.println(Constants.MENU);
                int choice = scanner.nextInt();
                PlayMode playMode = playModes.get(choice);
                if (playMode != null) {
                    playMode.play();
                } else {
                    System.out.println("Incorrect input!!! Please re-enter choice from menu");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.next(); // consume the invalid input
            }
            System.out.println("Do you want to keep playing? YES/NO");
            String choice2 = scanner.next().toUpperCase();
            try{
                if (!choice2.equals("YES")) {
                    keepPlaying = false;
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.next(); // consume the invalid input
            }
        }
    }
}