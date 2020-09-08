import java.util.ArrayList;
import java.util.HashMap;

public class TaxTest {

// CHECK DONE

    // create array for hashmaps
    public static ArrayList<HashMap> hashList = new ArrayList<>();

    public static void main(String[] arg0) {

        // Create a HashMap object called capitalCities
        HashMap<String, String> person = new HashMap<>();


        Utility.Print("Entrez le nom ('stop' pour accéder à la liste)");
        String userName = Utility.Scan();


        if (userName.equals("stop")) {
            if (hashList.isEmpty()) {
                Utility.Print("Vous n'avez pas saisi de valeurs.");
                TaxTest.main(arg0);
            } else {
                for (HashMap perso : hashList) {
                    String genderFinal = "une femme";
                    String pronom = "elle";
                    if (perso.get("gender").equals("h")) {
                        genderFinal = "un homme";
                        pronom = "Il";
                    }

                    String taxable = " paie des impôts.";
                    if (perso.get("taxable").equals("0")) {
                        taxable = " ne paie pas d'impôts.";
                    }

                    Utility.Print(perso.get("name") + " est " + genderFinal + " de " + perso.get("age") + " ans. " + pronom + taxable + '\n');
                }
            }

        } else {

            Utility.Print("Entrez le sexe (h ou f)");
            String userSex = Utility.Scan();

            if (!userSex.equals("h") && !userSex.equals("f")) {
                TaxTest.main(arg0);
                return;
            }

            Utility.Print("Entrez l'age");
            String userAge = Utility.Scan();


            int userAgeInt = Integer.parseInt(userAge);

            // Add keys and values
            person.put("name", userName);
            person.put("gender", userSex);
            person.put("age", userAge);


            person.put("taxable", "1");
            if (userSex.equals("h") && userAgeInt >= 20) {
                Utility.Print(userName + " est imposable.");
            } else if (userSex.equals("f") && userAgeInt >= 18 && userAgeInt <= 35) {
                Utility.Print(userName + " est imposable.");
            } else {
                person.put("taxable", "0");
                Utility.Print(userName + " n'est pas imposable.");
            }

            hashList.add(person);
            TaxTest.main(arg0);

        }
    }
}
