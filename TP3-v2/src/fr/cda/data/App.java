package fr.cda.data;

import fr.cda.data.files.filesManagement.*;
import java.util.ArrayList;


public class App {

    public static void run() {
        Integer userType = Display.displayUserTypeMenu(); // select type of user menu

        if (userType == 3) {
            // write & read for accounts
            ArrayList<Object> arrComptesObject = Banque.getListeComptesObj();
            FilesManager.writeDownAll("comptes", arrComptesObject);

            ArrayList<Object> arrComptesObjectReceived = FilesManager.readAll("comptes");
            if (!arrComptesObjectReceived.isEmpty()) {
                for (Object comptesIn : arrComptesObjectReceived) {
                    Compte compte = (Compte) comptesIn;
                    System.out.println("Compte n°" + compte.code);
                }
            } else {
                System.out.println("Liste comptes vide.");
            }


            // write & read for customers
            ArrayList<Object> arrClientsObj = Banque.getListeCustomersObj();
            FilesManager.writeDownAll("clients", arrClientsObj);

            ArrayList<Object> arrCustomersObjectReceived = FilesManager.readAll("clients");
            if (!arrCustomersObjectReceived.isEmpty()) {
                for (Object customersIn : arrCustomersObjectReceived) {
                    Client clientIn = (Client) customersIn;
                    System.out.println("Nom du client : " + clientIn.firstname + " " + clientIn.surname);
                }
            } else {
                System.out.println("Liste clients vide.");
            }




            // write & read for advisors
            ArrayList<Object> arrAdvisorsObj = Banque.getListeAdvisorsObj();
            FilesManager.writeDownAll("conseillers", arrAdvisorsObj);

            ArrayList<Object> arrAdvisorsObjectReceived = FilesManager.readAll("conseillers");
            if (!arrAdvisorsObjectReceived.isEmpty()) {
                for (Object advisorsIn : arrAdvisorsObjectReceived) {
                    Conseiller conseillerIn = (Conseiller) advisorsIn;
                    System.out.println("Nom du conseiller " + conseillerIn.firstname + " " + conseillerIn.surname);
                }
            } else {
                System.out.println("Liste conseillers vide.");
            }

            App.run();
        }

        Integer createConnectChoice = Display.displayCreateConnect(); // select create user account or connect


        if (createConnectChoice == 1) { // if create user account
            Auth.createUser(userType);
            run();

        } else if (createConnectChoice == 2) {  // if user wants to connect
            boolean connected = Auth.connect(userType);


            if (connected) { // if connection is okay
                System.out.println("Connecté");
                User userConnected = Banque.currentUser;

                if (userType == 1) {  // if user is a customer
                    Display.displayCustomerMenu(userConnected);
                } else if (userType == 2) { // if user is an advisor
                    Display.displayAdvisorMenu(userConnected);
                }


            } else {  // if connection not okay
                System.out.println("Pas connecté");
                run();
            }

        } else { // if uncorrect choice between create user & connect, return to base
            run();
        }

    }
}
