package razvan.wordpuzzlefx.app.console;

import java.io.IOException;
import java.util.*;

public class GameMethods {


    public static String getRandomWordFromList(List<String> list){
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    public static List<String> chooseTheWordsWithSpecificNumberOfLetters(List<String> list, int length) throws IOException {


        List<String> result = new ArrayList<>();
        for(String s : list){
            if(s.length() == length) {
                result.add(s);
            }
        }
        return result;
    }
    public static boolean checkersForNextWord(Set<String> list, String name1, String name2){

        if(!list.contains(name2))
        {
            System.out.println("Word is not in the dictionary!");
            return false;
        } else if (!GameMethods.checkIfSameLength(name1, name2)) {
            System.out.println("Words are not of the same length!");
            return false;
        } else if (!GameMethods.checkIfOnlyOneLetterWasChanged(name1, name2)) {
            System.out.println("Change only ONE letter!");
            return false;
        }
        return true;
    }

    public static String checkersForFirstWordString(List<String> list, String name1,  int n) {
        Set<String> errors = new HashSet<>();
        if (!list.contains(name1)) {
            errors.add("Word is not in the dictionary!");
        }
        if (!GameMethods.checkIfSameLength(name1, n)) {
            errors.add("Word does not have " + n + " number of letters!");
        }
        if (errors.isEmpty()) {
            return null; // No errors, return null to indicate success
        } else {
            return String.join("\n", errors); // Concatenate errors together with newline separator
        }
    }

    public static String checkersForNextWordString(List<String> list, String name1, String name2, int n) {
        Set<String> errors = new HashSet<>();
        if (!list.contains(name2)) {
            errors.add("Word is not in the dictionary!");
        }
        if (!GameMethods.checkIfSameLength(name1, name2)) {
            errors.add("Words are not of the same length!");
        }
        if (!GameMethods.checkIfOnlyOneLetterWasChanged(name1, name2)) {
            errors.add("Change only ONE letter!");
        }
        if (!GameMethods.checkIfSameLength(name1, n)) {
            errors.add("Word does not have " + n + " number of letters!");
        }
        if (errors.isEmpty()) {
            return null; // No errors, return null to indicate success
        } else {
            return String.join("\n", errors); // Concatenate errors together with newline separator
        }
    }

    public static boolean checkIfSameLength(String name1, String name2){
        return name1.length() == name2.length();
    }

    public static boolean checkIfSameLength(String name1, int n){
        return name1.length() == n;
    }

    public static boolean checkIfInDictionary(Set<String> list, String name){
        return list.contains(name);
    }

    public static boolean checkIfOnlyOneLetterWasChanged(String s1, String s2){
        if(checkIfSameLength(s1, s2)){
            int count = 0;
            // Compare each character of the two strings
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    count++;
                }
                // If the count exceeds one, more than one letter must have changed
                if (count > 1) {
                    return false;
                }
            }
            // If the count is exactly one, only one letter was changed
            return count == 1;
        }else {
            return false;
        }
    }



}

