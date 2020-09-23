package fr.cda.data;


public class Courant extends Compte {
    /**
     * Attributes
     */
    protected Double decouvert;



    /**
     * Constructor
     */
    public Courant (String code, Double solde, boolean activated, String identifiantUser, Double decouvert) {
        super(code, solde, activated, identifiantUser);
        this.decouvert = decouvert;
    }



    /**
     * Getters & Setters
     */
    public Double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(Double decouvert) {
        this.decouvert = decouvert;
    }
}
