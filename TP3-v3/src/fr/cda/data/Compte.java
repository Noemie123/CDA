package fr.cda.data;

import java.io.Serializable;
import java.util.ArrayList;


public class Compte implements Serializable {

    /**************************
     *                        *
     *       ATTRIBUTES       *
     *                        *
     **************************/

    protected String code;
    protected Double solde;
    protected boolean activated;
    protected User owner;


    protected ArrayList<Operations> operationsArrayList = new ArrayList<>();




    /**************************
     *                        *
     *      CONSTRUCTOR       *
     *                        *
     **************************/

    public Compte (String code, Double solde, boolean activated, User owner) {
        this.code = code;
        this.solde = solde;
        this.activated = activated;
        this.owner = owner;
        Banque.getListeComptes().add(this);
    }


    public String toString() {
        return "Compte " + (this instanceof Courant ? "courant" : "épargne") + " n°" + code + ", solde : " + solde + (this instanceof Courant ? (", découvert autorisé " + ((Courant) this).getDecouvert()) : (", taux d'intérêts : " + ((Epargne)this).getTauxInteret())) + ", activé : " + (activated? "oui" : "non") + ", appartenant à : " + owner.getFirstname() + " " + owner.getSurname();
    }






    /**************************
     *                        *
     *    GETTER & SETTER     *
     *                        *
     **************************/

    public String getCode() {
        return code;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Operations> getOperationsArrayList() {
        return operationsArrayList;
    }


}
