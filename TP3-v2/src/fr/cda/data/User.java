package fr.cda.data;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    /**************************
     *                        *
     *       ATTRIBUTES       *
     *                        *
     **************************/

    protected String surname;
    protected String firstname;
    protected String identifiant;
    protected String mdp;

    public static ArrayList<User> listeUsers = new ArrayList<>();

    public static User currUser;


    /**************************
     *                        *
     *      CONSTRUCTOR       *
     *                        *
     **************************/

    public User (String surname, String firstname, String identifiant, String mdp) {
        this.surname = surname;
        this.firstname = firstname;
        this.identifiant = identifiant;
        this.mdp = mdp;

        listeUsers.add(this);
    }



    /**************************
     *                        *
     *    GETTER & SETTER     *
     *                        *
     **************************/

    public String getSurname() {
        return surname;
    }


    public String getFirstname() {
        return firstname;
    }


    public String getIdentifiant() {
        return identifiant;
    }


    public String getMdp() {
        return mdp;
    }





    /**************************
     *                        *
     *        METHODS         *
     *                        *
     **************************/

    /**
     * Method to find index of a user in the ArrayList of all users thanks to its id
     */
    public static Integer findIndexUser(String idUser) {
        for (int i = 0; i < User.listeUsers.size(); i++) {
            if (User.listeUsers.get(i).getIdentifiant().equals(idUser)) {
                return i;
            }
        }

        return -1;
    }

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

            System.out.println("Pseudo / Identifiant ?");
            String userIdentifiant = myObj.next();

            Integer indexUser = findIndexUser(userIdentifiant); // checking if id not used yet

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
                    } else if (userType == 2) {
                        new Conseiller(userName, userFirstname, userIdentifiant, userMdp);
                    }
                } else {
                    System.out.println("Mots de passe non identiques.");
                    createUser(userType);
                }
            } else {
                System.out.println("Identifiant déjà utilisé.");
                createUser(userType);
            }
        }

    }




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
            User.currUser = User.listeUsers.get(indexUser);

            // if correct type
            if ((currUser instanceof Client && userType == 1) || (currUser instanceof Conseiller && userType == 2)) {

                // if mdp is okay
                if (currUser.getMdp().equals(mdp)) {
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
