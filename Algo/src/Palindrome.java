import java.text.Normalizer;

public class Palindrome {

    public static void main(String[] arg0) {
        Utility.Print("Entrez un mot");
        String userWord = Utility.Scan();

        boolean palindrome = true;

        String temp = userWord;
        userWord = userWord.replaceAll("\\s", "");
        userWord = Normalizer.normalize(userWord, Normalizer.Form.NFD);
        userWord = userWord.replaceAll("\\p{M}", "");


        String[] table = userWord.split("");
        int midLength = table.length/2;


        for (int i = 0; i < midLength; i++) {
            for (int j = table.length-1; j > midLength; j--) {
                if (!table[i].equalsIgnoreCase(table[j])) {
                    palindrome = false;
                    break;
                }
                i++;
            }
        }

        if (palindrome) {
            Utility.Print("'" + temp + "' est un palindrome.");
        } else {
            Utility.Print("'" + temp + "' n'est pas un palindrome.");
        }

    }
}
