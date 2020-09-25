package fr.cda.data;

import java.util.ArrayList;
import java.util.Scanner;

public class Conseiller extends User {

    /**************************
     *                        *
     *      CONSTRUCTOR       *
     *                        *
     **************************/

    public Conseiller (String surname, String firstname, String identifiant, String mdp ) {
        super(surname, firstname, identifiant, mdp);
    }



    /**************************
     *                        *
     *        METHODS         *
     *                        *
     **************************/

    /**
     * Method to activate or disactive an account
     */
    public static void activeAccount() {
        boolean found = false;

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Nom du compte à activer ou désactiver ? (0 pour arrêter)");
        String accountCode = myObj.next();

        if (!accountCode.equals("0")) {
            ArrayList<Compte> listeDesComptes = Banque.getListeComptes();

            for (Compte compt : listeDesComptes) {
                if (compt.getCode().equals(accountCode)) {
                    found = true;
                    if (!compt.isActivated()) {
                        compt.setActivated(true);
                        System.out.println("Le compte n°" + accountCode + " a été activé");
                    } else {
                        compt.setActivated(false);
                        System.out.println("Le compte n°" + accountCode + " a été désactivé");
                    }

                    break;
                }
            }

            if (!found) {
                System.out.println("Compte introuvable.");
                activeAccount();
            }
        }
    }


    /**
     * Method to change interest rate of an account - checking the account is a savings one
     */
    public static void changeInterest() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Nom du compte à modifier ? (0 pour arrêter)");
        String accountCode = myObj.next();

        if (!accountCode.equals("0")) {
            Integer indexCompte = Compte.findIndexCompte(accountCode); // checking the account exists
            if (indexCompte != -1 && Banque.getListeComptes().get(indexCompte).isActivated()) {
                Compte compte = Banque.getListeComptes().get(indexCompte);

                if (compte instanceof Epargne) { // checking the account is a savings one
                    System.out.println("Entrez le nouveau taux d'intérêts");
                    String newInterestString = myObj.next(); // receiving user answer in String
                    Double newInterest = null;


                    try { // try to parse user answer to check if Double
                        newInterest = Double.parseDouble(newInterestString);
                    } catch (NumberFormatException ex) {
                        System.out.println("Veuillez renseigner un nombre.");
                        changeInterest();
                    }

                    if (newInterest != null) { // if user answer is a Double
                        ((Epargne) compte).setTauxInteret(newInterest);
                        System.out.println("Taux d'intérêt changé.");
                    }
                } else {
                    System.out.println("Veuillez sélectionner un compte épargne.");
                    changeInterest();
                }
            } else {
                System.out.println("Compte introuvable.");
                changeInterest();
            }
        }
    }


    /**
     * Method to change overdraft rate of an account - checking the account is a current one
     */
    public static void changeOverdraft() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Nom du compte à modifier ? (0 pour arrêter)");
        String accountCode = myObj.next();

        if (!accountCode.equals("0")) {
            Integer indexCompte = Compte.findIndexCompte(accountCode); // checking the account exists
            if (indexCompte != -1 && Banque.getListeComptes().get(indexCompte).isActivated()) {
                Compte compte = Banque.getListeComptes().get(indexCompte);

                if (compte instanceof Courant) { // checking account is a current one
                    System.out.println("Entrez le nouveau montant du découvert autorisé");
                    String newOverdraftString = myObj.next(); // receiving user answer in String
                    Double newOverdraft = null;


                    try { // try to parse user answer to check if Double
                        newOverdraft = Double.parseDouble(newOverdraftString);
                    } catch (NumberFormatException ex) {
                        System.out.println("Veuillez renseigner un nombre.");
                        changeOverdraft();
                    }

                    if (newOverdraft != null) { // if user answer is a Double
                        ((Courant) compte).setDecouvert(newOverdraft);
                        System.out.println("Montant du découvert changé.");
                    }
                } else {
                    System.out.println("Veuillez sélectionner un compte courant.");
                    changeOverdraft();
                }
            } else {
                System.out.println("Compte introuvable.");
                changeOverdraft();
            }
        }
    }
}
