package fr.cda.data;

public class Epargnes extends Comptes {

    /**
     * Attributes
     */
    private Double tauxInteret;


    /**
     * Constructor
     */
    public Epargnes (String code, Double solde, Double tauxInteret) {
        super (code, solde);
        this.tauxInteret = tauxInteret;
    }


    /**
     * Lists of getters and setters
     */
    public Double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(Double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}
