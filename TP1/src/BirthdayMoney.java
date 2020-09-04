public class BirthdayMoney {

    // CHECK
    public static void main(String[] arg0) {

        Utility.Print("Entrez l'age de Marie pour savoir combien son grand père lui a donné d'argent.");

        // save input value
        String userNumber = Utility.Scan();


        // check that input is a number (or "stop")
        int inputInt = 0;
        try
        {
            inputInt = Integer.parseInt(userNumber);
        } catch (Exception e)
        {
            if (!userNumber.equals("stop")) {
                Utility.Print("Pas un chiffre");
                return;
            }
        }

        int sum = 0;
        for (int i = 1; i <= inputInt; i++) {
            sum = sum + 100 + (i*2);
        }

        Utility.Print("Il lui a donnée " + sum + " euros.");
    }
}
