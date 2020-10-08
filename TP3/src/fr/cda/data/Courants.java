package fr.cda.data;

public class Courants extends Comptes {

    /**
     * Attributes
     */
    private Double decouvert;


    /**
     * Constructor
     */
    public Courants (String code, Double solde, Double decouvert) {
        super (code, solde);
        this.decouvert = decouvert;
    }


    /**
     * Lists of getters and setters
     */
    public Double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(Double decouvert) {
        this.decouvert = decouvert;
    }
}
