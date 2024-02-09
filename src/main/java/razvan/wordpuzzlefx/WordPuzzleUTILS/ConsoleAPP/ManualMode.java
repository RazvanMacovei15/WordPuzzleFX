package razvan.wordpuzzlefx.WordPuzzleUTILS.ConsoleAPP;

import razvan.wordpuzzlefx.WordPuzzleUTILS.Domain.WordsDictionary;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class ManualMode extends PlayMode{

    int countOfHints;
    int countOfSteps;
    List<String> initialMostEfficientPath;
    ArrayList<String> myPath;
    int numHints;
    List<String>mostEfficientPath;
    List<String> listOfWordsOfTheSameLength;
    double start;
    double endProcess;
    double  runtimeOfApp;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    Date obj;
    WordsDictionary dictionary = new WordsDictionary("src/main/java/razvan/wordpuzzlefx/WordPuzzleUTILS/BIGDICKTIONARY.txt");
    List<String> list = dictionary.getWords();
    PathFinder<String> pathFinder;
    public ManualMode(WordsDictionary wordsDictionary, Graph<String> graph, Scanner scanner) throws IOException {
        super(wordsDictionary, graph, scanner);
    }

    void play() {
        pathFinder = new PathFinder<>();

        //read from csv to do

        System.out.println("<<Playing Mode>>");
        start = Instant.now().toEpochMilli();
        numHints = 3;
        countOfHints=0;
        myPath = new ArrayList<>();
        System.out.print("Username: ");
        Scanner input = new Scanner(System.in);
        String username ;
        while (true) {
            try {
                username = input.next();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                input.next(); // consume the invalid input
            }
        }
        System.out.println();
//        listOfPlayerNames.add(username);
        System.out.print("Choose the number of letters for the words:: ");

        int numberOfLetters;
        while (true) {
            try {
                numberOfLetters = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                input.next(); // consume the invalid input
            }
        }
        try {
            listOfWordsOfTheSameLength = GameMethods.chooseTheWordsWithSpecificNumberOfLetters(list , numberOfLetters);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String startWord;
        countOfSteps = 0;
        boolean exit = true;
        System.out.print("Choose a starting word from the dictionary:: ");
        startWord = input.next();
        do {
            String errorsForFirstWord = GameMethods.checkersForFirstWordString(list ,startWord, numberOfLetters);
            if(errorsForFirstWord == null){

                myPath.add(startWord);
                String endWord = GameMethods.getRandomWordFromList(listOfWordsOfTheSameLength);
                System.out.println("The last word will be '" + endWord + "'");
                //to be deleted later
                initialMostEfficientPath = pathFinder.findShortestPath(graph, startWord, endWord);
                while (initialMostEfficientPath.isEmpty()) {
                    System.out.println("No path found with the initial endWord. Getting a new endWord...");
                    endWord = GameMethods.getRandomWordFromList(listOfWordsOfTheSameLength);
                    System.out.println("The new end word is '" + endWord + "'");
                    initialMostEfficientPath = pathFinder.findShortestPath(graph, startWord, endWord);
                }
                mostEfficientPath = pathFinder.findShortestPath(graph, startWord, endWord);

                System.out.println("to delete later, but for now the best path is " + mostEfficientPath);
                System.out.println();
                String nextWord = " ";
                System.out.println(Constants.MENU2);
                boolean firstIteration = true;
                while(!nextWord.equals(endWord)){
                    if (!firstIteration) {
                        System.out.println(Constants.MENU2);
                    } else {
                        firstIteration = false;
                    }
                    int choice;
                    while (true) {
                        try {
                            System.out.print("Enter your choice--->");
                            choice = input.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid choice.");
                            input.next(); // consume the invalid input
                        }
                    }
                    switch (choice) {
                        case 1 -> {
                            System.out.println("<<Playing Mode>>");
                            numHints = 3;
                            System.out.print("Next word:: ");
                            nextWord = input.next();
                            countOfSteps++;

                            while(true){
                                String error = GameMethods.checkersForNextWordString(list, startWord, nextWord, numberOfLetters);
                                if(error==null){
                                    if(!mostEfficientPath.get(1).equals(nextWord)){
                                        mostEfficientPath = pathFinder.findShortestPath(graph, nextWord, endWord);
                                        System.out.println(mostEfficientPath);//doar pt teste
                                    } else {
                                        mostEfficientPath.remove(0);
                                        System.out.println(mostEfficientPath);//doar pt teste
                                    }
                                    System.out.println("Word '" + nextWord + "' added to the path.");
                                    myPath.add(nextWord);
                                    startWord = nextWord;
                                    break;
                                }else{
                                    System.out.println("Error: " + error);
                                    System.out.println("Choose the next word again!");
                                    nextWord = input.next();
                                }
                            }
                        }
                        case 2 -> {

                            if (numHints > 0) {
                                hint(startWord);
                                numHints--;
                            } else {
                                System.out.println("No more hints allowed!");
                                System.out.println("to delete later, but for now the best path is " + mostEfficientPath);
                            }
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                }
                endProcess = Instant.now().toEpochMilli();
                double runtimeOfApp2 = endProcess - start;
                runtimeOfApp = runtimeOfApp2/60000;
                System.out.println("took " + (runtimeOfApp2) + " millis");
                obj = new Date();
                System.out.println(dateFormat.format(obj));
                System.out.println("<<<GAME OVER>>>");
                System.out.println();
                System.out.printf("%.2f", runtimeOfApp);

                if(restartGame()){
                    return;
                } else {
                    exit = false;
                }
            } else {
                System.out.println("Errors: " + errorsForFirstWord);
                System.out.println("Give me a valid starting word!");
                startWord = input.next();
            }
        } while (exit);


    }

    public void hint(String startWord){

        System.out.println("<<Playing Mode>>");

        countOfHints++;
        countOfSteps++;

        if(numHints > 1) {
            int hintsLeft = numHints -1;
            System.out.println("You have " + hintsLeft + " more hint(s) left");

        } else {
            System.out.println("You have 0 hints left");
        }

        String nextHint = mostEfficientPath.get(1);
        String hintForNextWord = Hints.hintNumberOne(startWord, nextHint);
        System.out.println("Letter that needs to be changed for " + startWord + " is " + hintForNextWord);

        if(numHints == 2) {
            Hints.hintNumberTwo(startWord, nextHint);

        }
        else if(numHints == 1) {
            System.out.println("Next word is " + Hints.hintNumberThree(startWord, nextHint));
        }
    }
}