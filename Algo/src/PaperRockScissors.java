import java.util.ArrayList;

public class PaperRockScissors {

    public static void main(String[] arg0) {
        ArrayList<String> possibilities = new ArrayList<>();
        possibilities.add("Pierre");
        possibilities.add("Papier");
        possibilities.add("Ciseaux");

        String userChoice;
        int comptUser = 0;
        int comptComputer = 0;

        Utility.Print("Combien de manches ?");
        int userSets = Utility.ScanInt();



        for (int i = 0; i < userSets ; i++) {
            int randomNumber = Utility.Random(1, 3);
            String computerChoice = possibilities.get(randomNumber-1);

            Utility.Print("Vous : " + comptUser + " - Ordinateur = " + comptComputer + ". Pierre (1), Papier (2) ou ciseaux (3) ?");
            int userChoiceInt = Utility.ScanInt();

            switch (userChoiceInt) {
                case 1:
                    userChoice = "Pierre";
                    break;
                case 2:
                    userChoice = "Papier";
                    break;
                case 3:
                    userChoice = "Ciseaux";
                    break;
                default:
                    userChoice = "faux";
            }

            if (userChoice.equals("faux")) {
                Utility.Print("Mauvaise rentrée.");
                return;
            } else if (userChoice.equals(computerChoice)) {
                comptComputer++;
                comptUser++;
            } else if (userChoice.equals("Pierre")) {
                if (computerChoice.equals("Papier")) {
                    comptComputer++;
                } else {
                    comptUser++;
                }
            } else if (userChoice.equals("Papier")) {
                if (computerChoice.equals("Ciseaux")) {
                    comptComputer++;
                } else {
                    comptUser++;
                }
            } else {
                if (computerChoice.equals("Pierre")) {
                    comptComputer++;
                } else {
                    comptUser++;
                }
            }

            Utility.Print("Vous avez choisi " + userChoice + ". L'ordinateur a choisi : " + computerChoice + ".");

        }

        Utility.Print("Vous : " + comptUser + " - Ordinateur = " + comptComputer + ".");

    }

}
