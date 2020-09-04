import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.Collections;

public class BinaryDecimalHexa {

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
    private static String hexaToDeci (String str) {
        Integer strToInt = null;

        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            Integer correctNb;
            if (Character.toString(letter).matches("[0-9]")) {
                correctNb = Integer.parseInt(String.valueOf(letter));
                strToInt += correctNb;
            } else {
                switch (letter) {
                    case 'A':
                        correctNb = 10;
                        strToInt += correctNb;
                        break;
                    case 'B':
                        correctNb = 11;
                        strToInt += correctNb;
                        break;
                    case 'C':
                        correctNb = 12;
                        strToInt += correctNb;
                        break;
                    case 'D':
                        correctNb = 13;
                        strToInt += correctNb;
                        break;
                    case 'E':
                        correctNb = 14;
                        strToInt += correctNb;
                        break;
                    case 'F':
                        correctNb = 15;
                        strToInt += correctNb;
                        break;
                }
            }
        }

        return " Res ? " + strToInt;

    }

    public static void main(String[] arg0) {

//        Utility.Print("Nombre d'entrée = Binaire (b), Decimal (d) ou Hexadecimal (h) ? ");
//        String userChoice = Utility.Scan();

//        Utility.Print("Entrer un nb (bin to deci)");
//        Integer userNumber1 = Utility.ScanInt();
//
//        Utility.PrintNoBreak("La valeur decimale de " + userNumber1 + " est : " + binToDeci(userNumber1) + ".");

//        Utility.Print("Entrer un nb (deci to bin)");
//        Integer userNumber2 = Utility.ScanInt();
//
//        // w/ ternary to check if user input equals 0
//        Utility.PrintNoBreak("La valeur binaire de " + userNumber2 + " est : " + (userNumber2 == 0 ? userNumber2 : deciToBin(userNumber2)) + ".");

        //        Utility.Print("Entrer un nb (deci to hexa)");
//        Integer userNumber3 = Utility.ScanInt();
//
//        Utility.PrintNoBreak("La valeur hexadécimal de " + userNumber3 + " est : " + deciToHexa(userNumber3) + ".");


//        Utility.Print("Entrer un nb (bin to hexa)");
//        Integer userNumber4 = Utility.ScanInt();
//
//        String res = deciToHexa(Integer.parseInt(binToDeci(userNumber4))) ;
//
//        Utility.PrintNoBreak("La valeur hexa de " + userNumber4 + " est : " + res + ".");

        Utility.Print("Entrer un nb (hexa to dec)");
        String userNumber5 = Utility.Scan();

        Utility.PrintNoBreak("La valeur dec de " + userNumber5 + " est : " + hexaToDeci(userNumber5) + ".");
    }
}
