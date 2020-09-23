package fr.cda.data;

import java.util.ArrayList;
import java.util.Scanner;

public class Display {

    public static Integer displayUserTypeMenu() {

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Client ?");
        System.out.println("2. Conseiller ?");

        String chosenNumberString = myObj.next();
        Integer chosenNumber = null;


        try {
            chosenNumber = Integer.parseInt(chosenNumberString);
        } catch (NumberFormatException ex) {
            System.out.println("Veuillez renseigner un nombre.");
        }

        if (chosenNumber != null) {
            if (chosenNumber == 1 || chosenNumber == 2) {
                return chosenNumber;
            }
        }
        return displayUserTypeMenu();
    }


    public static Integer displayCreateConnect() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Créer un compte utilisateur");
        System.out.println("2. Se connecter");

        String chosenNumberString2 = myObj.next();
        Integer chosenNumber2 = null;


        try {
            chosenNumber2 = Integer.parseInt(chosenNumberString2);
        } catch (NumberFormatException ex) {
            System.out.println("Veuillez renseigner un nombre.");
        }

        if (chosenNumber2 != null) {
            if (chosenNumber2 == 1 || chosenNumber2 == 2) {
                return chosenNumber2;
            }
        }
        return displayCreateConnect();
    }




    private static void displayListeCompte(Integer userType) {
        ArrayList<Compte> listeDesComptes = new ArrayList<>();

        if (userType == 1) {
            for (Compte compt : Compte.listeComptes) {
                if (compt.getIdentifiantUser().equals(User.currUser.getIdentifiant()) && compt.isActivated()) {
                    listeDesComptes.add(compt);
                }
            }
        } else if (userType == 2) {
            listeDesComptes = Compte.listeComptes;
        }

        if (listeDesComptes.isEmpty()) {
            System.out.println("Aucun compte à afficher.");
        } else {
            for (Compte compteUnit : listeDesComptes) {
                if (compteUnit instanceof Epargne) {
                    compteUnit.setSolde(compteUnit.getSolde() + (((Epargne) compteUnit).getTauxInteret() * compteUnit.getSolde()));
                }
                User accountOwner = User.listeUsers.get(User.findIndexUser(compteUnit.getIdentifiantUser()));
                System.out.println("Compte n° " + compteUnit.getCode() + ", solde : " + compteUnit.getSolde() + "€, " + (compteUnit instanceof Courant ? "Découvert autorisé : " + ((Courant) compteUnit).getDecouvert() + "€" : "Taux d'intérêts : " + ((Epargne) compteUnit).getTauxInteret() + "%") + (userType == 2 ? ", appartenant à " + accountOwner.getSurname() + " " + accountOwner.getFirstname() + ", Activé = " + (compteUnit.isActivated() ? "oui" : "non") : ""));
            }
        }
    }






    public static void displayCustomerMenu() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Créer un compte");
        System.out.println("2. Versement");
        System.out.println("3. Retrait");
        System.out.println("4. Virement");
        System.out.println("5. Consulter solde / Liste des comptes");
        System.out.println("6. Consulter la liste des opérations");
        System.out.println("7. Consulter le total des montants des versements");
        System.out.println("8. Consulter le total des montants des retraits");


        System.out.println("Choisir une option (Pour arrêter = 0)");
        String userChoiceString = myObj.next();
        Integer userChoice = null;


        try {
            userChoice = Integer.parseInt(userChoiceString);
        } catch (NumberFormatException ex) {
            System.out.println("Veuillez renseigner un nombre.");
        }

        if (userChoice != null) {

            switch (userChoice) {
                case 0:
                    App.appli();
                    break;
                case 1:
                    Compte.createAccount(User.currUser.getIdentifiant());
                    break;
                case 2:
                    Operations.versement(User.currUser.getIdentifiant());
                    break;
                case 3:
                    Operations.retrait(User.currUser.getIdentifiant());
                    break;
                case 4:
                    Operations.virement(User.currUser.getIdentifiant(), 1);
                    break;
                case 5:
                    Display.displayListeCompte(1);
                    break;
                case 6:
                    Compte.displayOperationAmount(3, 1, User.currUser.getIdentifiant());
                    break;
                case 7:
                    Compte.displayOperationAmount(1, 1, User.currUser.getIdentifiant());
                    break;
                case 8:
                    Compte.displayOperationAmount(2, 1, User.currUser.getIdentifiant());
                    break;
                default:
                    System.out.println("Ce choix n'existe pas.");
            }
        }
        displayCustomerMenu();
    }


    public static void displayAdvisorMenu() {

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Activer/désactiver un compte");
        System.out.println("2. Virement");
        System.out.println("3. Voir la Liste des comptes");
        System.out.println("4. Changer les intérêts d'un compte");
        System.out.println("5. Changer la limite du découvert d'un compte");
        System.out.println("6. Consulter la liste des opérations");

        System.out.println("Choisir une option (Pour arrêter = 0)");
        String userChoiceString = myObj.next();
        Integer userChoice = null;


        try {
            userChoice = Integer.parseInt(userChoiceString);
        } catch (NumberFormatException ex) {
            System.out.println("Veuillez renseigner un nombre.");
        }

        if (userChoice != null) {

            switch (userChoice) {
                case 0:
                    App.appli();
                    break;
                case 1:
                    Conseiller.activeAccount();
                    break;
                case 2:
                    Operations.virement(User.currUser.getIdentifiant(), 2);
                    break;
                case 3:
                    Display.displayListeCompte(2);
                    break;
                case 4:
                    Conseiller.changeInterest();
                    break;
                case 5:
                    Conseiller.changeOverdraft();
                    break;
                case 6:
                    Compte.displayOperationAmount(3, 2, User.currUser.getIdentifiant());
                    break;
                default:
                    System.out.println("Ce choix n'existe pas.");
            }
        }
        displayAdvisorMenu();
    }
}
