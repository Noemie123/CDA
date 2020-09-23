package fr.cda.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Operations {
    /**
     * Attributes
     */
    private Integer numero;
    private String date;
    private Double montant;
    private String type;
    private String identifiantUser;


    /**
     * Constructor
     */
    public Operations (Integer numero, String date, Double montant, String type, String identifiantUser) {
        this.numero = numero;
        this.date = date;
        this.montant = montant;
        this.type = type;
        this.identifiantUser = identifiantUser;
    }


    /**
     * Lists of getters and setters
     */
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifiantUser() {
        return identifiantUser;
    }

    public void setIdentifiantUser(String identifiantUser) {
        this.identifiantUser = identifiantUser;
    }


    /**
     * Methods
     */

    public static void saveOperations(Compte compteChoisi, Double montant, String type, String identifiantUser) {
        Date date = new Date();
        String today = new SimpleDateFormat("dd-MM-yyyy").format(date);

        Integer operationNumber = compteChoisi.getOperationsArrayList().size()+1;

        Operations op = new Operations(operationNumber, today, montant, type, identifiantUser);
        compteChoisi.getOperationsArrayList().add(op);
    }


    /**
     * Method to credit a selected account of an amount given by user
     * Including saving operations into an array
     */
    public static void versement(String identifiantUser) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte (0 pour arrêter)");
        String numeroCompteChoisi = myObj.next();


        if (!numeroCompteChoisi.equals("0")) {
            Integer indexCompteChoisi = Compte.findIndexCompte(numeroCompteChoisi);


            if (indexCompteChoisi != -1) {
                if (!Compte.listeComptes.get(indexCompteChoisi).getIdentifiantUser().equals(identifiantUser) || !Compte.listeComptes.get(indexCompteChoisi).isActivated()) {
                    System.out.println("Compte indisponible.");
                } else {

                    System.out.println("Montant de versement");
                    String montantVersementString = myObj.next();
                    Double montantVersement = null;


                    try {
                        montantVersement = Double.parseDouble(montantVersementString);
                    } catch (NumberFormatException ex) {
                        System.out.println("Veuillez renseigner un nombre.");
                        versement(identifiantUser);
                    }

                    if (montantVersement != null) {
                        Compte compteChoisi = Compte.listeComptes.get(indexCompteChoisi);
                        Double soldePrecedent = compteChoisi.getSolde();
                        String soldePrec = soldePrecedent < 0 ? "\u001B[31m" + soldePrecedent + "€" + "\u001B[0m" : soldePrecedent + "€";
                        compteChoisi.setSolde(soldePrecedent + montantVersement);
                        Double nouveauSolde = compteChoisi.getSolde();

                        // saving operation
                        saveOperations(compteChoisi, montantVersement, "versement", identifiantUser);


                        System.out.println("Le compte a bien été crédité de " + montantVersement + "€. Nouveau solde : " + nouveauSolde + "€ (ancien : " + soldePrec + ").");
                    }
                }
            } else {
                System.out.println("Le compte n'existe pas.");
            }
        }
    }


    /**
     * Method to debit a selected account of an amount given by user - checking if withdrawal is possible (overdraft)
     * Including saving operations into an array
     */
    public static void retrait(String identifiantUser) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte (0 pour arrêter)");
        String numeroCompte = myObj.next();

        if (!numeroCompte.equals("0")) {
            Integer indexCompteChoisi = Compte.findIndexCompte(numeroCompte);

            if (indexCompteChoisi != -1) {

                if (!Compte.listeComptes.get(indexCompteChoisi).getIdentifiantUser().equals(identifiantUser) || !Compte.listeComptes.get(indexCompteChoisi).isActivated()) {
                    System.out.println("Compte indisponible.");
                } else {

                    System.out.println("Montant à débiter");
                    String montantRetraitString = myObj.next();
                    Double montantRetrait = null;


                    try {
                        montantRetrait = Double.parseDouble(montantRetraitString);
                    } catch (NumberFormatException ex) {
                        System.out.println("Veuillez renseigner un nombre.");
                        retrait(identifiantUser);
                    }

                    if (montantRetrait != null) {
                    Compte compteChoisi = Compte.listeComptes.get(indexCompteChoisi);
                    Double soldePrecedent = compteChoisi.getSolde();


                    if ((compteChoisi instanceof Courant && (soldePrecedent - montantRetrait > ((Courant) compteChoisi).getDecouvert())) || (compteChoisi instanceof Epargne && (soldePrecedent - montantRetrait >= 0))) {
                        compteChoisi.setSolde(soldePrecedent - montantRetrait);
                        Double nouveauSolde = compteChoisi.getSolde();

                        // saving operation
                        saveOperations(compteChoisi, montantRetrait, "retrait", identifiantUser);

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
     * Method to credit a selected account of an amount given by user and debit another account - checking if withdrawal is possible (overdraft)
     * Including saving operations into an array
     */
    public static void virement(String identifiantUser, Integer typeUser) {

        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte 1 (à débiter) (0 pour arrêter)");
        String numeroCompte = myObj.next();


        if (!numeroCompte.equals("0")) {
            Integer indexCompteChoisi = Compte.findIndexCompte(numeroCompte);

            if (indexCompteChoisi == -1 || (typeUser == 1 && !Compte.listeComptes.get(indexCompteChoisi).getIdentifiantUser().equals(identifiantUser)) || !Compte.listeComptes.get(indexCompteChoisi).isActivated()) {
                System.out.println("Compte introuvable.");
            } else {
                System.out.println("N° du compte 2 (à créditer)");
                String numeroCompte2 = myObj.next();
                Integer indexCompteChoisi2 = Compte.findIndexCompte(numeroCompte2);

                if (indexCompteChoisi2 == -1 || (typeUser == 1 && !Compte.listeComptes.get(indexCompteChoisi2).getIdentifiantUser().equals(identifiantUser)) && !Compte.listeComptes.get(indexCompteChoisi2).isActivated()) {
                    System.out.println("Compte introuvable.");
                    virement(identifiantUser, typeUser);
                } else if (indexCompteChoisi.equals(indexCompteChoisi2)) {
                    System.out.println("Vous ne pouvez pas effectuer de virement sur le même compte.");
                    virement(identifiantUser, typeUser);
                } else {

                    System.out.println("Montant de virement");
                    String montantVirementString = myObj.next();
                    Double montantVirement = null;


                    try {
                        montantVirement = Double.parseDouble(montantVirementString);
                    } catch (NumberFormatException ex) {
                        System.out.println("Veuillez renseigner un nombre.");
                        virement(identifiantUser, typeUser);
                    }

                    if (montantVirement != null) {
                        Compte compteChoisi = Compte.listeComptes.get(indexCompteChoisi);


                        //checking overdraft on first account
                        if (compteChoisi instanceof Courant && (compteChoisi.getSolde() - montantVirement > ((Courant) compteChoisi).getDecouvert()) || (compteChoisi instanceof Epargne && (compteChoisi.getSolde() - montantVirement >= 0))) {
                            Compte compteChoisi2 = Compte.listeComptes.get(indexCompteChoisi2);

                            compteChoisi.setSolde(compteChoisi.getSolde() - montantVirement);
                            compteChoisi2.setSolde(compteChoisi2.getSolde() + montantVirement);

                            saveOperations(compteChoisi, montantVirement, "virement / retrait", identifiantUser);
                            saveOperations(compteChoisi2, montantVirement, "virement / versement", identifiantUser);

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
