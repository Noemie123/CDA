import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GamesHangman {

    public static void main(String[] arg0) {
        ArrayList<String> words = new ArrayList<>();
        words.add("adresse");
        words.add("application");
        words.add("navigateur");
        words.add("langage");
        words.add("code");
        words.add("developpeur");

        ArrayList<String> lettersSent = new ArrayList<>();
        HashMap<Integer, String> wordInGame = new HashMap<>();

        int errors = 0;
        List<Integer> ind = new ArrayList<>();

        int randomNumber = Utility.Random(1, words.size());
        boolean foundWord = false;
        boolean deja = false;
        boolean correctLetter = false;



        String wordPicked = words.get(randomNumber-1);
        String[] wordPickedTable = wordPicked.split("");

        Utility.Print("Jeu du pendu. Proposez une lettre en minuscule et sans accent (12 erreurs maximum)");
        Utility.Print("Le mot est " + "_ ".repeat(wordPicked.length()));

        while (!foundWord) {
            Utility.Print("Entrez une lettre.");
            String letter = Utility.Scan();

            // check if user sent a letter
            if (letter.matches("[a-z]")) {
                Utility.Print(letter);

                // check if already sent
                for (String s : lettersSent) {
                    if (s.equals(letter)) {
                        Utility.Print("Lettre déjà donnée");
                        deja = true;
                        break;
                    }
                }

                if (!deja) {
                    lettersSent.add(letter);

                    // check if letter is in the word and if so save index in ArrayList
                    for (int m = 0; m < wordPicked.length(); m++) {
                        if (wordPickedTable[m].equals(letter)) {
                            correctLetter = true;
                            ind.add(m);

                            wordInGame.put(m, letter);
                        }
                    }

                    if (!correctLetter) {
                        errors++;
                    }

                    // reinitialise for future loops
                    correctLetter = false;
                } else {
                    errors++;
                }


                StringBuilder wordToDisplay = new StringBuilder();
                for (int p = 0; p < wordPicked.length(); p++) {
                    if (wordInGame.get(p) != null) {
                        wordToDisplay.append(wordInGame.get(p)).append(" ");
                    } else {
                        wordToDisplay.append("_ ");
                    }
                }


                // display where we at
                Utility.Print("Le mot est : " + wordToDisplay + " (" + errors + "/12)");


                // reinitialise deja for future loops
                deja = false;
            }


            // if word true + check errors
            if (errors <12) {
                if (ind.size() == wordPicked.length()) {
                    Utility.Print("BRAVO");
                    foundWord = true;
                }
            } else {
                Utility.Print("PERDU");
                return;
            }
        }

    }
}
