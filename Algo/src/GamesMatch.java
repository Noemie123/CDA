public class GamesMatch {

    public static void main(String[] arg0) {
        Utility.Print("Jeu des alumettes. Il y a 15 alumettes. Le but est de prendre la dernière. Vous pouvez choisir d'en prendre 1, 2 ou 3.");

        int matchesNumber = 15;
        int computerNumber;

        while (matchesNumber > 0) {
            int userNumber = Utility.ScanInt();
            matchesNumber = matchesNumber - userNumber;

            Utility.Print("Reste " + matchesNumber + " alumettes.");

            if (matchesNumber <= 0) {
                Utility.Print("Vous avez gagné.");
            } else {

                if (matchesNumber <= 3) {
                    computerNumber = matchesNumber;
                } else {
                    computerNumber = Utility.Random(1, 3);
                }
                matchesNumber = matchesNumber - computerNumber;

                Utility.Print("L'ordinateur retire " + computerNumber + " alumettes. Reste " + matchesNumber + " alumettes.");

                if (matchesNumber <= 0) {
                    Utility.Print("L'ordinateur a gagné.");
                }
            }
        }


    }
}
