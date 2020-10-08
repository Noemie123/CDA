public class InverseString {

    // CHECK DONE
    public static void main(String[] arg0) {

        Utility.Print("Entrer quelque chose (pour finir, écrire '0') ");

        // save input value
        String userInput = Utility.Scan();

        // Check if input is number to later check if 0
        boolean numeric = true;

        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            numeric = false;
        }

        // check if input is 0 to stop
        if (numeric && Integer.parseInt(userInput) == 0) {
            System.out.println("Fini");
        } else {
            String[] table = userInput.split("");
            StringBuilder newResult = new StringBuilder();

            for (String a : table) {
                newResult.insert(0, a);
            }

            Utility.Print(newResult.toString());
            InverseString.main(arg0);
        }

    }

}
