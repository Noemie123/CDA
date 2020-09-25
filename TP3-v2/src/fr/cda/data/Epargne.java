package fr.cda.data;

public class Epargne extends Compte {

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
