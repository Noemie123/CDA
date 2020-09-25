package fr.cda.data;


import java.util.ArrayList;

public class Client extends User {

    /**************************
     *                        *
     *       ATTRIBUTES       *
     *                        *
     **************************/

    public static ArrayList<Compte> listeComptesClient = new ArrayList<>();


    /**************************
     *                        *
     *      CONSTRUCTOR       *
     *                        *
     **************************/

    public Client (String surname, String firstname, String identifiant, String mdp ) {
        super(surname, firstname, identifiant, mdp);
    }

}
