import fr.cda.data.Comptes;
import fr.cda.data.Courants;
import fr.cda.data.Epargnes;
import fr.cda.data.Operations;

import java.util.ArrayList;
import java.util.Scanner;

public class Traitement {

    /**
     * Method to display the list of withdrawal and total amount of withdrawal on a selected account
     */
    public static void displayAmountRetrait() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Index du compte");
        Integer indexCompte = myObj.nextInt();

        Comptes compte = arrayComptes.get(indexCompte);
        ArrayList<Operations> operationArray = compte.getOperationsArrayList();
        Double montant = 0.0;


        System.out.println("Liste des retraits du compte n° " + compte.getCode());

        for (Operations operations : operationArray) {
            if (operations.getType().equals("retrait") || operations.getType().equals("virement / retrait")) {
                System.out.println("Op n°" + operations.getNumero() + " ---- " + operations.getDate() + " - " + operations.getType() + " de " + operations.getMontant() + "€.");
                montant += operations.getMontant();
            }
        }

        if (montant > 0) {
            System.out.println("Le montant total est de " + montant + "€.");
        } else {
            System.out.println("Aucun retrait enregistré pour ce compte.");
        }

        System.out.println("---------------------------------------------------------");
    }


    /**
     * Method to display the list of transfers and total amount of transfers on a selected account
     */
    public static void displayAmountVersement() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Index du compte");
        Integer indexCompte = myObj.nextInt();

        Comptes compte = arrayComptes.get(indexCompte);
        ArrayList<Operations> operationArray = compte.getOperationsArrayList();
        Double montant = 0.0;


        System.out.println("Liste des versements du compte n° " + compte.getCode());

        for (Operations operations : operationArray) {
            if (operations.getType().equals("versement") || operations.getType().equals("virement / versement")) {
                System.out.println("Op n°" + operations.getNumero() + " ---- " + operations.getDate() + " - " + operations.getType() + " de " + operations.getMontant() + "€.");
                montant += operations.getMontant();
            }
        }

        if (montant > 0) {
            System.out.println("Le montant total est de " + montant + "€.");
        } else {
            System.out.println("Aucun versement enregistré pour ce compte.");
        }

        System.out.println("---------------------------------------------------------");
    }


    /**
     * Method to display list of operations of a selected account
     */
    public static void displayOperations() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Index du compte");
        Integer indexCompte = myObj.nextInt();

        Comptes compte = arrayComptes.get(indexCompte);
        ArrayList<Operations> operationArray = compte.getOperationsArrayList();

        System.out.println("Liste des opérations du compte n° " + compte.getCode());

        for (Operations operations : operationArray) {
            System.out.println("Op n°" + operations.getNumero() + " ---- " + operations.getDate() + " - " + operations.getType() + " de " + operations.getMontant() + "€.");
        }

        System.out.println("---------------------------------------------------------");
    }


    /**
     * Method to display list of accounts
     */
    public static void displayAccount() {
        arrayComptes.clear();
        Integer index = 0;

        if (!arrayComptesCourants.isEmpty()) {
            System.out.println("Listes comptes courants : ");
            for (Courants arrayComptesCourant : arrayComptesCourants) {
                arrayComptes.add(arrayComptesCourant);
                String solde = arrayComptesCourant.getSolde() < 0 ? "\u001B[31m" + arrayComptesCourant.getSolde() + "€" + "\u001B[0m" : arrayComptesCourant.getSolde() + "€";
                String displayAccounts = index + " - Compte n°" + arrayComptesCourant.getCode() + " - solde de " + solde + ". Découvert = " + arrayComptesCourant.getDecouvert() + "€.";
                System.out.println(displayAccounts);
                index++;
            }
        }
        System.out.println("\n");
        if (!arrayComptesEpargnes.isEmpty()) {
            System.out.println("Listes comptes épargnes : ");
            for (Epargnes arrayComptesEpargne : arrayComptesEpargnes) {
                // adding interest whenever account is displayed
                Double solde = Math.floor(arrayComptesEpargne.getSolde() + (arrayComptesEpargne.getSolde() * arrayComptesEpargne.getTauxInteret()/100));
                arrayComptesEpargne.setSolde(solde);

                // adding the saving account in the general account array and displaying
                arrayComptes.add(arrayComptesEpargne);
                String displayAccounts = index + " - Compte n°" + arrayComptesEpargne.getCode() + " - solde de " + arrayComptesEpargne.getSolde() + "€. Taux d'intérêts = " + arrayComptesEpargne.getTauxInteret() + "%.";
                System.out.println(displayAccounts);
                index++;
            }
        }

        System.out.println("---------------------------------------------------------");
    }


    /**
     * Method to display menu list of possibilities
     */
    public static Integer displayMenu() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("1. Créer un compte");
        System.out.println("2. Versement");
        System.out.println("3. Retrait");
        System.out.println("4. Virement");
        System.out.println("5. Consulter solde / Liste des comptes");
        System.out.println("6. Changer les intérêts d'un compte");
        System.out.println("7. Changer la limite du découvert d'un compte");
        System.out.println("8. Consulter la liste des opérations");
        System.out.println("9. Consulter le total des montants des versements");
        System.out.println("10. Consulter le total des montants des retraits");

        System.out.println("Chosir une option (Pour arrêter = 0)");

        return myObj.nextInt();
    }


    /**
     * List of arrayLists needed for main to function
     */
    private static final ArrayList<Courants> arrayComptesCourants = new ArrayList<>();
    private static final ArrayList<Epargnes> arrayComptesEpargnes = new ArrayList<>();
    private static final ArrayList<Comptes> arrayComptes = new ArrayList<>();

    public static void main(String[] arg) {
        Integer userChoiceMenu = displayMenu();

        if (arrayComptes.isEmpty() && userChoiceMenu !=1) {
            System.out.println("Veuillez créer un compte.");
            Traitement.main(arg);
        } else if (arrayComptes.size() < 2 && userChoiceMenu == 4) {
            System.out.println("Il faut créer deux comptes pour pouvoir utiliser cette méthode.");
            Traitement.main(arg);
        }

        if (arrayComptesCourants.isEmpty() && userChoiceMenu == 7) {
            System.out.println("Veuillez créer un compte courant.");
            Traitement.main(arg);
        } else if (arrayComptesEpargnes.isEmpty() && userChoiceMenu == 6) {
            System.out.println("Veuillez créer un compte épargne.");
            Traitement.main(arg);
        }

        switch (userChoiceMenu) {
            case 1:
                Comptes.createAccount(arrayComptes, arrayComptesCourants, arrayComptesEpargnes);
                break;
            case 2:
                displayAccount();
                Operations.versement(arrayComptes);
                break;
            case 3:
                displayAccount();
                Operations.retrait(arrayComptes, arrayComptesCourants);
                break;
            case 4:
                displayAccount();
                Operations.virement(arrayComptes, arrayComptesCourants);
                break;
            case 5:
                displayAccount();
                break;
            case 6:
                displayAccount();
                Operations.changeInterest(arrayComptes, arrayComptesEpargnes);
                break;
            case 7:
                displayAccount();
                Operations.changeOverdraft(arrayComptes, arrayComptesCourants);
                break;
            case 8:
                displayAccount();
                displayOperations();
                break;
            case 9:
                displayAccount();
                displayAmountVersement();
                break;
            case 10:
                displayAccount();
                displayAmountRetrait();
                break;
        }

        if (userChoiceMenu != 0) {
            Traitement.main(arg);
        }

    }
}
