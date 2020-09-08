import java.util.ArrayList;

public class GamesTicTacToe {

    public static void main(String[] args) {
        //displaying rules
        Utility.Print("Vous jouez les X.");
        Utility.Print("1 | 2 | 3 ");
        Utility.Print("4 | 5 | 6 ");
        Utility.Print("7 | 8 | 9 ");

        // initializing board game
        String uno = "_";
        String dos = "_";
        String tres = "_";
        String cuatro = "_";
        String cinco = "_";
        String seis = "_";
        String siete = "_";
        String ocho = "_";
        String nueve = "_";

        ArrayList<Boolean> taken = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            taken.add(false);
        }

        boolean finish = false;
        int compt = 0;

        while (!finish) {
            // for user
            int userChoice = Utility.ScanInt();

            if (!taken.get(userChoice-1)) {
                switch (userChoice) {
                    case 1:
                        uno = "X";
                        taken.set(0, true);
                        break;
                    case 2:
                        dos = "X";
                        taken.set(1, true);
                        break;
                    case 3:
                        tres = "X";
                        taken.set(2, true);
                        break;
                    case 4:
                        cuatro = "X";
                        taken.set(3, true);
                        break;
                    case 5:
                        cinco = "X";
                        taken.set(4, true);
                        break;
                    case 6:
                        seis = "X";
                        taken.set(5, true);
                        break;
                    case 7:
                        siete = "X";
                        taken.set(6, true);
                        break;
                    case 8:
                        ocho = "X";
                        taken.set(7, true);
                        break;
                    case 9:
                        nueve = "X";
                        taken.set(8, true);
                        break;
                }
            }

            if (taken.get(0) && uno.equals(dos) && dos.equals(tres)) {
                finish = true;
                Utility.Print(uno + " a gagné.");
            }

            if (taken.get(0) && uno.equals(cinco) && cinco.equals(nueve)) {
                finish = true;
                Utility.Print(uno + " a gagné.");
            }

            if (taken.get(0) && uno.equals(cuatro) && dos.equals(siete)) {
                finish = true;
                Utility.Print(uno + " a gagné.");
            }

            if (taken.get(1) && dos.equals(cinco) && cinco.equals(ocho)) {
                finish = true;
                Utility.Print(dos + " a gagné.");
            }

            if (taken.get(3) && cuatro.equals(cinco) && cinco.equals(seis)) {
                finish = true;
                Utility.Print(cuatro + " a gagné.");
            }

            if (taken.get(2) && tres.equals(cinco) && cinco.equals(siete)) {
                finish = true;
                Utility.Print(tres + " a gagné.");
            }

            if (taken.get(2) && tres.equals(seis) && seis.equals(nueve)) {
                finish = true;
                Utility.Print(tres + " a gagné.");
            }

            if (taken.get(6) && siete.equals(ocho) && ocho.equals(nueve)) {
                finish = true;
                Utility.Print(siete + " a gagné.");
            }


            if (!finish) {
                // for computer
                int randomNumber = Utility.Random(1, 9);

                while (taken.get(randomNumber - 1)) {
                    randomNumber = Utility.Random(1, 9);
                }


                switch (randomNumber) {
                    case 1:
                        uno = "O";
                        taken.set(0, true);
                        break;
                    case 2:
                        dos = "O";
                        taken.set(1, true);
                        break;
                    case 3:
                        tres = "O";
                        taken.set(2, true);
                        break;
                    case 4:
                        cuatro = "O";
                        taken.set(3, true);
                        break;
                    case 5:
                        cinco = "O";
                        taken.set(4, true);
                        break;
                    case 6:
                        seis = "O";
                        taken.set(5, true);
                        break;
                    case 7:
                        siete = "O";
                        taken.set(6, true);
                        break;
                    case 8:
                        ocho = "O";
                        taken.set(7, true);
                        break;
                    case 9:
                        nueve = "O";
                        taken.set(8, true);
                        break;

                }

                // final print
                Utility.Print(uno + " | " + dos + " | " + tres);
                Utility.Print(cuatro + " | " + cinco + " | " + seis);
                Utility.Print(siete + " | " + ocho + " | " + nueve);




                if (taken.get(0) && uno.equals(dos) && dos.equals(tres)) {
                    finish = true;
                    Utility.Print(uno + " a gagné.");
                }

                if (taken.get(0) && uno.equals(cinco) && cinco.equals(nueve)) {
                    finish = true;
                    Utility.Print(uno + " a gagné.");
                }

                if (taken.get(0) && uno.equals(cuatro) && dos.equals(siete)) {
                    finish = true;
                    Utility.Print(uno + " a gagné.");
                }

                if (taken.get(1) && dos.equals(cinco) && cinco.equals(ocho)) {
                    finish = true;
                    Utility.Print(dos + " a gagné.");
                }

                if (taken.get(3) && cuatro.equals(cinco) && cinco.equals(seis)) {
                    finish = true;
                    Utility.Print(cuatro + " a gagné.");
                }

                if (taken.get(2) && tres.equals(cinco) && cinco.equals(siete)) {
                    finish = true;
                    Utility.Print(tres + " a gagné.");
                }

                if (taken.get(2) && tres.equals(seis) && seis.equals(nueve)) {
                    finish = true;
                    Utility.Print(tres + " a gagné.");
                }

                if (taken.get(6) && siete.equals(ocho) && ocho.equals(nueve)) {
                    finish = true;
                    Utility.Print(siete + " a gagné.");
                }
            }

            if (!finish) {
                // checking if game complete
                for (boolean take : taken) {
                    if (take) {
                        compt++;
                    }
                }

                if (compt == 9) {
                    finish = true;
                    Utility.Print("FINI");
                } else {
                    compt = 0;
                }
            }
        }
    }
}
