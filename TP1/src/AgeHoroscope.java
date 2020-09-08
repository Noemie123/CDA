import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AgeHoroscope {

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

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }



        return age;
    }

    public static void main(String[] arg0) {
        Utility.Print("Saisissez votre date de naissance (DD/MM/YYYY).");
        String userInputBirthdate = Utility.Scan();

        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);

        Date todayDate = new Date(System.currentTimeMillis());

        int userAge = getAge(userInputBirthdate);

        Utility.Print("Aujourd'hui nous sommes le " + formatter.format(todayDate));
        Utility.Print("Vous êtes né le " + userInputBirthdate + ". Vous avez " + userAge + " ans.");
    }
}
