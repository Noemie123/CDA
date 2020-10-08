public class Multiplication {

    // CHECK DONE
    public static void main(String[] arg0){
        System.out.println("Veuillez entrer un chiffre (pour finir, écrire 'stop') ");

        // save input value
        String userInput = Utility.Scan();

        // check that input is a number
        if (!Utility.checkInt(userInput)) {
            if (userInput.equals("stop")) {
                return;
            }
            Utility.Print("Pas un chiffre");
        } else {
            int userNumber = Integer.parseInt(userInput);

            Utility.Print("Voici la table de " + userNumber);
            for (int i = 0; i < 11; i++) {
                String ligne = userNumber + " x " + i + " = " + (i * userNumber);
                Utility.Print(ligne);
            }

        }
        Multiplication.main(arg0);
    }
}




