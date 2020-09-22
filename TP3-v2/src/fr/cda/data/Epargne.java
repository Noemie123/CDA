package fr.cda.data;

public class Epargne extends Compte {
    /**
     * Attributes
     */
    protected Double tauxInteret;


    /**
     * Constructor
     */
    public Epargne (String code, Double solde, boolean activated, String identifiantUser, Double tauxInteret) {
        super(code, solde, activated, identifiantUser);
        this.tauxInteret = tauxInteret;
    }

    /**
     * Getters & Setters
     */
    public Double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(Double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}
