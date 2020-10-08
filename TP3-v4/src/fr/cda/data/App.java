package fr.cda.data;

import fr.cda.data.files.filesManagement.*;
import java.util.ArrayList;


public class App {

    public static void run() {
        Integer userType = Display.displayUserTypeMenu(); // select type of user menu


        if (userType == 3) {

            // write for accounts
            ArrayList<Object> arrComptesObject = Banque.getListeComptesObj();
            FilesManager.writeDownAll("comptes", arrComptesObject);


            // write for customers
            ArrayList<Object> arrClientsObj = Banque.getListeCustomersObj();
            FilesManager.writeDownAll("clients", arrClientsObj);


            // write for advisors
            ArrayList<Object> arrAdvisorsObj = Banque.getListeAdvisorsObj();
            FilesManager.writeDownAll("conseillers", arrAdvisorsObj);

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

                System.out.println(userConnected.getIdentifiant());

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
