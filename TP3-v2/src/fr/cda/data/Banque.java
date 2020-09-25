package fr.cda.data;

import java.util.ArrayList;
import java.util.Scanner;

public class Banque {

    /**************************
     *                        *
     *       ATTRIBUTES       *
     *                        *
     **************************/

    public static ArrayList<Compte> listeComptes = new ArrayList<>();
    public static ArrayList<User> listeUsers = new ArrayList<>();
    public static User currentUser;



    /**************************
     *                        *
     *        METHODS         *
     *                        *
     **************************/

    /**
     * Method to connect - true if connection is ok, false if not
     */
    public static boolean connect(Integer userType) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Identifiant ?");
        String idUser = myObj.next();

        Integer indexUser = User.findIndexUser(idUser);

        System.out.println("Mot de passe ?");
        String mdp = myObj.next();

        // if exists
        if (indexUser != -1) {
            Banque.currentUser = Banque.listeUsers.get(indexUser);

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
