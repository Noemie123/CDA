package fr.cda.data;

import java.util.Date;
import java.util.Scanner;

public class Operations {
    /**
     * Attributes
     */
    private Integer numero;
    private Date date;
    private Double montant;
    private String type;


    /**
     * Constructor
     */
    public Operations (Integer numero, Date date, Double montant, String type) {
        this.numero = numero;
        this.date = date;
        this.montant = montant;
        this.type = type;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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


    public static void saveOperations(Compte compteChoisi, Double montant, String type) {
        Date date = new Date();

        Integer operationNumber = compteChoisi.getOperationsArrayList().size()+1;

        Operations op = new Operations(operationNumber, date, montant, type);
        compteChoisi.getOperationsArrayList().add(op);
    }

    /**
     * Method to credit a selected account of an amount given by user
     * Including saving operations into an array
     */
    public static void versement(String indentifiantUser) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte");
        String numeroCompteChoisi = myObj.next();
        Integer indexCompteChoisi = Compte.findIndexCompte(numeroCompteChoisi);


        if (indexCompteChoisi != -1) {
            if (!Compte.listeComptes.get(indexCompteChoisi).getIdentifiantUser().equals(indentifiantUser)) {
                System.out.println("Compte indisponible.");
            } else {

                System.out.println("Montant de versement");
                Double montantVersement = myObj.nextDouble();

                Compte compteChoisi = Compte.listeComptes.get(indexCompteChoisi);
                Double soldePrecedent = compteChoisi.getSolde();
                String soldePrec = soldePrecedent < 0 ? "\u001B[31m" + soldePrecedent + "€" + "\u001B[0m" : soldePrecedent + "€";
                compteChoisi.setSolde(soldePrecedent + montantVersement);
                Double nouveauSolde = compteChoisi.getSolde();

                // saving operation
                saveOperations(compteChoisi, montantVersement, "versement");


                System.out.println("Le compte a bien été crédité de " + montantVersement + "€. Nouveau solde : " + nouveauSolde + "€ (ancien : " + soldePrec + ").");
            }
        } else {
            System.out.println("Le compte n'existe pas.");
        }

    }


    /**
     * Method to debit a selected account of an amount given by user - checking if withdrawal is possible (overdraft)
     * Including saving operations into an array
     */
    public static void retrait(String identifiantUser) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte");
        String numeroCompte = myObj.next();
        Integer indexCompteChoisi = Compte.findIndexCompte(numeroCompte);

        if (indexCompteChoisi != -1) {

            if (!Compte.listeComptes.get(indexCompteChoisi).getIdentifiantUser().equals(identifiantUser)) {
                System.out.println("Compte indisponible.");
            } else {

                System.out.println("Montant à débiter");
                Double montantRetrait = myObj.nextDouble();

                Compte compteChoisi = Compte.listeComptes.get(indexCompteChoisi);
                Double soldePrecedent = compteChoisi.getSolde();



                if ((compteChoisi instanceof Courant && (soldePrecedent-montantRetrait > ((Courant) compteChoisi).getDecouvert())) || (compteChoisi instanceof Epargne && (soldePrecedent-montantRetrait >= 0))) {
                    compteChoisi.setSolde(soldePrecedent - montantRetrait);
                    Double nouveauSolde = compteChoisi.getSolde();
                    System.out.println("Le compte a bien été débité de " + montantRetrait + "€. Nouveau solde : " + nouveauSolde + " (ancien : " + soldePrecedent + "€).");

                } else {
                    System.out.println("Opération impossible.");
                }

            }
        } else {
            System.out.println("Compte introuvable.");
        }
    }



    /**
     * Method to credit a selected account of an amount given by user and debit another account - checking if withdrawal is possible (overdraft)
     * Including saving operations into an array
     */
    public static void virement(String identifiantUser, Integer typeUser) {

        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte 1 (à débiter)");
        String numeroCompte = myObj.next();
        Integer indexCompteChoisi = Compte.findIndexCompte(numeroCompte);

        if (indexCompteChoisi == -1 || (typeUser == 1 && !Compte.listeComptes.get(indexCompteChoisi).getIdentifiantUser().equals(identifiantUser))) {
            System.out.println("Compte introuvable.");
            virement(identifiantUser, typeUser);
        } else {
            System.out.println("N° du compte 2 (à créditer)");
            String numeroCompte2 = myObj.next();
            Integer indexCompteChoisi2 = Compte.findIndexCompte(numeroCompte2);

            if (indexCompteChoisi2 == -1 || (typeUser == 1 && !Compte.listeComptes.get(indexCompteChoisi2).getIdentifiantUser().equals(identifiantUser))) {
                System.out.println("Compte introuvable.");
                virement(identifiantUser, typeUser);
            } else if (indexCompteChoisi.equals(indexCompteChoisi2)) {
                System.out.println("Vous ne pouvez pas effectuer de virement sur le même compte.");
                virement(identifiantUser, typeUser);
            } else {

                System.out.println("Montant de virement");
                Double montantVirement = myObj.nextDouble();

                Compte compteChoisi = Compte.listeComptes.get(indexCompteChoisi);


                //checking overdraft on first account
                if (compteChoisi instanceof Courant && (compteChoisi.getSolde() - montantVirement > ((Courant) compteChoisi).getDecouvert()) || (compteChoisi instanceof Epargne && (compteChoisi.getSolde() - montantVirement >= 0))) {
                    Compte compteChoisi2 = Compte.listeComptes.get(indexCompteChoisi2);

                    compteChoisi.setSolde(compteChoisi.getSolde() - montantVirement);
                    compteChoisi2.setSolde(compteChoisi2.getSolde() + montantVirement);

                    saveOperations(compteChoisi, montantVirement, "virement / retrait");
                    saveOperations(compteChoisi2, montantVirement, "virement / versement");
                } else {
                    System.out.println("Opération impossible.");
                }

            }
        }
    }



}
