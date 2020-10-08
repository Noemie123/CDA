package fr.cda.data;

import fr.cda.data.files.filesManagement.FilesManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Auth {

    /**************************
     *                        *
     *        METHODS         *
     *                        *
     **************************/


    /**
     * Method to create user and set his type according to choice made in first menu
     */
    public static void createUser(Integer userType) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Nom ? (0 pour arrêter)");
        String userName = myObj.next();

        if (!userName.equals("0")) {

            System.out.println("Prénom ?");
            String userFirstname = myObj.next();

            boolean checkExists = checkUserExists(userName, userFirstname, userType); // check from external file

            if (!checkExists) {
                System.out.println("Pseudo / Identifiant ?");
                String userIdentifiant = myObj.next();

                Integer indexUser = Finder.findIndexUser(userIdentifiant); // checking if id not used yet

                // if id not already taken
                if (indexUser == -1) {
                    System.out.println("Mdp ?");
                    String userMdp = myObj.next();

                    System.out.println("Répéter votre mdp ?");
                    String userMdp2 = myObj.next();

                    // if both passwords are the same
                    if (userMdp.equals(userMdp2)) { // checking if passwords are the same and then condition to create right type of user
                        if (userType == 1) {
                            new Client(userName, userFirstname, userIdentifiant, userMdp);
                            FilesManager.updateCustomersFile();
                        } else if (userType == 2) {
                            new Conseiller(userName, userFirstname, userIdentifiant, userMdp);
                            FilesManager.updateAdvisorsFile();
                        }

                    } else {
                        System.out.println("Mots de passe non identiques.");
                        createUser(userType);
                    }

                } else {
                    System.out.println("Identifiant déjà utilisé.");
                    createUser(userType);
                }
            } else {
                System.out.println("Vous avez déjà un compte.");
                createUser(userType);
            }
        }
    }




    /**
     * Method to check user exists + except if advisor wants to create a customer account
     */
    public static boolean checkUserExists(String nom, String prenom, Integer userType) {
        ArrayList<Object> userListAll = FilesManager.readAll("clients"); // retrieve data in external files
        userListAll.addAll(FilesManager.readAll("conseillers"));
        Integer compt = 0;


        for (Object userObj : userListAll) {
            User userInst = (User) userObj; // create instance from Object to User
            if (userInst.getSurname().equals(nom) && userInst.getFirstname().equals(prenom) && ((userInst instanceof Conseiller && userType == 2) || (userInst instanceof Client && userType == 1))) {
                compt++;
            }
        }


        if (compt > 0) {
            return true;
        }

        return false;
    }





    /**
     * Method to connect - true if connection is ok, false if not
     */
    public static boolean connect(Integer userType) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Identifiant ?");
        String idUser = myObj.next();

        Integer indexUser = Finder.findIndexUser(idUser); // find index from external file

        System.out.println("Mot de passe ?");
        String mdp = myObj.next();

        // if exists
        if (indexUser != -1) {
            ArrayList<Object> usersListAll = FilesManager.readAll("clients");
            usersListAll.addAll(FilesManager.readAll("conseillers"));

            Banque.currentUser = (User) usersListAll.get(indexUser);

            // if correct type
            if ((Banque.currentUser instanceof Client && userType == 1) || (Banque.currentUser instanceof Conseiller && userType == 2)) {

                // if mdp is okay
                if (Banque.currentUser.getMdp().equals(mdp)) {
                    return true;
                }

            } else {
                System.out.println("Choisissez le bon type.");
                return false;
            }
        }
        System.out.println("Identifiant incorrect.");
        return false;

    }
}
