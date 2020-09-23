package fr.cda.data;

public class App {

    public static void appli() {
        // select type of user menu
        Integer userType = Display.displayUserTypeMenu();

        // select create user account or connect
        Integer createConnectChoice = Display.displayCreateConnect();

        // if create user account
        if (createConnectChoice == 1) {
            User.createUser(userType);
            appli();

            // if user wants to connect
        } else if (createConnectChoice == 2) {
            boolean connected = User.connect(userType);


            // if connection is okay
            if (connected) {
                System.out.println("Connecté");

                // if user is a customer
                if (userType == 1) {
                    Display.displayCustomerMenu();


                    // if user is an advisor
                } else if (userType == 2) {
                    Display.displayAdvisorMenu();

                }

                // if connection not okay
            } else {
                System.out.println("Pas connecté");
                appli();
            }

            // if uncorrect choice between create user & connect
        } else {
            appli();
        }

    }
}
