public class PairNumber {

    // CHECK DONE
    public static void main(String[] arg0) {
        for (int i = 0; i <= 100; i++) {
            int mod = (i%2);

            if (mod == 0) {
                Utility.Print(String.valueOf(i));
            }
        }
    }
}
