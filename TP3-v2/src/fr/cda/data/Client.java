package fr.cda.data;

import java.util.ArrayList;

public class Client extends User {

    /**************************
     *                        *
     *       ATTRIBUTES       *
     *                        *
     **************************/

    protected ArrayList<Compte> listeComptesClient = new ArrayList<>();


    /**************************
     *                        *
     *      CONSTRUCTOR       *
     *                        *
     **************************/

    public Client (String surname, String firstname, String identifiant, String mdp ) {
        super(surname, firstname, identifiant, mdp);
    }


    public ArrayList<Compte> getListeComptesClient() {
        return listeComptesClient;
    }
}
