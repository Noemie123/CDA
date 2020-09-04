public class DiceThrow {


    // CHECK DONE
    public static void main(String[] arg0) {
        Utility.Print("Entrez le nombre de lancers de dés (pour finir, écrire '0') ");

        // save input value
        String userNumber = Utility.Scan();

        int uno = 0;
        int dos = 0;
        int tres = 0;
        int cuatro = 0;
        int cinco = 0;
        int seis = 0;


        // check that input is a number
        if (Utility.checkInt(userNumber)) {
            if (userNumber.equals("stop")) {
                return;
            }
            Utility.Print("Pas un chiffre");
            Multiplication.main(arg0);
        } else {

            int inputInt = Integer.parseInt(userNumber);
            if (inputInt > 0) {
                // random for dice throwing x times
                for (int i = 0; i < inputInt; i++) {

//                // 1ST version for random
//                double randomNumber = Math.random();
//                double throwing = Math.floor(randomNumber * 6 + 1);
//                long round = Math.round(throwing);
//                int roundInt = (int)round;

                    // 2ND version for random
                    int roundInt = Utility.Random(1, 6);

                    switch (roundInt) {
                        case 1:
                            uno++;
                            break;
                        case 2:
                            dos++;
                            break;
                        case 3:
                            tres++;
                            break;
                        case 4:
                            cuatro++;
                            break;
                        case 5:
                            cinco++;
                            break;
                        case 6:
                            seis++;
                            break;
                    }


                }

                Utility.Print("Le dé a été lancé " + userNumber + " fois. Il est tombé " + uno + " fois sur le 1, " + dos + " fois sur le 2, " + tres + " fois sur le 3, " + cuatro + " fois sur le 4, " + cinco + " fois sur le 5, et " + seis + " fois sur le 6. ");
            }
        }

    }
}
