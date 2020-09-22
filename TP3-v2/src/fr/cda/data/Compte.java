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
        this.listeComptes = listeComptes;
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

        Integer accountType = myObj.nextInt();

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
                Double soldeCompte = myObj.nextDouble();

                if (accountType == 1) {
                    Compte compteCourant = new Courant(codeCompte, soldeCompte, false, idUser, -150.0);

                } else {
                    Compte compteEpargne = new Epargne(codeCompte, soldeCompte, false, idUser, 2.50);
                }
            } else {
                System.out.println("Code du compte non valide.");
                createAccount(idUser);
            }
        }

    }

}
