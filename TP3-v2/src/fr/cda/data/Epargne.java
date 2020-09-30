package fr.cda.data;

import java.io.Serializable;

public class Epargne extends Compte implements Serializable {

    /**************************
     *                        *
     *       ATTRIBUTES       *
     *                        *
     **************************/

    protected Double tauxInteret;




    /**************************
     *                        *
     *      CONSTRUCTOR       *
     *                        *
     **************************/

    public Epargne (String code, Double solde, boolean activated, User connectedUser, Double tauxInteret) {
        super(code, solde, activated, connectedUser);
        this.tauxInteret = tauxInteret;
    }



    /**************************
     *                        *
     *    GETTER & SETTER     *
     *                        *
     **************************/

    public Double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(Double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}
