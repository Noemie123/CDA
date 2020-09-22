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
        System.out.println("Nom du compte à activer ou désactiver ?");
        String accountCode = myObj.next();


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

        Scanner myObj2 = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Voulez-vous continuer ? (o pour continuer)");
        String userStop = myObj2.next();

        if (userStop.equals("o")) {
            activeAccount();
        } else {
            //TODO : find how to call main

        }
    }


    public static void changeInterest() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Nom du compte à modifier ?");
        String accountCode = myObj.next();

        Integer indexCompte = Compte.findIndexCompte(accountCode);
        if (indexCompte != -1) {
            Compte compte = Compte.listeComptes.get(indexCompte);

            if (compte instanceof Epargne) {
                System.out.println("Entrez le nouveau taux d'intérêts");
                Double newInterest = myObj.nextDouble();

                ((Epargne) compte).setTauxInteret(newInterest);
            } else {
                System.out.println("Veuillez sélectionner un compte épargne.");
                changeInterest();
            }
        } else {
            System.out.println("Compte introuvable.");
            changeInterest();
        }
    }

    public static void changeOverdraft() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Nom du compte à modifier ?");
        String accountCode = myObj.next();

        Integer indexCompte = Compte.findIndexCompte(accountCode);
        if (indexCompte != -1) {
        Compte compte = Compte.listeComptes.get(indexCompte);

            if (compte instanceof Courant) {
                System.out.println("Entrez le nouveau montant du découvert autorisé");
                Double newOverdraft = myObj.nextDouble();

                ((Courant) compte).setDecouvert(newOverdraft);
            } else {
                System.out.println("Veuillez sélectionner un compte courant.");
                changeInterest();
            }
        } else {
            System.out.println("Compte introuvable.");
            changeOverdraft();
        }
    }
}
