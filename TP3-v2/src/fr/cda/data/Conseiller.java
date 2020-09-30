package fr.cda.data;


import java.io.Serializable;

public class Conseiller extends User implements Serializable {

    /**************************
     *                        *
     *      CONSTRUCTOR       *
     *                        *
     **************************/

    public Conseiller (String surname, String firstname, String identifiant, String mdp ) {
        super(surname, firstname, identifiant, mdp);
    }




    public String toString() {
        return surname + " " + firstname + ", id : " + identifiant;
    }

}
