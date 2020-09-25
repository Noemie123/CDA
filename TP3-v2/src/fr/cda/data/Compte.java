package fr.cda.data;

import java.util.ArrayList;
import java.util.Scanner;

public class Compte {

    /**************************
     *                        *
     *       ATTRIBUTES       *
     *                        *
     **************************/

    protected String code;
    protected Double solde;
    protected boolean activated;
    protected User owner;


    protected ArrayList<Operations> operationsArrayList = new ArrayList<>();




    /**************************
     *                        *
     *      CONSTRUCTOR       *
     *                        *
     **************************/

    public Compte (String code, Double solde, boolean activated, User owner) {
        this.code = code;
        this.solde = solde;
        this.activated = activated;
        this.owner = owner;
        Banque.getListeComptes().add(this);
    }




    /**************************
     *                        *
     *    GETTER & SETTER     *
     *                        *
     **************************/

    public String getCode() {
        return code;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Operations> getOperationsArrayList() {
        return operationsArrayList;
    }





    /**************************
     *                        *
     *        METHODS         *
     *                        *
     **************************/

    /**
     * Method to find index of an account in the ArrayList of all accounts thanks to its code
     */
    public static Integer findIndexCompte(String codeCompte) {
        for (int i = 0; i < Banque.getListeComptes().size(); i++) {
            if (Banque.getListeComptes().get(i).getCode().equals(codeCompte)) {
                return i;
            }
        }

        return -1; // if index not found means does not exists then return -1
    }


    /**
     * Method to find index of an account in the ArrayList of accounts of a specific user
     */
    public static Integer findIndexCompte(String codeCompte, User currentUser) {
        for (int i = 0; i < ((Client) currentUser).getListeComptesClient().size(); i++) {
            if (((Client) currentUser).getListeComptesClient().get(i).getCode().equals(codeCompte)) {
                return i;
            }
        }

        return -1; // if index not found means does not exists then return -1
    }


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
                        Integer indexCompte = findIndexCompte(codeCompte);

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
            App.run();
        }
    }



    /**
     * Method to display the list of operations (either a part or whole) and calculating the total amount for each type of operations
     */
    public static void displayOperationAmount(Integer typeDisplay, Integer typeUser, User connectedUser) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Entrez le n° du compte pour afficher le montant des versements.");

        String accountCode = myObj.next();
        Integer indexCompteChoisi = Compte.findIndexCompte(accountCode);

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
