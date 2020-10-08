package fr.cda.data;

import fr.cda.data.files.filesManagement.FilesManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Banking {

    /**************************
     *                        *
     *        METHODS         *
     *                        *
     **************************/

    /**
     * Method to create an account and saving it in the ArrayList of all accounts
     */
    public static void createAccount(User connectedUser) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Compte courant ?");
        System.out.println("2. Compte épargne ?");
        System.out.println("0 pour arrêter.");

        String accountTypeString = myObj.next(); // receiving user answer in String
        Integer accountType = null;


        if (!accountTypeString.equals("0")) {

            try { // try to parse user answer to check if Integer
                accountType = Integer.parseInt(accountTypeString);
            } catch (NumberFormatException ex) {
                System.out.println("Veuillez renseigner un nombre.");
                createAccount(connectedUser); // recursive
            }

            if (accountType != null) { // if user answer is an Integer
                if (accountType != 1 && accountType != 2) {
                    System.out.println("Choix incorrect.");
                    createAccount(connectedUser); // recursive
                } else {
                    System.out.println("Code du compte - 3 caractères minimum (0 pour arrêter)");
                    String codeCompte = myObj.next();

                    if (codeCompte.length() > 2) {
                        Integer indexCompte = Finder.findIndexCompte(codeCompte); // find account index in external file

                        // if account code does not exist already
                        if (indexCompte == -1) {
                            System.out.println("Solde du compte");
                            String soldeCompteString = myObj.next(); // receiving user answer in String
                            Double soldeCompte = null;


                            try { // try to parse user answer to check if Double
                                soldeCompte = Double.parseDouble(soldeCompteString);
                            } catch (NumberFormatException ex) {
                                System.out.println("Veuillez renseigner un nombre.");
                                createAccount(connectedUser);
                            }

                            if (soldeCompte != null) { // if user answer is a Double

                                if (accountType == 1) { // if account is current account
                                    Courant compteCourant = new Courant(codeCompte, soldeCompte, false, connectedUser, -150.0);
                                    ((Client)connectedUser).getListeComptesClient().add(compteCourant);
                                } else { // if account is savings account
                                    Epargne compteEpargne = new Epargne(codeCompte, soldeCompte, false, connectedUser, 2.50);
                                    ((Client)connectedUser).getListeComptesClient().add(compteEpargne);
                                }

                                FilesManager.updateAccountFile(); // updating external account file with new data

                            }
                        } else {
                            System.out.println("Code du compte déjà pris.");
                            createAccount(connectedUser); // recursive
                        }
                    } else {
                        System.out.println("Le code du compte doit comporter 3 caractères minimum.");
                        createAccount(connectedUser); // recursive
                    }
                }
            }
        } else {
            Display.displayCustomerMenu(connectedUser);
        }
    }



    /**
     * Method to credit a selected account of an amount given by user, including saving operations into an array
     */
    public static void versement(User connectedUser) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte (0 pour arrêter)");
        String numeroCompteChoisi = myObj.next();


        if (!numeroCompteChoisi.equals("0")) {
            Integer indexCompteChoisi = Finder.findIndexCompte(numeroCompteChoisi, connectedUser); // account index in external file


            if (indexCompteChoisi != -1) { // if account exists
                ArrayList<Object> accountListAllObj = FilesManager.readAll("comptes");
                Compte compteChoisi = (Compte) accountListAllObj.get(indexCompteChoisi);


                // if account was activated beforehand
                if (!compteChoisi.isActivated()) {
                    System.out.println("Compte indisponible.");
                } else {

                    System.out.println("Montant de versement");
                    String montantVersementString = myObj.next(); // receiving user answer in String
                    Double montantVersement = null;


                    try { // try to parse user answer to check if Double
                        montantVersement = Double.parseDouble(montantVersementString);
                    } catch (NumberFormatException ex) {
                        System.out.println("Veuillez renseigner un nombre.");
                        versement(connectedUser); // recursive
                    }

                    if (montantVersement != null) { // if user answer is a Double
                        Double soldePrecedent = compteChoisi.getSolde();
                        compteChoisi.setSolde(soldePrecedent + montantVersement);
                        Double nouveauSolde = compteChoisi.getSolde();

                        // saving operation
                        Operations.saveOperations(compteChoisi, montantVersement, "versement", connectedUser.identifiant);

                        FilesManager.updateAccountFile(); // updating external file

                        System.out.println("Le compte a bien été crédité de " + montantVersement + "€. Nouveau solde : " + nouveauSolde + "€ (ancien : " + soldePrecedent + ").");
                    }
                }
            } else {
                System.out.println("Le compte n'existe pas.");
            }
        }
    }


    /**
     * Method to debit a selected account of an amount given by user - checking if withdrawal is possible (overdraft) and including saving operations into an array
     */
    public static void retrait(User connectedUser) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte (0 pour arrêter)");
        String numeroCompteChoisi = myObj.next();

        if (!numeroCompteChoisi.equals("0")) {
            Integer indexCompteChoisi = Finder.findIndexCompte(numeroCompteChoisi, connectedUser); // index in user's own array of accounts

            if (indexCompteChoisi != -1) {// if account exists
                ArrayList<Object> accountListAllObj = FilesManager.readAll("comptes");
                Compte compteChoisi = (Compte) accountListAllObj.get(indexCompteChoisi);

                // if account was activated beforehand
                if (!compteChoisi.isActivated()) {
                    System.out.println("Compte indisponible.");
                } else {

                    System.out.println("Montant à débiter");
                    String montantRetraitString = myObj.next(); // receiving user answer in String
                    Double montantRetrait = null;


                    try { // try to parse user answer to check if Double
                        montantRetrait = Double.parseDouble(montantRetraitString);
                    } catch (NumberFormatException ex) {
                        System.out.println("Veuillez renseigner un nombre.");
                        retrait(connectedUser); // recursive
                    }

                    if (montantRetrait != null) { // if user answer is a Double
                        Double soldePrecedent = compteChoisi.getSolde();


                        // checking that debit does cause to go overdraft if current account or below 0 if savings account
                        if ((compteChoisi instanceof Courant && (soldePrecedent - montantRetrait > ((Courant) compteChoisi).getDecouvert())) || (compteChoisi instanceof Epargne && (soldePrecedent - montantRetrait >= 0))) {
                            compteChoisi.setSolde(soldePrecedent - montantRetrait);
                            Double nouveauSolde = compteChoisi.getSolde();

                            // saving operation
                            Operations.saveOperations(compteChoisi, montantRetrait, "retrait", connectedUser.identifiant);

                            FilesManager.updateAccountFile(); // updating external file

                            System.out.println("Le compte a bien été débité de " + montantRetrait + "€. Nouveau solde : " + nouveauSolde + " (ancien : " + soldePrecedent + "€).");

                        } else {
                            System.out.println("Opération impossible.");
                        }}

                }
            } else {
                System.out.println("Compte introuvable.");
            }
        }
    }



    /**
     * Method to credit a selected account of an amount given by user and debit another account - checking if withdrawal is possible (overdraft) and including saving operations into an array
     */
    public static void virement(User connectedUser, Integer typeUser) {

        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte 1 (à débiter) (0 pour arrêter)");
        String numeroCompte = myObj.next();


        if (!numeroCompte.equals("0")) {
            Integer indexCompteChoisi = Finder.findIndexCompte(numeroCompte); // find index in global array

            ArrayList<Object> accountListAllObj = FilesManager.readAll("comptes");
            Compte compteChoisi = indexCompteChoisi != -1 ? (Compte) accountListAllObj.get(indexCompteChoisi) : null;

            // if account exists and account is the user's account (if user is customer) and if account was activated beforehand
            if (indexCompteChoisi == -1 || (typeUser == 1 && compteChoisi.getOwner().equals(connectedUser)) || !compteChoisi.isActivated()) {
                System.out.println("Compte introuvable.");
            } else {
                System.out.println("N° du compte 2 (à créditer)");
                String numeroCompte2 = myObj.next();
                Integer indexCompteChoisi2 = Finder.findIndexCompte(numeroCompte2);
                Compte compteChoisi2 = indexCompteChoisi2 != -1 ? (Compte) accountListAllObj.get(indexCompteChoisi2) : null;

                // if account does not exist AND if account not activated
                if (indexCompteChoisi2 == -1 || !compteChoisi2.isActivated()) {
                    System.out.println("Compte introuvable.");
                } else if (indexCompteChoisi.equals(indexCompteChoisi2)) { // if bank transfer on the same account
                    System.out.println("Vous ne pouvez pas effectuer de virement sur le même compte.");
                    virement(connectedUser, typeUser); // recursive
                } else {

                    System.out.println("Montant de virement");
                    String montantVirementString = myObj.next(); // receiving user answer in String
                    Double montantVirement = null;


                    try { // try to parse user answer to check if Double
                        montantVirement = Double.parseDouble(montantVirementString);
                    } catch (NumberFormatException ex) {
                        System.out.println("Veuillez renseigner un nombre.");
                        virement(connectedUser, typeUser); // recursive
                    }

                    if (montantVirement != null) { // if user answer is a Double

                        // checking that debit does cause to go overdraft if current account or below 0 if savings account on first account
                        if (compteChoisi instanceof Courant && (compteChoisi.getSolde() - montantVirement > ((Courant) compteChoisi).getDecouvert()) || (compteChoisi instanceof Epargne && (compteChoisi.getSolde() - montantVirement >= 0))) {

                            compteChoisi.setSolde(compteChoisi.getSolde() - montantVirement);
                            compteChoisi2.setSolde(compteChoisi2.getSolde() + montantVirement);

                            Operations.saveOperations(compteChoisi, montantVirement, "virement / retrait", connectedUser.identifiant);
                            Operations.saveOperations(compteChoisi2, montantVirement, "virement / versement", connectedUser.identifiant);

                            FilesManager.updateAccountFile(); // updating external file

                            System.out.println("Le virement de " + montantVirement + "€ a été effectué du compte n°" + compteChoisi.getCode() + " au compte n°" + compteChoisi2.getCode() + ".");
                        } else {
                            System.out.println("Opération impossible.");
                        }
                    }
                }
            }
        }
    }


    /**
     * Method to activate or disactive an account
     */
    public static void activeAccount() {
        boolean found = false;

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Nom du compte à activer ou désactiver ? (0 pour arrêter)");
        String accountCode = myObj.next();

        if (!accountCode.equals("0")) {
            ArrayList<Object> listeDesComptesObj = FilesManager.readAll("comptes");

            for (Object comptObj : listeDesComptesObj) {
                Compte comptInst = (Compte) comptObj;
                if (comptInst.getCode().equals(accountCode)) {
                    found = true;

                    Integer index = -1;
                    if (Banque.getListeComptes().size() > 0) {
                        for (int i = 0; i < Banque.getListeComptes().size(); i++) {
                            if (Banque.getListeComptes().get(i).getCode().equals(comptInst.code)) {
                                index = i;
                            }
                        }
                    }


                    if (!comptInst.isActivated()) {

                        if (index != -1) {
                            Banque.getListeComptes().get(index).setActivated(true);
                        } else {
                            Banque.getListeComptes().add(comptInst);
                            Banque.getListeComptes().get(Banque.getListeComptes().size() - 1).setActivated(true);
                        }


                        System.out.println("Le compte n°" + accountCode + " a été activé");
                    } else {

                        if (index != -1) {
                            Banque.getListeComptes().get(index).setActivated(false);
                        } else {
                            Banque.getListeComptes().add(comptInst);
                            Banque.getListeComptes().get(Banque.getListeComptes().size() - 1).setActivated(false);
                        }

                        System.out.println("Le compte n°" + accountCode + " a été désactivé");
                    }

                    FilesManager.updateAccountFile(); // save new results
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
            ArrayList<Object> accountListAllObj = FilesManager.readAll("comptes");
            Integer indexCompte = Finder.findIndexCompte(accountCode); // checking the account exists
            Compte accountInst = indexCompte != -1 ? (Compte) accountListAllObj.get(indexCompte) : null;

            if (accountInst != null) {

                if (accountInst instanceof Epargne) { // checking the account is a savings one
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


                        Integer index = -1;
                        if (Banque.getListeComptes().size() > 0) {
                            for (int i = 0; i < Banque.getListeComptes().size(); i++) {
                                if (Banque.getListeComptes().get(i).getCode().equals(accountInst.code)) {
                                    index = i;
                                }
                            }
                        }


                        if (index == -1) {
                            Banque.getListeComptes().add(accountInst);
                            index = Banque.getListeComptes().size()-1;
                        }

                        Epargne compteEp = (Epargne) Banque.getListeComptes().get(index);
                        compteEp.setTauxInteret(newInterest);
                        System.out.println("Taux d'intérêt changé.");

                        FilesManager.updateAccountFile();
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
            ArrayList<Object> accountListAllObj = FilesManager.readAll("comptes");
            Integer indexCompte = Finder.findIndexCompte(accountCode); // checking the account exists
            Compte accountInst = indexCompte != -1 ? (Compte) accountListAllObj.get(indexCompte) : null;

            if (accountInst != null) {

                if (accountInst instanceof Courant) { // checking account is a current one
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

                        Integer index = -1;
                        if (Banque.getListeComptes().size() > 0) {
                            for (int i = 0; i < Banque.getListeComptes().size(); i++) {
                                if (Banque.getListeComptes().get(i).getCode().equals(accountInst.code)) {
                                    index = i;
                                }
                            }
                        }


                        if (index == -1) {
                            Banque.getListeComptes().add(accountInst);
                            index = Banque.getListeComptes().size()-1;
                        }

                        Courant compteCour = (Courant) Banque.getListeComptes().get(index);
                        compteCour.setDecouvert(newOverdraft);

                        System.out.println("Montant du découvert changé.");
                        FilesManager.updateAccountFile();
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
