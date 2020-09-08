public class AdditionToNum {

    // CHECK DONE
    public static void main(String[] arg0) {
        Utility.Print("Veuillez entrer un nombre positif (pour finir, écrire 'stop') ");

        // save input value
        String userNumber = Utility.Scan();

        // check that input is a number
        if (!Utility.checkInt(userNumber)) {
            if (userNumber.equals("stop")) {
                return;
            }
            Utility.Print("Pas un chiffre");
            AdditionToNum.main(arg0);
        } else {
            int inputInt = Integer.parseInt(userNumber);

            // condition to print out the table A FINIR
            if (inputInt < 0) {
                Utility.Print("Nombre positif attendu");
                AdditionToNum.main(arg0);
            } else if (inputInt == 0) {
                Utility.Print("Le résultat est 0.");
            } else {
                int resPos = 0;
                for (int i = 1; i < (inputInt + 1); i++) {
                    resPos = resPos + i;
                }
                Utility.Print("Le résultat est : " + resPos);
            }
        }
    }

}
