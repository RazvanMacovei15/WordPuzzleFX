package razvan.wordpuzzlefx.WordPuzzleUTILS.Domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryBuilder {

    public static void main(String[] args) {
        extractAndWriteFourLetterWords("src/main/java/razvan/wordpuzzlefx/WordPuzzleUTILS/BIGDICKTIONARY.txt", "src/main/java/razvan/wordpuzzlefx/WordPuzzleUTILS/dictionaryMedium.txt");
    }

    public static void extractAndWriteFourLetterWords(String inputFileName, String outputFileName) {
        List<String> fourLetterWords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Split the line into words
                String[] words = line.split("\\s+");

                // Check each word for length 4 and add to the list if true
                for (String word : words) {
                    if (word.length() == 4) {
                        fourLetterWords.add(word);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the four-letter words to a new file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String word : fourLetterWords) {
                bw.write(word);
                bw.newLine();
            }
            System.out.println("Four-letter words written to " + outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
