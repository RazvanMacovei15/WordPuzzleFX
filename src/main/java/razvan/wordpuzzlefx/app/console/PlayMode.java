package razvan.wordpuzzlefx.app.console;

import razvan.wordpuzzlefx.app.domain.WordsDictionary;

import java.util.*;

public abstract class PlayMode {
    protected WordsDictionary wordsDictionary;
    protected Graph<String> graph;
    protected Scanner scanner;

    public PlayMode() {
    }

    public PlayMode(WordsDictionary wordsDictionary, Graph<String> graph, Scanner scanner)  {
        this.wordsDictionary = wordsDictionary;
        this.graph = graph;
        this.scanner =scanner;
    }
    abstract void play();

    public boolean restartGame()  {
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Do you want to keep playing?YES/NO");
            String choice2 = scan.next().toUpperCase();
            switch (choice2) {
                case "YES" -> {
                    return true;
                }
                case "NO" -> {
                    System.exit(0);
                    return false;
                }
                default -> System.out.println("Incorrect input!!! Please re-enter choice from menu");
            }
        }
    }
}