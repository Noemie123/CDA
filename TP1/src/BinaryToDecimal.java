import java.util.ArrayList;
import java.util.Collections;

public class BinaryToDecimal {

    private static String binToDeci(int arg) {
        int temp = arg;
        int powerTwo = 1;
        int decimal = 0;

        while (arg > 0) {
            decimal = decimal + (arg % 10) * powerTwo;
            powerTwo = 2 * powerTwo;
            arg = arg / 10;
        }

        return "La valeur décimal de " + temp + " est : " + decimal + ".";
    }

    private static String deciToBin(int arg) {
        ArrayList<Integer> binaryArr = new ArrayList<>();
        String result = "";
        int temp = arg;

        if (arg == 0) {
            binaryArr.add(0);
        } else {
            while (arg > 1) {
                int rest = arg%2;

                if (rest == 0) {
                    binaryArr.add(0);
                } else {
                    binaryArr.add(1);
                }
                arg = arg/2;
            }

            binaryArr.add(1);
        }

        Collections.reverse(binaryArr);
        int arrayLength = binaryArr.size();

        for (int j = 0; j < arrayLength; j++) {
            result += binaryArr.get(j);
        }

        return "La valeur binaire de " + temp + " est : " + result + ".";
    }


    public static void main(String[] arg0) {

        Utility.Print("Binaire to Deci (1) ou Decimal to Bin (2) ?");
        String userChoice = Utility.Scan();

        // check if user sent 1 or 2
        if (Utility.checkInt(userChoice)) {
            int userChoiceInt = Integer.parseInt(userChoice);

            if (userChoiceInt == 1 || userChoiceInt == 2) {

                Utility.Print("Entrez un nombre.");
                String userNumber = Utility.Scan();

                // check if user sent a number to change
                if (!Utility.checkInt(userNumber)) {
                    BinaryToDecimal.main(arg0);
                } else {
                    int userNumberInt = Integer.parseInt(userNumber);

                    if (userChoiceInt == 1) {
                        Utility.PrintNoBreak(binToDeci(userNumberInt));
                    } else if (userChoiceInt == 2) {
                        Utility.PrintNoBreak(deciToBin(userNumberInt));
                    } else {
                        Utility.PrintNoBreak("1 ou 2 ?");
                        BinaryToDecimal.main(arg0);
                    }
                }

            } else {
                Utility.Print("1 ou 2 ?");
                BinaryToDecimal.main(arg0);

            }
        } else {
            BinaryToDecimal.main(arg0);
        }
    }

}
