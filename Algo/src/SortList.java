public class SortList {

    public static void main(String[] arg0) {

        // let user create data
        int[] intArray = new int[5];

        for (int i = 0; i < 5; i++) {
            Utility.Print("Entrez un nombre.");
            int userInput = Utility.ScanInt();
            intArray[i] = userInput;
        }


        // sort array
        for (int j = 0; j <4; j++) {
            int min = intArray[j];
            int indice_min = j;

            for (int k = j+1; k < 5; k++) {
                if (min > intArray[k]) {
                    min = intArray[k];
                    indice_min = k;
                }
            }

            if (j != indice_min) {
                int temp = intArray[j];
                intArray[j] = min;
                intArray[indice_min] = temp;
            }

        }

        // display
        Utility.Print(String.valueOf(intArray[0]));
        Utility.Print(String.valueOf(intArray[1]));
        Utility.Print(String.valueOf(intArray[2]));
        Utility.Print(String.valueOf(intArray[3]));
        Utility.Print(String.valueOf(intArray[4]));
    }
}
