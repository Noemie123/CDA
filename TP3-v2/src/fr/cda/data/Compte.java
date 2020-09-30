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
