import java.util.ArrayList;

public class Equation {

    public static void main(String[] arg0) {

        ArrayList<Integer> resultsX = new ArrayList<>();
        ArrayList<Integer> resultsY = new ArrayList<>();

        int squareX;
        int equa;

        //x² - 2y² = 1

        for (int i = 0; i < 100; i++) {
            squareX = 1 + (2 * (i * i));
            int x = (int) Math.sqrt(squareX);

            equa = (x * x) - 2 * (i * i);
            if (equa == 1) {
                resultsX.add(x);
                resultsY.add(i);
            }
        }

        for (int j = 0; j < resultsX.size(); j++) {
            Utility.Print("x : " + resultsX.get(j) + " - y : " + resultsY.get(j));
        }

    }
}
