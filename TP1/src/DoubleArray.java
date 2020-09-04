public class DoubleArray {

    private static String[][] createDoubleArray(Integer int1, Integer int2) {
        String[][] doubleArray = new String[int1][int2];

        // ask user to complete
        for (int i = 0; i < int1; i++) {
            for (int j = 0; j < int2; j++) {
                Utility.Print("ligne " + (i + 1) + " / Colonne " + (j + 1));
                String userInput = Utility.Scan();
                doubleArray[i][j] = userInput;
            }
        }
        return doubleArray;
    }



    public static void main(String[] args) {

        Utility.Print("Nombre de lignes ?");
        String lign = Utility.Scan();

        Utility.Print("Nombre de colonnes ?");
        String col = Utility.Scan();

        if (Utility.checkInt(lign) && Utility.checkInt(col)) {
            // create and fill double array by user
            String[][] doubleArr = createDoubleArray(Integer.parseInt(lign), Integer.parseInt(col));


            // to print diagonally
            if (doubleArr.length <= doubleArr[0].length) {
                for (int i = 0; i < doubleArr.length; i++) {
                    Utility.PrintNoBreak(doubleArr[i][i] + " ");
                }
            } else {
                for (int i = 0; i < doubleArr[0].length; i++) {
                    Utility.PrintNoBreak(doubleArr[i][i] + " ");
                }
            }


            // displaying horizontally or vertically
//            Utility.Print("h pour horizontal / v pour vertical");
//            String userInput = Utility.Scan();
//
//            while (!userInput.equals("h") && !userInput.equals("v")) {
//                Utility.Print("h pour horizontal / v pour vertical");
//                userInput = Utility.Scan();
//            }
//
//            for (int i = 0; i < doubleArr.length; i++) {
//                for (int j = 0; j < doubleArr[0].length; j++) {
//                    if (userInput.equals("v")) {
//                        Utility.PrintNoBreak(doubleArr[j][i] + " ");
//                    } else {
//                        Utility.PrintNoBreak(doubleArr[i][j] + " ");
//                    }
//                }
//                Utility.Print(" ");
//            }
        } else {
            DoubleArray.main(args);
        }

    }
}
