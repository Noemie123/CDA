public class PasswordCheck {

    // CHECK DONE

    public static int luck = 0;

    public static String mdp = "Bonjour";

    public static void main(String[] arg0) {
        for (int i = 0; i < 4; i++) {
            luck++;
            Utility.Print("Entrez votre mot de passe ");

            // save input value
            String userInput = Utility.Scan();

            if (luck >= 3) {
                Utility.Print("Réponse à la question secrète ? ");

                // save input value
                String userInputAnswer = Utility.Scan();


                // secret question check
                if (userInputAnswer.equals("Minou")) {

                    Utility.Print("Réponse OK. Session ouverte. Saisissez votre nouveau mdp.");

                    // save input value
                    String userInputNewMdp1 = Utility.Scan();


                    Utility.Print("Saisissez à nouveau votre nouveau mot de passe.");

                    // save input value
                    String userInputNewMdp2 = Utility.Scan();

                    // check if new mdp instances are the same
                    if (userInputNewMdp1.equals(userInputNewMdp2)) {
                        mdp = userInputNewMdp1;
                        luck = 0;
                        PasswordCheck.main(arg0);
                    } else {
                        Utility.Print("Les mots de passe ne correspondent pas. Retour.");
                    }
                    return;

                } else {
                    Utility.Print("Réponse incorrecte");
                    return;
                }

            } else if (userInput.equals(mdp)) {
                Utility.Print("MDP OK. Session ouverte.");
                return;
            }
        }

    }
}
