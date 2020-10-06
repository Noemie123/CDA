package fr.cda.data;


import java.io.Serializable;

public class Courant extends Compte implements Serializable  {

    /**************************
     *                        *
     *       ATTRIBUTES       *
     *                        *
     **************************/

    private Double decouvert;



    /**************************
     *                        *
     *      CONSTRUCTOR       *
     *                        *
     **************************/

    public Courant (String code, Double solde, boolean activated, User connectedUser, Double decouvert) {
        super(code, solde, activated, connectedUser);
        this.decouvert = decouvert;
    }



    /**************************
     *                        *
     *    GETTER & SETTER     *
     *                        *
     **************************/

    public Double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(Double decouvert) {
        this.decouvert = decouvert;
    }
}
