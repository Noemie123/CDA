import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.Collections;

public class BinaryDecimalHexa {

    public static void main(String[] arg0) {

        Utility.Print("Nombre d'entrée = Binaire (b), Decimal (d) ou Hexadecimal (h) ? ");
        String userChoice = Utility.Scan();

        if (userChoice.equals("b")) {
            Utility.Print("Entrez un nb en binaire");
            Integer userNumber1 = Utility.ScanInt();

            Utility.Print("Binaire : " + userNumber1);
            Utility.Print("Decimal : " + binToDeci(userNumber1));
            Utility.Print("Hexadecimal : " + deciToHexa(Integer.valueOf(binToDeci(userNumber1))));

        } else if (userChoice.equals("d")) {
            Utility.Print("Entrez un nb en décimal");
            Integer userNumber2 = Utility.ScanInt();

            Utility.Print("Decimal : " + userNumber2);
            Utility.Print("Binaire : " + deciToBin(userNumber2));
            Utility.Print("Hexadecimal : " + deciToHexa(userNumber2));

        } else if (userChoice.equals("h")) {
            Utility.Print("Entrez un nb en hexadecimal");
            String userNumber3 = Utility.Scan();

            Utility.Print("Hexadecimal : " + userNumber3);
            Utility.Print("Decimal : " + hexaToDeci(userNumber3));
            Utility.Print("Binaire : " + deciToBin(hexaToDeci(userNumber3)));

        } else {
            BinaryDecimalHexa.main(arg0);
        }
    }

    private static String binToDeci(Integer nb) {
        int powerTwo = 1;
        int decimal = 0;

        while (nb > 0) {
            decimal = decimal + (nb % 10) * powerTwo;
            powerTwo = 2 * powerTwo;
            nb = nb / 10;
        }

        return String.valueOf(decimal);
    }

    private static String deciToBin(Integer nb) {
        ArrayList<String> binaryArr = new ArrayList<>();
        String result = "";

        while (nb > 1) {
            int rest = nb%2;

            if (rest == 0) {
                binaryArr.add("0");
            } else {
                binaryArr.add("1");
            }
            nb = nb/2;
        }
        binaryArr.add("1");


        Collections.reverse(binaryArr);
        int arrayLength = binaryArr.size();

        for (int j = 0; j < arrayLength; j++) {
            result += binaryArr.get(j);
        }

        return result;
    }

    private static String deciToHexa (Integer nb) {
        ArrayList<String> hexaArray = new ArrayList<>();

        while (nb > 0) {
            int rest = nb % 16;

            switch (rest) {
                case 0:
                    hexaArray.add("0");
                    break;
                case 1:
                    hexaArray.add("1");
                    break;
                case 2:
                    hexaArray.add("2");
                    break;
                case 3:
                    hexaArray.add("3");
                    break;
                case 4:
                    hexaArray.add("4");
                    break;
                case 5:
                    hexaArray.add("5");
                    break;
                case 6:
                    hexaArray.add("6");
                    break;
                case 7:
                    hexaArray.add("7");
                    break;
                case 8:
                    hexaArray.add("8");
                    break;
                case 9:
                    hexaArray.add("9");
                    break;
                case 10:
                    hexaArray.add("A");
                    break;
                case 11:
                    hexaArray.add("B");
                    break;
                case 12:
                    hexaArray.add("C");
                    break;
                case 13:
                    hexaArray.add("D");
                    break;
                case 14:
                    hexaArray.add("E");
                    break;
                case 15:
                    hexaArray.add("F");
                    break;
            }

            nb = nb/16;
        }

        String result = "";

        Collections.reverse(hexaArray);
        int arrayLength = hexaArray.size();

        for (int j = 0; j < arrayLength; j++) {
            result += hexaArray.get(j);
        }

        return result;
    }


    // to be done
    private static Integer hexaToDeci (String str) {
        int length = str.length()-1;

        int correctNb = 0;
        int finalDeci = 0;
        int puissance = 0;

        for (int i = length; i >= 0; i--) {
            char letter = str.charAt(i);
            switch (letter) {
                case 'A':
                    correctNb = 10;
                    break;
                case 'B':
                    correctNb = 11;
                    break;
                case 'C':
                    correctNb = 12;
                    break;
                case 'D':
                    correctNb = 13;
                    break;
                case 'E':
                    correctNb = 14;
                    break;
                case 'F':
                    correctNb = 15;
                    break;
                default:
                    correctNb = Integer.parseInt(String.valueOf(letter));
                    break;
            }

            if (puissance == 0) {
                finalDeci += correctNb;
            } else {
                double hexaPuissance = Math.pow(16, puissance);
                finalDeci += (correctNb * hexaPuissance);
            }
            puissance++;
        }

        return finalDeci;

    }


}
