package fr.cda.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Operations {

    /**************************
     *                        *
     *       ATTRIBUTES       *
     *                        *
     **************************/

    private Integer numero;
    private String date;
    private Double montant;
    private String type;
    private String identifiantUser;




    /**************************
     *                        *
     *      CONSTRUCTOR       *
     *                        *
     **************************/

    public Operations (Integer numero, String date, Double montant, String type, String identifiantUser) {
        this.numero = numero;
        this.date = date;
        this.montant = montant;
        this.type = type;
        this.identifiantUser = identifiantUser;
    }




    /**************************
     *                        *
     *    GETTER & SETTER     *
     *                        *
     **************************/


    public String getDate() {
        return date;
    }

    public Double getMontant() {
        return montant;
    }

    public String getType() {
        return type;
    }

    public String getIdentifiantUser() {
        return identifiantUser;
    }




    /**************************
     *                        *
     *        METHODS         *
     *                        *
     **************************/

    /**
     * Method to save operations in ArrayList of the selected account, specifying the id of the user who did the op
     */
    public static void saveOperations(Compte compteChoisi, Double montant, String type, String identifiantUser) {
        Date date = new Date();
        String today = new SimpleDateFormat("dd-MM-yyyy").format(date); // date format to European date stored as a String

        Integer operationNumber = compteChoisi.getOperationsArrayList().size()+1; // setting operation number one after the other

        Operations op = new Operations(operationNumber, today, montant, type, identifiantUser);
        compteChoisi.getOperationsArrayList().add(op);
    }

}
