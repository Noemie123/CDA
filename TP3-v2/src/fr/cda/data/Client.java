package fr.cda.data;

import java.util.ArrayList;

public class Client extends User {

    /**
     * Attributes
     */
    ArrayList<Compte> userAccountList = new ArrayList<>();

    /**
     * Constructor
     */
    public Client (String surname, String firstname, String identifiant, String mdp ) {
        super(surname, firstname, identifiant, mdp);
    }


    /**
     * Getters & Setters
     */
    public ArrayList<Compte> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(ArrayList<Compte> userAccountList) {
        this.userAccountList = userAccountList;
    }
}
