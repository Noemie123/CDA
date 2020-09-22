import fr.cda.data.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Traitement {

    public static User currUser;



    private static Integer displayUserTypeMenu() {

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Client ?");
        System.out.println("2. Conseiller ?");

        Integer chosenNumber = myObj.nextInt();

        if (chosenNumber == 1 || chosenNumber == 2) {
            return chosenNumber;
        }

        return displayUserTypeMenu();
    }

    private static Integer displayCreateConnect() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Créer un compte utilisateur");
        System.out.println("2. Se connecter");

        Integer chosenNumber2 = myObj.nextInt();

        if (chosenNumber2 == 1 || chosenNumber2 == 2) {
            return chosenNumber2;
        }

        return displayCreateConnect();
    }




    private static boolean connect(Integer userType) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Identifiant ?");
        String idUser = myObj.next();
        Integer indexUser = User.findIndexUser(idUser);

        System.out.println("Mot de passe ?");
        String mdp = myObj.next();

        // if exists
        if (indexUser != -1) {
            Traitement.currUser = User.listeUsers.get(indexUser);

            // if correct type
            if ((currUser instanceof Client && userType == 1) || (currUser instanceof Conseiller && userType == 2)) {

                // if mdp is okay
                if (currUser.getMdp().equals(mdp)) {
                    return true;
                }

            } else {
                System.out.println("Choisissez le bon type.");
                return false;
            }
        }
        System.out.println("Identifiant incorrect.");
        return false;

    }


    private static void displayCustomerMenu() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Créer un compte");
        System.out.println("2. Versement");
        System.out.println("3. Retrait");
        System.out.println("4. Virement");
        System.out.println("5. Consulter solde / Liste des comptes");
        System.out.println("6. Consulter la liste des opérations");
        System.out.println("7. Consulter le total des montants des versements");
        System.out.println("8. Consulter le total des montants des retraits");


        System.out.println("Choisir une option (Pour arrêter = 0)");

        Integer userChoice = myObj.nextInt();

        switch (userChoice) {
            case 0:
                Traitement.main(null);
                break;
            case 1 :
                Compte.createAccount(Traitement.currUser.getIdentifiant());
                break;
            case 2:
                //TODO : versement
                break;
            case 3:
                //TODO : retrait
                break;
            case 4:
                //TODO : virement
                break;
            case 5:
                displayListeCompte(1);
                break;
            case 6:
                //TODO : liste des opérations
                break;
            case 7:
                //TODO : montants versements
                break;
            case 8:
                //TODO : montant retraits
                break;
            default:
                System.out.println("Ce choix n'existe pas.");
        }

        displayCustomerMenu();
    }


    private static void displayAdvisorMenu() {
//        Conseiller advisor = (Conseiller) currUser;

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Activer/désactiver un compte");
        System.out.println("2. Virement");
        System.out.println("3. Voir la Liste des comptes");
        System.out.println("4. Changer les intérêts d'un compte");
        System.out.println("5. Changer la limite du découvert d'un compte");

        System.out.println("Choisir une option (Pour arrêter = 0)");

        Integer userChoice = myObj.nextInt();

        switch (userChoice) {
            case 0:
                System.out.println("Vous êtes déconnecté.");
                String[] arg = new String[0];
                Traitement.main(arg);
                break;
            case 1 :
                Conseiller.activeAccount();
//                displayAdvisorMenu();
                break;
            case 2:
                //TODO : virement
                break;
            case 3:
                displayListeCompte(2);
                break;
            case 4:
                Conseiller.changeInterest();
                break;
            case 5:
                Conseiller.changeOverdraft();
                break;
            default:
                System.out.println("Ce choix n'existe pas.");
        }

        displayAdvisorMenu();
    }



    private static void displayListeCompte(Integer userType) {
        ArrayList<Compte> listeDesComptes = new ArrayList<>();

        if (userType == 1) {
            for (Compte compt : Compte.listeComptes) {
                if (compt.getIdentifiantUser().equals(Traitement.currUser.getIdentifiant()) && compt.isActivated()) {
                    listeDesComptes.add(compt);
                }
            }
        } else if (userType == 2) {
            listeDesComptes = Compte.listeComptes;
        }

        if (listeDesComptes.isEmpty()) {
            System.out.println("Aucun compte à afficher.");
        } else {
            for (Compte compteUnit : listeDesComptes) {
                User accountOwner = User.listeUsers.get(User.findIndexUser(compteUnit.getIdentifiantUser()));
                System.out.println("Compte n° " + compteUnit.getCode() + ", solde : " + compteUnit.getSolde() + "€, " + (compteUnit instanceof Courant ? "Découvert autorisé : " + ((Courant) compteUnit).getDecouvert() + "€" : "Taux d'intérêts : " + ((Epargne) compteUnit).getTauxInteret() + "%") + (userType == 2 ? ", appartenant à " + accountOwner.getSurname() + " " + accountOwner.getFirstname() + ", Activé = " + (compteUnit.isActivated() ? "oui" : "non") : ""));
            }
        }
    }


    public static void main(String[] arg) {
        // select type of user menu
        Integer userType = displayUserTypeMenu();

        // select create user account or connect
        Integer createConnectChoice = displayCreateConnect();

        // if create user account
        if (createConnectChoice == 1) {
            User.createUser(userType);
            Traitement.main(arg);

            // if user wants to connect
        } else if (createConnectChoice == 2) {
            boolean connected = connect(userType);


            // if connection is okay
            if (connected) {
                System.out.println("Connecté");
//                String identifiantUser = Traitement.currUser.getIdentifiant();

                // if user is a customer
                if (userType == 1) {
                    displayCustomerMenu();


                    // if user is an advisor
                } else if (userType == 2) {
                    displayAdvisorMenu();

                }

                // if connection not okay
            } else {
                System.out.println("Pas connecté");
                Traitement.main(arg);
            }

            // if uncorrect choice between create user & connect
        } else {
            Traitement.main(arg);
        }

    }
}
