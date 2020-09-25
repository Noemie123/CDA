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


    /**
     * Method to credit a selected account of an amount given by user, including saving operations into an array
     */
    public static void versement(User connectedUser) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte (0 pour arrêter)");
        String numeroCompteChoisi = myObj.next();


        if (!numeroCompteChoisi.equals("0")) {
            Integer indexCompteChoisi = Compte.findIndexCompte(numeroCompteChoisi, connectedUser); // index in user's own array of accounts


            if (indexCompteChoisi != -1) { // if account exists
                // if account was activated beforehand
                if (!((Client)connectedUser).getListeComptesClient().get(indexCompteChoisi).isActivated()) {
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
                        Compte compteChoisi = ((Client)connectedUser).getListeComptesClient().get(indexCompteChoisi); // index of the connected user array of accounts
                        Double soldePrecedent = compteChoisi.getSolde();
                        compteChoisi.setSolde(soldePrecedent + montantVersement);
                        Double nouveauSolde = compteChoisi.getSolde();

                        // saving operation
                        saveOperations(compteChoisi, montantVersement, "versement", connectedUser.identifiant);


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
            Integer indexCompteChoisi = Compte.findIndexCompte(numeroCompteChoisi, connectedUser); // index in user's own array of accounts

            if (indexCompteChoisi != -1) {// if account exists
                // if account was activated beforehand
                if (!((Client)connectedUser).getListeComptesClient().get(indexCompteChoisi).isActivated()) {
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
                    Compte compteChoisi = ((Client)connectedUser).getListeComptesClient().get(indexCompteChoisi); // index of the connected user array of accounts
                    Double soldePrecedent = compteChoisi.getSolde();


                    // checking that debit does cause to go overdraft if current account or below 0 if savings account
                    if ((compteChoisi instanceof Courant && (soldePrecedent - montantRetrait > ((Courant) compteChoisi).getDecouvert())) || (compteChoisi instanceof Epargne && (soldePrecedent - montantRetrait >= 0))) {
                        compteChoisi.setSolde(soldePrecedent - montantRetrait);
                        Double nouveauSolde = compteChoisi.getSolde();

                        // saving operation
                        saveOperations(compteChoisi, montantRetrait, "retrait", connectedUser.identifiant);

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
            Integer indexCompteChoisi = Compte.findIndexCompte(numeroCompte); // find index in global array


            // if account exists and account is the user's account (if user is customer) and if account was activated beforehand
            if (indexCompteChoisi == -1 || (typeUser == 1 && !Banque.getListeComptes().get(indexCompteChoisi).getOwner().equals(connectedUser)) || !Banque.getListeComptes().get(indexCompteChoisi).isActivated()) {
                System.out.println("Compte introuvable.");
            } else {
                System.out.println("N° du compte 2 (à créditer)");
                String numeroCompte2 = myObj.next();
                Integer indexCompteChoisi2 = Compte.findIndexCompte(numeroCompte2);

                // if account does not exist AND if account not activated
                if (indexCompteChoisi2 == -1 || !Banque.getListeComptes().get(indexCompteChoisi2).isActivated()) {
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
                        Compte compteChoisi = Banque.getListeComptes().get(indexCompteChoisi);


                        // checking that debit does cause to go overdraft if current account or below 0 if savings account on first account
                        if (compteChoisi instanceof Courant && (compteChoisi.getSolde() - montantVirement > ((Courant) compteChoisi).getDecouvert()) || (compteChoisi instanceof Epargne && (compteChoisi.getSolde() - montantVirement >= 0))) {
                            Compte compteChoisi2 = Banque.getListeComptes().get(indexCompteChoisi2);

                            compteChoisi.setSolde(compteChoisi.getSolde() - montantVirement);
                            compteChoisi2.setSolde(compteChoisi2.getSolde() + montantVirement);

                            saveOperations(compteChoisi, montantVirement, "virement / retrait", connectedUser.identifiant);
                            saveOperations(compteChoisi2, montantVirement, "virement / versement", connectedUser.identifiant);

                            System.out.println("Le virement de " + montantVirement + "€ a été effectué du compte n°" + compteChoisi.getCode() + " au compte n°" + compteChoisi2.getCode() + ".");
                        } else {
                            System.out.println("Opération impossible.");
                        }
                    }
                }
            }
        }
    }

}
