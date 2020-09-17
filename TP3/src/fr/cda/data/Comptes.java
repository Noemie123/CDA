package fr.cda.data;

import java.util.ArrayList;
import java.util.Scanner;

public class Comptes {
    /**
     * Attributes
     */
    protected String code;
    protected Double solde;
    protected Integer type;
    protected Integer indexUser;

//    protected ArrayList<Comptes> comptesArrayList = new ArrayList();
    protected ArrayList<Operations> operationsArrayList = new ArrayList<>();


    /**
     * Constructor
     */
    public Comptes (String code, Double solde, Integer type, Integer indexUser) {
        this.code = code;
        this.solde = solde;
        this.type = type;
        this.indexUser = indexUser;

//        comptesArrayList.add(this);
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIndexUser() {
        return indexUser;
    }

    public void setIndexUser(Integer indexUser) {
        this.indexUser = indexUser;
    }

    /**
     * Method to create a new account with a code and a type - recursive
     */
    public static void createAccount(Integer indexUser, ArrayList<Comptes> arrayComptes, ArrayList<Courants> arrayComptesCourants, ArrayList<Epargnes> arrayComptesEpargnes ) {

        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte");
        String codeCompte = myObj.next();

        Integer indexFound = findIndex(arrayComptes, codeCompte);

        if (indexFound == -1) {
            System.out.println("Courant(1) ou Epargne(2) ?");
            Integer userChoice = myObj.nextInt();

            if (userChoice == 1) {
                Courants compteCourant = new Courants(codeCompte, 0.0, userChoice, indexUser, -150.0);
                arrayComptesCourants.add(compteCourant);
                arrayComptes.add(compteCourant);
            } else if (userChoice == 2) {
                Epargnes compteEpargne = new Epargnes(codeCompte, 0.0, userChoice , indexUser,2.5);
                arrayComptesEpargnes.add(compteEpargne);
                arrayComptes.add(compteEpargne);
            } else {
                createAccount(indexUser, arrayComptes, arrayComptesCourants, arrayComptesEpargnes);
            }

            Scanner myObj2 = new Scanner(System.in);
            System.out.println("Continuer ? (o pour oui)");
            String continueAnswer = myObj2.nextLine();

            if (continueAnswer.equals("o")) {
                createAccount(indexUser, arrayComptes, arrayComptesCourants, arrayComptesEpargnes);
            }
        } else {
            System.out.println("Le compte existe déjà.");
            createAccount(indexUser, arrayComptes, arrayComptesCourants, arrayComptesEpargnes);
        }

    }


    /**
     * Method to find index of account in global array
     */

    public static Integer findIndex(ArrayList<Comptes> arr, String numeroCompte) {

        for (int i = 0; i < arr.size(); i++) {

                if (arr.get(i).getCode().equals(numeroCompte)) {
                    return i;
                }

        }
        return -1;
    }


    public static Integer findIndexCourants(ArrayList<Courants> arr, String numeroCompte) {

        for (int i = 0; i < arr.size(); i++) {

            if (arr.get(i).getCode().equals(numeroCompte)) {
                return i;
            }

        }
        return -1;
    }


    public static Integer findIndexEpargnes(ArrayList<Epargnes> arr,String numeroCompte) {

        for (int i = 0; i < arr.size(); i++) {

            if (arr.get(i).getCode().equals(numeroCompte)) {
                return i;
            }

        }
        return -1;
    }
}
