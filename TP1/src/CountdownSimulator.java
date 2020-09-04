public class CountdownSimulator {

    public static void main(String[] arg0) {
        Utility.Print("Minutes ?");
        int userInputMin = Utility.ScanInt();


        if (userInputMin > 59) {
          Utility.Print("Pas plus de 59 min.");
          CountdownSimulator.main(arg0);
        } else {
            Utility.Print("Secondes ?");
            int userInputSec = Utility.ScanInt();

            if (userInputSec > 59) {
                Utility.Print("Pas plus de 59 sec.");
                CountdownSimulator.main(arg0);
            } else {
                for (int i = userInputMin; i >= 0; i--) {
                    for (int j = userInputSec; j >= 0; j--) {
                        int finalI = i;
                        int finalJ = j;
                        if (finalI == 0) {
                            Utility.setTimeoutSync(() -> System.out.println(finalJ + " sec"), 1000);
                        } else {
                            Utility.setTimeoutSync(() -> System.out.println(finalI + " min " + finalJ + " sec"), 1000);
                        }
                    }
                    userInputSec = 59;
                }
            }
        }
    }
}
