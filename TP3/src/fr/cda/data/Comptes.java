package fr.cda.data;

import java.util.ArrayList;
import java.util.Scanner;

public class Comptes {
    /**
     * Attributes
     */
    protected Integer code;
    protected Double solde;
    private Integer type;

    private ArrayList<Operations> operationsArrayList = new ArrayList<>();


    /**
     * Constructor
     */
    public Comptes (Integer code, Double solde) {
        this.code = code;
        this.solde = solde;
    }


    /**
     * Lists of getters and setters
     */
    public ArrayList<Operations> getOperationsArrayList() {
        return operationsArrayList;
    }

    public void setOperationsArrayList(ArrayList<Operations> operationsArrayList) {
        this.operationsArrayList = operationsArrayList;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }



    /**
     * Method to create a new account with a code and a type - recursive
     */
    public static void createAccount(ArrayList<Comptes> arrayComptes, ArrayList<Courants> arrayComptesCourants, ArrayList<Epargnes> arrayComptesEpargnes ) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Code du compte");
        Integer codeCompte = myObj.nextInt();

        System.out.println("Courant(1) ou Epargne(2) ?");
        Integer userChoice = myObj.nextInt();

        if (userChoice == 1) {
            Courants compteCourant = new Courants(codeCompte, 0.0, -150.0);
            compteCourant.setType(userChoice);
            arrayComptesCourants.add(compteCourant);
            arrayComptes.add(compteCourant);
        } else if (userChoice == 2) {
            Epargnes compteEpargne = new Epargnes(codeCompte, 0.0, 2.5);
            compteEpargne.setType(userChoice);
            arrayComptesEpargnes.add(compteEpargne);
            arrayComptes.add(compteEpargne);
        } else {
            createAccount(arrayComptes, arrayComptesCourants, arrayComptesEpargnes);
        }

        Scanner myObj2 = new Scanner(System.in);
        System.out.println("Continuer ? (o pour oui)");
        String continueAnswer = myObj2.nextLine();

        if (continueAnswer.equals("o")) {
            createAccount(arrayComptes, arrayComptesCourants, arrayComptesEpargnes);
        }

    }
}
