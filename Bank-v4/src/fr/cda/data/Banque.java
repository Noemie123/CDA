package fr.cda.data;

import java.util.ArrayList;
import java.util.Scanner;

public class Banque {

    /**************************
     *                        *
     *       ATTRIBUTES       *
     *                        *
     **************************/

    private static ArrayList<Compte> listeComptes = new ArrayList<>();
    public static ArrayList<User> listeUsers = new ArrayList<>();
    public static User currentUser;


    /**************************
     *                        *
     *    GETTER & SETTER     *
     *                        *
     **************************/

    public static ArrayList<Compte> getListeComptes() {
        return listeComptes;
    }

    public static void setCurrentUser(User currentUser) {
        Banque.currentUser = currentUser;
    }

    public static void setListeComptes(ArrayList<Compte> listeComptes) {
        Banque.listeComptes = listeComptes;
    }

    public static void setListeUsers(ArrayList<User> listeUsers) {
        Banque.listeUsers = listeUsers;
    }

    public static ArrayList<Object> getListeComptesObj() {
        ArrayList<Object> arrObj = new ArrayList<>();

        for (Compte compt : listeComptes) {
            arrObj.add((Object) compt);
        }

        return arrObj;
    }

    public static ArrayList<Object> getListeCustomersObj() {
        ArrayList<Object> arrObj = new ArrayList<>();

        for (User userIn : listeUsers) {
            if (userIn instanceof Client) {
                arrObj.add((Object) userIn);
            }
        }

        return arrObj;
    }

    public static ArrayList<Object> getListeAdvisorsObj() {
        ArrayList<Object> arrObj = new ArrayList<>();

        for (User userIn : listeUsers) {
            if (userIn instanceof Conseiller) {
                arrObj.add((Object) userIn);
            }
        }

        return arrObj;
    }


}
