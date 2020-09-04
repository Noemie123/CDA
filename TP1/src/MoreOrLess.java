public class MoreOrLess {

    public static void main(String[] arg0) {
        Utility.Print("Devinez le nombre entre 1 et 100 (10 tentatives).");
        int userNumber = Utility.ScanInt();

        int randomNumber = Utility.Random(1, 100);
        int compt = 0;

        for (int i = 0; i < 11; i++) {
            compt++;
            if (userNumber == randomNumber) {
                Utility.Print("BRAVO !!! La bonne réponse était " + randomNumber + ". Vous avez trouvé en " + compt + " tentatives.");
                break;
            } else if (userNumber < randomNumber) {
                Utility.Print("Plus grand.");
                userNumber = Utility.ScanInt();
            } else {
                Utility.Print(" Plus petit.");
                userNumber = Utility.ScanInt();
            }
        }

    }
}
