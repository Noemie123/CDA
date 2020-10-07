import fr.cda.data.*;
import fr.cda.data.files.filesManagement.FilesManager;

import java.util.ArrayList;


public class Traitement {

    public static void main(String[] arg) {

        // loading datas from external files to the app

        // accounts
        ArrayList<Object> arrComptesObjectReceived = FilesManager.readAll("comptes");
        if (!arrComptesObjectReceived.isEmpty()) {
            ArrayList<Compte> arrComptesList = new ArrayList<>();

            for (Object comptesIn : arrComptesObjectReceived) {
                Compte compte = (Compte) comptesIn;
                arrComptesList.add(compte);
            }

            Banque.setListeComptes(arrComptesList);

        }



        ArrayList<User> arrUsersList = new ArrayList<>();

        // customers
        ArrayList<Object> arrCustomersObjectReceived = FilesManager.readAll("clients");
        if (!arrCustomersObjectReceived.isEmpty()) {

            for (Object customersIn : arrCustomersObjectReceived) {
                User clientIn = (User) customersIn;
                arrUsersList.add(clientIn);
            }

        }


        // advisors
        ArrayList<Object> arrAdvisorsObjectReceived = FilesManager.readAll("conseillers");
        if (!arrAdvisorsObjectReceived.isEmpty()) {

            for (Object advisorsIn : arrAdvisorsObjectReceived) {
                User conseillerIn = (User) advisorsIn;
                arrUsersList.add(conseillerIn);
            }
        }

        Banque.setListeUsers(arrUsersList);

        App.run();
    }
}
