package fr.cda.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Client extends User implements Serializable {

    /**************************
     *                        *
     *       ATTRIBUTES       *
     *                        *
     **************************/

//    protected ArrayList<Compte> listeComptesClient = new ArrayList<>();


    /**************************
     *                        *
     *      CONSTRUCTOR       *
     *                        *
     **************************/

    public Client (String surname, String firstname, String identifiant, String mdp) {
        super(surname, firstname, identifiant, mdp);
    }


    public String toString() {
        return surname + " " + firstname + ", id : " + identifiant;
    }




//    public ArrayList<Compte> getListeComptesClient() {
//        return listeComptesClient;
//    }
}
