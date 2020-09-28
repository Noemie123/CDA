package fr.cda.data;

import java.util.Scanner;

public class User {

    /**************************
     *                        *
     *       ATTRIBUTES       *
     *                        *
     **************************/

    protected String surname;
    protected String firstname;
    protected String identifiant;
    protected String mdp;




    /**************************
     *                        *
     *      CONSTRUCTOR       *
     *                        *
     **************************/


    public User (String surname, String firstname, String identifiant, String mdp) {
        this.surname = surname;
        this.firstname = firstname;
        this.identifiant = identifiant;
        this.mdp = mdp;

        Banque.listeUsers.add(this);
    }



    /**************************
     *                        *
     *    GETTER & SETTER     *
     *                        *
     **************************/

    public String getSurname() {
        return surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getMdp() {
        return mdp;
    }


}
