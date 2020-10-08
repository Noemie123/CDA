package fr.cda.data;

import fr.cda.data.files.filesManagement.FilesManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Display {

    /**
     * Method to display menu about user types : customer or advisor
     */
    public static Integer displayUserTypeMenu() {

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Client ?");
        System.out.println("2. Conseiller ?");

        String chosenNumberString = myObj.next(); // receiving user answer in String
        Integer chosenNumber = null;


        try { // try to parse user answer to check if Integer
            chosenNumber = Integer.parseInt(chosenNumberString);
        } catch (NumberFormatException ex) {
            System.out.println("Veuillez renseigner un nombre.");
        }

        if (chosenNumber != null && chosenNumber > 0 && chosenNumber < 3) { // if user answer is an Integer
            switch (chosenNumber) {
                case 1:
                    System.out.println("Vous avez choisi Client");
                    break;
                case 2:
                    System.out.println("Vous avez choisi Conseiller");
            }

                return chosenNumber;

        }

        return displayUserTypeMenu(); // recursive
    }

    /**
     * Method to display menu to choose between create user or connect
     */
    public static Integer displayCreateConnect() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Créer un compte utilisateur");
        System.out.println("2. Se connecter");
        System.out.println("0 pour revenir en arrière");

        String chosenNumberString2 = myObj.next(); // receiving user answer in String
        Integer chosenNumber2 = null;

        if (!chosenNumberString2.equals("0")) {

            try { // try to parse user answer to check if Integer
                chosenNumber2 = Integer.parseInt(chosenNumberString2);
            } catch (NumberFormatException ex) {
                System.out.println("Veuillez renseigner un nombre.");
            }

            if (chosenNumber2 != null) { // if user answer is an Integer
                if (chosenNumber2 == 1 || chosenNumber2 == 2) {
                    return chosenNumber2;
                }
            }

            return displayCreateConnect(); // recursive
        } else {
            App.run();
        }

        return displayUserTypeMenu();
    }



    /**
     * Method to display list of accounts according to type of current user
     */
    private static void displayListeCompte(User connectedUser) {
        ArrayList<Object> listeDesComptes = new ArrayList<>();


        if (connectedUser instanceof Client) { // if user is a customer - select only his accounts
            ArrayList<Object> listeDesComptesAll = FilesManager.readAll("comptes");

            for (Object compteInstObj : listeDesComptesAll) {
                Compte compteInst = (Compte) compteInstObj;

                if (compteInst.getOwner().getIdentifiant().equals(connectedUser.getIdentifiant())) {
                    listeDesComptes.add(compteInstObj);
                }
            }
        } else {
            listeDesComptes = FilesManager.readAll("comptes");
        }


        if (listeDesComptes.isEmpty()) {
            System.out.println("Aucun compte à afficher.");
        } else { // if there are accounts to display
            for (Object compteUnit : listeDesComptes) {
                Compte compteIn = (Compte) compteUnit; // object to Compte

                Epargne compteEp = null;
                try {
                   compteEp = (Epargne) compteIn;
                } catch (Exception e) {
                    e.getSuppressed();
                }

                if (compteEp != null) { // changing interest when display
                    compteEp.setSolde(compteEp.getSolde() + (((Epargne) compteUnit).getTauxInteret() * compteEp.getSolde())); // changing account balance - adding interest rate amount
                }

                // print out account description accordingly
                System.out.println(compteIn.toString());
            }
        }
    }




    /**
     * Method to display menu if current user is a customer + reactions regarding menu choice
     */
    public static void displayCustomerMenu(User connectedUser) {


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
        String userChoiceString = myObj.next(); // receiving user answer in String
        Integer userChoice = null;


        try {  // try to parse user answer to check if Integer
            userChoice = Integer.parseInt(userChoiceString);
        } catch (NumberFormatException ex) {
            System.out.println("Veuillez renseigner un nombre.");
        }

        if (userChoice != null) { // if user answer is an Integer

                boolean hasActivatedAccount = Finder.findActivatedAccount(connectedUser);

                if (!hasActivatedAccount && userChoice > 1) {
                    System.out.println("Vous ne possédez aucun compte actif.");
                } else {
                    switch (userChoice) {
                        case 0:
                            App.run();
                            break;
                        case 1:
                            Banking.createAccount(connectedUser);
                            break;
                        case 2:
                            Banking.versement(connectedUser);
                            break;
                        case 3:
                            Banking.retrait(connectedUser);
                            break;
                        case 4:
                            Banking.virement(connectedUser, 1);
                            break;
                        case 5:
                            Display.displayListeCompte(connectedUser);
                            break;
                        case 6:
                            Display.displayOperationAmount(3, 1, Banque.currentUser);
                            break;
                        case 7:
                            Display.displayOperationAmount(1, 1, Banque.currentUser);
                            break;
                        case 8:
                            Display.displayOperationAmount(2, 1, Banque.currentUser);
                            break;
                        default:
                            System.out.println("Ce choix n'existe pas.");
                    }
                }

        }

        displayCustomerMenu(connectedUser); // recursive
    }


    /**
     * Method to display menu if current user is an advisor + reactions regarding menu choice
     */
    public static void displayAdvisorMenu(User connectedUser) {

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Activer/désactiver un compte");
        System.out.println("2. Virement");
        System.out.println("3. Voir la Liste des comptes");
        System.out.println("4. Changer les intérêts d'un compte");
        System.out.println("5. Changer la limite du découvert d'un compte");
        System.out.println("6. Consulter la liste des opérations");

        System.out.println("Choisir une option (Pour arrêter = 0)");
        String userChoiceString = myObj.next(); // receiving user answer in String
        Integer userChoice = null;


        try {  // try to parse user answer to check if Integer
            userChoice = Integer.parseInt(userChoiceString);
        } catch (NumberFormatException ex) {
            System.out.println("Veuillez renseigner un nombre.");
        }

        if (userChoice != null) { // if user answer is an Integer

            switch (userChoice) {
                case 0:
                    App.run();
                    break;
                case 1:
                    Banking.activeAccount();
                    break;
                case 2:
                    Banking.virement(connectedUser, 2);
                    break;
                case 3:
                    Display.displayListeCompte(connectedUser);
                    break;
                case 4:
                    Banking.changeInterest();
                    break;
                case 5:
                    Banking.changeOverdraft();
                    break;
                case 6:
                    Display.displayOperationAmount(3, 2, Banque.currentUser);
                    break;
                default:
                    System.out.println("Ce choix n'existe pas.");
            }
        }

        displayAdvisorMenu(connectedUser); // recursive
    }


    /**
     * Method to display the list of operations (either a part or whole) and calculating the total amount for each type of operations
     */
    public static void displayOperationAmount(Integer typeDisplay, Integer typeUser, User connectedUser) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Entrez le n° du compte pour afficher le montant des versements.");

        String accountCode = myObj.next();
        Integer indexCompteChoisi = Finder.findIndexCompte(accountCode);

        // if account exists + user is advisor OR user is customer and the account is his
        if (indexCompteChoisi != -1 && (typeUser == 2 || (typeUser == 1 && (Banque.getListeComptes().get(indexCompteChoisi).getOwner().equals(connectedUser))))) {
            Compte compteChoisi = Banque.getListeComptes().get(indexCompteChoisi);
            Double amountVersement = 0.0;
            Double amountRetrait = 0.0;

            if (typeDisplay == 1 || typeDisplay == 3) { // if chose to show debit or both
                for (int i = 0; i < compteChoisi.getOperationsArrayList().size(); i++) {
                    Operations currInstance = compteChoisi.getOperationsArrayList().get(i);
                    if (currInstance.getType().equals("versement") || currInstance.getType().equals("virement / versement") ) {
                        System.out.println(currInstance.getDate() + " - " + currInstance.getType() + " de " + currInstance.getMontant() + " €, effectué par " + currInstance.getIdentifiantUser());
                        amountVersement += currInstance.getMontant(); // calculating the total amount of debit
                    }
                }
            }

            if (amountVersement != 0) {
                System.out.println("Le montant des versements et de " + amountVersement + " €.\n");
            } else if (typeDisplay != 2){
                System.out.println("Aucun versement à afficher.\n");
            }


            if (typeDisplay == 2 || typeDisplay == 3) { // if chose to show credit or both
                for (int i = 0; i < compteChoisi.getOperationsArrayList().size(); i++) {
                    Operations currInstance = compteChoisi.getOperationsArrayList().get(i);
                    if (currInstance.getType().equals("retrait") || currInstance.getType().equals("virement / retrait") ) {
                        System.out.println(currInstance.getDate() + " - " + currInstance.getType() + " de " + currInstance.getMontant() + " €, effectué par " + currInstance.getIdentifiantUser());
                        amountRetrait += currInstance.getMontant(); // calculating the total amount of credit
                    }
                }
            }

            if (amountRetrait != 0) {
                System.out.println("Le montant des retraits et de " + amountRetrait + " €.\n");
            } else if (typeDisplay !=1) {
                System.out.println("Aucun retrait à afficher.\n");
            }

        } else {
            System.out.println("Compte introuvable.");
        }
    }
}
