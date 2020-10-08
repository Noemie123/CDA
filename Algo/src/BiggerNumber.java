public class BiggerNumber {

    // CHECK DONE
    public static void main(String[] arg0) {

        Integer biggerNum = null;


        for (int x = 0; x < 5; x++) {
            Utility.Print("Entrez un nombre (5 nombres attendus / 'stop' pour arrêter) ");
            String userInput = Utility.Scan();


            // check that input is a number
            if (Utility.checkInt(userInput)) {
                if (userInput.equals("stop")) {
                    return;
                }
                Utility.Print("Pas un chiffre");
                BiggerNumber.main(arg0);
            } else {

                if (biggerNum == null || Integer.parseInt(userInput) > biggerNum) {
                    biggerNum = Integer.parseInt(userInput);
                }
            }

        }

        Utility.Print("Le plus grand chiffre est " + biggerNum);
    }

}
