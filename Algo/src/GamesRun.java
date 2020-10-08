import java.util.ArrayList;
import java.util.HashMap;

public class GamesRun {


    public static void main(String[] arg0) {
        ArrayList<String> names = new ArrayList<String>();
        HashMap<String, Integer> positions = new HashMap<String, Integer>();

        int compt = 0;
        int max = 0;
        String saveName = "";

        // pick number of players
        Utility.Print("Nombre de joueurs ?");

        // save input value
        int userNumber = Utility.ScanInt();

        Utility.Print("Vous avez choisi " + userNumber + " joueurs ? (o pour continuer)");
        String userContinue = Utility.Scan();

        if (userContinue.equals("o")) {
            // entering names & initialise positions
            for (int i = 1; i <= userNumber; i++) {
                Utility.Print("nom du joueur " + i + " ?");
                String userName = Utility.Scan();
                names.add(userName);
                positions.put(names.get(i-1), 0);
            }


            // throwing the dice
            while (compt < 5) {
                for (int i = 0; i < userNumber; i++) {
                    int numberDice = Utility.Random(1, 6);
                    int oldNumber = positions.get(names.get(i));
                    int newNumber = numberDice + oldNumber;

                    positions.put(names.get(i), newNumber);
                }


                // result
                Utility.Print(String.valueOf(positions));
                compt++;
            }

            // finish line (10)
            for (int i = 0; i < userNumber; i++) {
                int currentNumber = positions.get(names.get(i));
                if (max < currentNumber) {
                    max = currentNumber;
                    saveName = names.get(i);
                }
            }

            Utility.Print("Le gagnant est " + saveName);

        } else {
            GamesRun.main(arg0);
        }

    }
}
