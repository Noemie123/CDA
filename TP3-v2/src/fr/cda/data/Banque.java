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







}
