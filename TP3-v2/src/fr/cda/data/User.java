package fr.cda.data;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    /**
     * Attributes
     */
    protected String surname;
    protected String firstname;
    protected String identifiant;
    protected String mdp;

    public static ArrayList<User> listeUsers = new ArrayList<User>();

    /**
     * Constructor
     */
    public User (String surname, String firstname, String identifiant, String mdp) {
        this.surname = surname;
        this.firstname = firstname;
        this.identifiant = identifiant;
        this.mdp = mdp;

        listeUsers.add(this);
    }


    /**
     * Getters & Setters
     */
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public ArrayList<User> getListeUsers() {
        return listeUsers;
    }

    public void setListeUsers(ArrayList<User> listeUsers) {
        User.listeUsers = listeUsers;
    }


    /**
     * Methods
     */
    public static Integer findIndexUser(String idUser) {
        for (int i = 0; i < User.listeUsers.size(); i++) {
            if (User.listeUsers.get(i).getIdentifiant().equals(idUser)) {
                return i;
            }
        }

        return -1;
    }

    public static void createUser(Integer userType) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Nom ? (0 pour arrêter)");
        String userName = myObj.next();

        if (userName.equals("0")) {
            //TODO : find how to call main

        } else {
            System.out.println("Prénom ?");
            String userFirstname = myObj.next();

            System.out.println("Pseudo / Identifiant ?");
            String userIdentifiant = myObj.next();

            Integer indexUser = findIndexUser(userIdentifiant);

            // if id not already taken
            if (indexUser == -1) {
                System.out.println("Mdp ?");
                String userMdp = myObj.next();

                System.out.println("Répéter votre mdp ?");
                String userMdp2 = myObj.next();

                // if both passwords are the same
                if (userMdp.equals(userMdp2)) {
                    if (userType == 1) {
                        User client = new Client(userName, userFirstname, userIdentifiant, userMdp);
                    } else if (userType == 2) {
                        User conseiller = new Conseiller(userName, userFirstname, userIdentifiant, userMdp);
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
}
