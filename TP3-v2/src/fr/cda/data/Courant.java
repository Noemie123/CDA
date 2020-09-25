package fr.cda.data;


public class Courant extends Compte {

    /**************************
     *                        *
     *       ATTRIBUTES       *
     *                        *
     **************************/

    protected Double decouvert;



    /**************************
     *                        *
     *      CONSTRUCTOR       *
     *                        *
     **************************/

    public Courant (String code, Double solde, boolean activated, String identifiantUser, Double decouvert) {
        super(code, solde, activated, identifiantUser);
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
