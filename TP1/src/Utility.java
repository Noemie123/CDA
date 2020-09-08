import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Utility {

    public static void Print(String arg) {
        System.out.println(arg);
    }

    public static void Print(HashMap<Integer, String> arg) {
        System.out.println(arg);
    }

    public static void PrintNoBreak(String arg) {
        System.out.print(arg);
    }

    public static String Scan() {
        Scanner objScanner = new Scanner(System.in).useDelimiter("\n");
        return objScanner.next();
    }

    public static Integer ScanInt() {
        Scanner objScanner = new Scanner(System.in).useDelimiter("\n");
        return objScanner.nextInt();
    }

    public static boolean checkInt(String Arg) {
        try
        {
           Integer.parseInt(Arg);
        } catch (Exception e)
        {
            return false;
        }

        return true;

    }

    public static int Random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static void setTimeoutSync(Runnable runnable, int delay) {
        try {
            Thread.sleep(delay);
            runnable.run();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }

    public static int Factorial (int arg) {
        if (arg == 0) {
            return(1);
        }

        return(arg*Utility.Factorial(arg-1));
    }
}
