import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AgeHoroscope {

    private static String getHoroscope (String dobString) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date == null) return "0";

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH)+1;
        int day = dob.get(Calendar.DAY_OF_MONTH);

        if ((month == 3 && day > 20 ) || (month == 4 && day < 21)) {
            return "bélier";
        } else if ((month == 4 && day > 20) || month == 5 && day < 22) {
            return "taureau";
        } else if ((month == 5 && day > 21) || month == 6 && day < 22) {
            return "gémeaux";
        } else if ((month == 6 && day > 21) || month == 7 && day < 23) {
            return "cancer";
        } else if ((month == 7 && day > 22) || month == 8 && day < 23) {
            return "lion";
        } else if ((month == 8 && day > 22) || month == 9 && day < 23) {
            return "vierge";
        } else if ((month == 9 && day > 22) || month == 10 && day < 23) {
            return "balance";
        } else if ((month == 10 && day > 22) || month == 11 && day < 23) {
            return "scorpion";
        } else if ((month == 11 && day > 22) || month == 12 && day < 22) {
            return "sagittaire";
        } else if ((month == 12 && day > 21) || month == 1 && day < 21) {
            return "capricorne";
        } else if ((month == 1 && day > 20) || month == 2 && day < 19) {
            return "verseau";
        }
        return "poissons";
    }

    private static int getAge(String dobString){

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date == null) return 0;

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month+1, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);


        if (today.get(Calendar.MONTH)+1 < dob.get(Calendar.MONTH)){
            age--;
        } else if (today.get(Calendar.MONTH)+1 == dob.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        return age;
    }

    public static void main(String[] arg0) {
        Utility.Print("Comment vous appelez-vous?");
        String userInputNom = Utility.Scan();

        Utility.Print("Saisissez votre date de naissance (DD/MM/YYYY).");
        String userInputBirthdate = Utility.Scan();


        // get age
        int age = getAge(userInputBirthdate);
        String sign = getHoroscope(userInputBirthdate);
        Utility.Print("Bonjour " + userInputNom + ". Vous avez " + age + " ans. Vous êtes " + sign + ".");

    }
}
