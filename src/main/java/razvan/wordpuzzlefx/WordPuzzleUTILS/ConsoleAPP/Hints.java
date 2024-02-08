package razvan.wordpuzzlefx.WordPuzzleUTILS.ConsoleAPP;

public class Hints {

    public static int findFirstDiffIndex(String str1, String str2) {
        int n = Math.min(str1.length(), str2.length());
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return i;
            }
        }

        return n;
    }

    public static String replaceCharWithRed(String str, int index) {
        String redChar = "\u001B[31m" + str.charAt(index) + "\u001B[0m";
        return str.substring(0, index) + redChar + str.substring(index + 1);
    }




    public static String hintNumberOne(String s1, String s2){
        int n = findFirstDiffIndex(s1, s2);
        return replaceCharWithRed(s1,n);
    }

    public static void hintNumberTwo(String s1, String s2){
        int n = findFirstDiffIndex(s1,s2);

        if(isVowel(s2.charAt(n))){
            System.out.println("The highlighted letter will be a vowel!");

        } else  {
            System.out.println("The highlighted letter will be a consonant!");

        }

    }

    public static String hintNumberThree(String s1, String s2){
        return s2;
    }

    public static boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static boolean isConsonant(char c) {
        // Convert the character to lowercase
        c = Character.toLowerCase(c);

        // Check if the character is a letter
        if (!Character.isLetter(c)) {
            return false;
        }

        // Check if the character is a vowel
        return !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }
}
