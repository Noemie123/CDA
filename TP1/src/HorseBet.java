public class HorseBet {

    public static void main(String[] arg0) {

        //X = n ! / (n - p) !
        //Y = n ! / (p ! * (n – p) !)
        int chanceWinOrder = 0;
        int chanceWinDisorder = 0;


        Utility.Print("Nombre de chevaux au départ ?");
        int departHorses = Utility.ScanInt();

        Utility.Print("Nombre de chevaux joués ? ");
        int playedHorses = Utility.ScanInt();

        if (playedHorses > departHorses) {
            Utility.Print("Plus de chevaux joués qu'au départ.");
            HorseBet.main(arg0);
        } else {
            // in order
            chanceWinOrder = Utility.Factorial(departHorses) / (Utility.Factorial(departHorses - playedHorses));

            // disorder
            chanceWinDisorder = Utility.Factorial(departHorses) / (Utility.Factorial(playedHorses) * (Utility.Factorial(departHorses - playedHorses)));

            Utility.Print("Chances de gagner dans l'ordre : " + chanceWinOrder + ". Chances de gagner dans le désordre : " + chanceWinDisorder + ".");
        }

    }
}
