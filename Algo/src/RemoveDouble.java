import java.util.ArrayList;

public class RemoveDouble {

    public static void main(String[] arg0) {

        ArrayList<Integer> listInput = new ArrayList<>();
        int err = 0;

        for (int i = 0; i < 5; i++) {
            Utility.Print("Entrez un nombre.");
            int userInput = Utility.ScanInt();

            for (int inst : listInput) {
                if (inst == userInput) {
                    err = 1;
                    break;
                }
            }

            if (err == 0) {
                listInput.add(userInput);
            } else {
                err = 0;
            }
        }

        Utility.Print(String.valueOf(listInput));
    }
}
