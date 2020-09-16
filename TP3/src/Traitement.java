import fr.cda.data.*;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static fr.cda.data.Users.createUser;
import static fr.cda.data.Users.findIndexUser;


public class Traitement {

    private static final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Method to display the list of withdrawal and total amount of withdrawal of a selected account
     */
    private static void displayAmountRetrait() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte");
        String numeroCompte = myObj.next();
        Integer indexCompte = Comptes.findIndex(arrayComptes, numeroCompte);

        if (indexCompte != -1) {
            Comptes compte = arrayComptes.get(indexCompte);
            ArrayList<Operations> operationArray = compte.getOperationsArrayList();
            Double montant = 0.0;


            System.out.println("Liste des retraits du compte n° " + compte.getCode());

            for (Operations operations : operationArray) {
                if (operations.getType().equals("retrait") || operations.getType().equals("virement / retrait")) {
                    System.out.println("Op n°" + operations.getNumero() + " ---- " + format.format(operations.getDate()) + " - " + operations.getType() + " de " + operations.getMontant() + "€.");
                    montant += operations.getMontant();
                }
            }

            if (montant > 0) {
                System.out.println("Le montant total est de " + montant + "€.");
            } else {
                System.out.println("Aucun retrait enregistré pour ce compte.");
            }

            System.out.println("---------------------------------------------------------");
        } else {
            System.out.println("Compte introuvable.");
        }
    }


    /**
     * Method to display the list of transfers and total amount of transfers on a selected account
     */
    private static void displayAmountVersement() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte");
        String numeroCompte = myObj.next();
        Integer indexCompte = Comptes.findIndex(arrayComptes, numeroCompte);

        if (indexCompte != -1) {
            Comptes compte = arrayComptes.get(indexCompte);
            ArrayList<Operations> operationArray = compte.getOperationsArrayList();
            Double montant = 0.0;


            System.out.println("Liste des versements du compte n° " + compte.getCode());

            for (Operations operations : operationArray) {
                if (operations.getType().equals("versement") || operations.getType().equals("virement / versement")) {
                    System.out.println("Op n°" + operations.getNumero() + " ---- " + format.format(operations.getDate()) + " - " + operations.getType() + " de " + operations.getMontant() + "€.");
                    montant += operations.getMontant();
                }
            }

            if (montant > 0) {
                System.out.println("Le montant total est de " + montant + "€.");
            } else {
                System.out.println("Aucun versement enregistré pour ce compte.");
            }

            System.out.println("---------------------------------------------------------");
        } else {
            System.out.println("Compte introuvable.");
        }
    }


    /**
     * Method to display list of operations of a selected account
     */
    private static void displayOperations() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("N° du compte");
        String numeroCompte = myObj.next();
        Integer indexCompte = Comptes.findIndex(arrayComptes, numeroCompte);

        if (indexCompte != -1) {
            Comptes compte = arrayComptes.get(indexCompte);
            ArrayList<Operations> operationArray = compte.getOperationsArrayList();

            System.out.println("Liste des opérations du compte n° " + compte.getCode());

            for (Operations operations : operationArray) {
                System.out.println("Op n°" + operations.getNumero() + " ---- " + format.format(operations.getDate()) + " - " + operations.getType() + " de " + operations.getMontant() + "€.");
            }

            System.out.println("---------------------------------------------------------");
        } else {
            System.out.println("Compte introuvable.");
        }
    }


    /**
     * Method to display list of accounts
     */
    private static void displayAccount() {
        String displayAccounts = "";
        for (int i = 0; i < arrayComptes.size(); i++) {
            if (arrayComptes.get(i).getType() == 1) {
                Integer indexCourant = Comptes.findIndexCourants(arrayComptesCourants, arrayComptes.get(i).getCode());
                String solde = arrayComptes.get(i).getSolde() < 0 ? "\u001B[31m" + arrayComptes.get(i).getSolde() + "€" + "\u001B[0m" : arrayComptes.get(i).getSolde() + "€";
                displayAccounts = "Compte Courant n°" + arrayComptes.get(i).getCode() + " - solde de " + solde + ". Découvert autorisé = " + arrayComptesCourants.get(indexCourant).getDecouvert() + "€.";

            } else {
                Integer indexEpargne = Comptes.findIndexEpargnes(arrayComptesEpargnes, arrayComptes.get(i).getCode());
                // adding interest whenever account is displayed
                Double solde = Math.floor(arrayComptesEpargnes.get(indexEpargne).getSolde() + (arrayComptesEpargnes.get(indexEpargne).getSolde() * arrayComptesEpargnes.get(indexEpargne).getTauxInteret()/100));
                arrayComptesEpargnes.get(indexEpargne).setSolde(solde);

                // adding the saving account in the general account array and displaying
                displayAccounts = "Compte Épargne n°" + arrayComptes.get(i).getCode() + " - solde de " + arrayComptes.get(i).getSolde() + "€. Taux d'intérêts = " + arrayComptesEpargnes.get(indexEpargne).getTauxInteret() + "%.";

            }

            System.out.println(displayAccounts);
        }

        System.out.println("---------------------------------------------------------");
    }



    /**
     * Method to display menu list of possibilities
     */
    private static Integer displayMenu() {
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

        System.out.println("Choisir une option (Pour arrêter = 0)");

        return myObj.nextInt();
    }


    /**
     * Method to check before connect
     */

    private static boolean checkConnect() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        boolean correctMdp = false;

        System.out.println("Identifiant ?");
        String identifiantInput = myObj.next();
        Integer indexUser = findIndexUser(arrayUsers, identifiantInput);

        System.out.println("Mot de passe ?");
        String mdpInput = myObj.next();
        String saveMdp = indexUser == -1 ? "notFound" : arrayUsers.get(indexUser).getMotDePasse();

        if (saveMdp.equals(mdpInput)) {
            correctMdp = true;
        }

        if (indexUser != -1 && correctMdp) {
            System.out.println("Connexion réussie.");
            return true;
        } else {
            System.out.println("Identifiants incorrects.");
        }


        return false;
    }


    /**
     * Method to display menu list of possibilities
     */
    private static boolean displayBaseMenu() {
        // base menu
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("1. Créer un utilisateur.");
        System.out.println("2. Se connecter.");
        Integer choixMenu = myObj.nextInt();

        if (choixMenu == 1) {
            createUser(arrayUsers);
            System.out.println(arrayUsers.get(0).getIdentifiant() + ", vous pouvez maintenant vous connecter.");
        } else if (choixMenu == 2 && arrayUsers.size() <=0) {
            System.out.println("Veuillez créer au moins un utilisateur.");
        } else if (choixMenu == 2) {
            System.out.println("espace connexion");
            return checkConnect();
        }

        return false;
    }

    /**
     * List of arrayLists needed for main to function
     */
    private static final ArrayList<Courants> arrayComptesCourants = new ArrayList<>();
    private static final ArrayList<Epargnes> arrayComptesEpargnes = new ArrayList<>();
    private static final ArrayList<Comptes> arrayComptes = new ArrayList<>();
    private static final ArrayList<Users> arrayUsers = new ArrayList<>();

    public static void main(String[] arg) {
        boolean isIdentified = true;
        String[] argTable = new String[1];


        if (argTable[0] == null || !argTable[0].equals("in")) {
            isIdentified = displayBaseMenu();
        }


        if (isIdentified) {
            argTable[0] = "in";
            Integer userChoiceMenu = displayMenu();

            if (userChoiceMenu != 0 && arrayComptes.isEmpty() && userChoiceMenu != 1) {
                System.out.println("Veuillez créer un compte.");
                Traitement.main(argTable);
            }

            switch (userChoiceMenu) {
                case 0:
                    System.out.println("Vous êtes déconnecté.");
                    argTable[0] = "out";
                    Traitement.main(arg);
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
                default:
                    if (userChoiceMenu != 0) {
                        System.out.println("Choix incorrect. Veuillez recommencer.");
                    }
            }

            if (userChoiceMenu != 0) {
                Traitement.main(argTable);
            }

        } else {
            Traitement.main(arg);
        }

    }
}
