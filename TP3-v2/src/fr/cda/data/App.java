package fr.cda.data;

public class App {

    public static void run() {
        Integer userType = Display.displayUserTypeMenu(); // select type of user menu

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
