public class ArrayMin {

    public static void main(String[] arg0) {

        int min = 0;

        // boolean to override first loop
        boolean first = true;

        for (int i = 0; i < 5; i++) {
            Utility.Print("Entrez un nombre (5 nombres demandés).");
            int userInput = Utility.ScanInt();


            if (first || min > userInput) {
                min = userInput;
            }

            first = false;
        }

        Utility.Print("Le minimum est " + min);
    }
}
