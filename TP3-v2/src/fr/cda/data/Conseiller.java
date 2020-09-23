package fr.cda.data;

import java.util.ArrayList;
import java.util.Scanner;

public class Conseiller extends User {

    /**
     * Constructor
     */
    public Conseiller (String surname, String firstname, String identifiant, String mdp ) {
        super(surname, firstname, identifiant, mdp);
    }


    /**
     * Methods
     */
    public static void activeAccount() {
        boolean found = false;

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Nom du compte à activer ou désactiver ? (0 pour arrêter)");
        String accountCode = myObj.next();

        if (!accountCode.equals("0")) {
            ArrayList<Compte> listeDesComptes = Compte.listeComptes;

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


    public static void changeInterest() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Nom du compte à modifier ? (0 pour arrêter)");
        String accountCode = myObj.next();

        if (!accountCode.equals("0")) {
            Integer indexCompte = Compte.findIndexCompte(accountCode);
            if (indexCompte != -1 && Compte.listeComptes.get(indexCompte).isActivated()) {
                Compte compte = Compte.listeComptes.get(indexCompte);

                if (compte instanceof Epargne) {
                    System.out.println("Entrez le nouveau taux d'intérêts");
                    String newInterestString = myObj.next();
                    Double newInterest = null;


                    try {
                        newInterest = Double.parseDouble(newInterestString);
                    } catch (NumberFormatException ex) {
                        System.out.println("Veuillez renseigner un nombre.");
                        changeInterest();
                    }

                    if (newInterest != null) {
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

    public static void changeOverdraft() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Nom du compte à modifier ? (0 pour arrêter)");
        String accountCode = myObj.next();

        if (!accountCode.equals("0")) {
            Integer indexCompte = Compte.findIndexCompte(accountCode);
            if (indexCompte != -1 && Compte.listeComptes.get(indexCompte).isActivated()) {
                Compte compte = Compte.listeComptes.get(indexCompte);

                if (compte instanceof Courant) {
                    System.out.println("Entrez le nouveau montant du découvert autorisé");
                    String newOverdraftString = myObj.next();
                    Double newOverdraft = null;


                    try {
                        newOverdraft = Double.parseDouble(newOverdraftString);
                    } catch (NumberFormatException ex) {
                        System.out.println("Veuillez renseigner un nombre.");
                        changeOverdraft();
                    }

                    if (newOverdraft != null) {
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
