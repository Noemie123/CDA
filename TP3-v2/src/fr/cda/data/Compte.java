package fr.cda.data;

import java.util.ArrayList;
import java.util.Scanner;

public class Compte {
    /**
     * Attributes
     */
    protected String code;
    protected Double solde;
    protected boolean activated;
    protected String identifiantUser;

    public static ArrayList<Compte> listeComptes = new ArrayList<Compte>();
    protected ArrayList<Operations> operationsArrayList = new ArrayList<>();


    /**
     * Constructor
     */
    public Compte (String code, Double solde, boolean activated, String identifiantUser) {
        this.code = code;
        this.solde = solde;
        this.activated = activated;
        this.identifiantUser = identifiantUser;
        listeComptes.add(this);
    }



    /**
     * Getters & Setters
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getIdentifiantUser() {
        return identifiantUser;
    }

    public void setIdentifiantUser(String identifiantUser) {
        this.identifiantUser = identifiantUser;
    }

    public ArrayList<Compte> getListeComptes() {
        return listeComptes;
    }

    public void setListeComptes(ArrayList<Compte> listeComptes) {
        Compte.listeComptes = listeComptes;
    }

    public ArrayList<Operations> getOperationsArrayList() {
        return operationsArrayList;
    }

    public void setOperationsArrayList(ArrayList<Operations> operationsArrayList) {
        this.operationsArrayList = operationsArrayList;
    }


    /**
     * Methods
     */
    public static Integer findIndexCompte(String codeCompte) {
        for (int i = 0; i < Compte.listeComptes.size(); i++) {
            if (Compte.listeComptes.get(i).getCode().equals(codeCompte)) {
                return i;
            }
        }

        return -1;
    }


    public static void createAccount(String idUser) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Compte courant ?");
        System.out.println("2. Compte épargne ?");

        String accountTypeString = myObj.next();
        Integer accountType = null;


        try {
            accountType = Integer.parseInt(accountTypeString);
        } catch (NumberFormatException ex) {
            System.out.println("Veuillez renseigner un nombre.");
            createAccount(idUser);
        }

        if (accountType != null) {
            if (accountType != 1 && accountType != 2) {
                System.out.println("Choix incorrect.");
                createAccount(idUser);
            } else {
                System.out.println("Code du compte (0 pour arrêter)");
                String codeCompte = myObj.next();

                Integer indexCompte = findIndexCompte(codeCompte);

                // if account code does not exist already
                if (indexCompte == -1) {
                    System.out.println("Solde du compte");
                    String soldeCompteString = myObj.next();
                    Double soldeCompte = null;


                    try {
                        soldeCompte = Double.parseDouble(soldeCompteString);
                    } catch (NumberFormatException ex) {
                        System.out.println("Veuillez renseigner un nombre.");
                        createAccount(idUser);
                    }

                    if (soldeCompte != null) {
                        if (accountType == 1) {
                            Compte compteCourant = new Courant(codeCompte, soldeCompte, false, idUser, -150.0);

                        } else {
                            Compte compteEpargne = new Epargne(codeCompte, soldeCompte, false, idUser, 2.50);
                        }
                    }
                } else {
                    System.out.println("Code du compte non valide.");
                    createAccount(idUser);
                }
            }
        }
    }


    public static void displayOperationAmount(Integer typeDisplay, Integer typeUser, String identifiantUser) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Entrez le n° du compte pour afficher le montant des versements.");

        String accountCode = myObj.next();
        Integer indexCompteChoisi = Compte.findIndexCompte(accountCode);

        if (indexCompteChoisi != -1 && (typeUser == 2 || (typeUser == 1 && (Compte.listeComptes.get(indexCompteChoisi).getIdentifiantUser().equals(identifiantUser))))) {
            Compte compteChoisi = Compte.listeComptes.get(indexCompteChoisi);
            Double amountVersement = 0.0;
            Double amountRetrait = 0.0;

            if (typeDisplay == 1 || typeDisplay == 3) {
                for (int i = 0; i < compteChoisi.getOperationsArrayList().size(); i++) {
                    Operations currInstance = compteChoisi.getOperationsArrayList().get(i);
                    if (currInstance.getType().equals("versement") || currInstance.getType().equals("virement / versement") ) {
                        System.out.println(currInstance.getDate() + " - " + currInstance.getType() + " de " + currInstance.getMontant() + " €, effectué par " + currInstance.getIdentifiantUser());
                        amountVersement += currInstance.getMontant();
                    }
                }
            }

            if (amountVersement != 0) {
                System.out.println("Le montant des versements et de " + amountVersement + " €.\n");
            } else if (typeDisplay != 2){
                System.out.println("Aucun versement à afficher.\n");
            }


            if (typeDisplay == 2 || typeDisplay == 3) {
                for (int i = 0; i < compteChoisi.getOperationsArrayList().size(); i++) {
                    Operations currInstance = compteChoisi.getOperationsArrayList().get(i);
                    if (currInstance.getType().equals("retrait") || currInstance.getType().equals("virement / retrait") ) {
                        System.out.println(currInstance.getDate() + " - " + currInstance.getType() + " de " + currInstance.getMontant() + " €, effectué par " + currInstance.getIdentifiantUser());
                        amountRetrait += currInstance.getMontant();
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
