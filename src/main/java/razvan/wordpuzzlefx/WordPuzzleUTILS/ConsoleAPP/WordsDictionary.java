package razvan.wordpuzzlefx.WordPuzzleUTILS.ConsoleAPP;

import razvan.wordpuzzlefx.WordPuzzleUTILS.Domain.MyCircle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordsDictionary {
    private final List<String> words;
    private Map<String, MyCircle> myCirclesMap;

    public WordsDictionary(String dictionaryPath) throws IOException{
        this.words = Files.readAllLines(Paths.get(dictionaryPath));
        this.myCirclesMap = new HashMap<>();
    }

    public Map<String, MyCircle> getMyCirclesMap() {
        return myCirclesMap;
    }

    public List<String> getWords() {
        return words;
    }

    public Map<String, List<String>> getWordsGroupedByWildcard() {

        //empty map
        Map<String, List<String>> neighbouringNodesOfWildcard = new HashMap<>();
        for (String word : words) {
            //iterate through the words
            for (int i = 0; i < word.length(); i++) {

                String wildCard = word.substring(0, i) + "*" + word.substring(i + 1);
                if (!neighbouringNodesOfWildcard.containsKey(wildCard)) {
                    neighbouringNodesOfWildcard.put(wildCard, new ArrayList<>());
                }
                List<String> nodesForWildcard = neighbouringNodesOfWildcard.get(wildCard);
                nodesForWildcard.add(word);

            }
        }
        return neighbouringNodesOfWildcard;
    }

    public Map<MyCircle, List<MyCircle>> getWordsGroupedByWildcard2() {
        Map<MyCircle, List<MyCircle>> neighbouringNodesOfWildcard = new HashMap<>();

        for (String word : words) {
            MyCircle wordCircle = new MyCircle(word);
            myCirclesMap.put(word, wordCircle);
            for (int i = 0; i < word.length(); i++) {
                String wildCardString = word.substring(0, i) + "*" + word.substring(i + 1);
                MyCircle wildCardCircle = new MyCircle(wildCardString);
                if (!neighbouringNodesOfWildcard.containsKey(wildCardCircle)) {
                    neighbouringNodesOfWildcard.put(wildCardCircle, new ArrayList<>());
                }
                List<MyCircle> nodesForWildcard = neighbouringNodesOfWildcard.get(wildCardCircle);
                nodesForWildcard.add(wordCircle);
            }
        }
        return neighbouringNodesOfWildcard;
    }
}
