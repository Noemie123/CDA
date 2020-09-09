import fr.cda.data.Fidelite;
import fr.cda.data.Permis;
import fr.cda.data.Person;

import java.util.ArrayList;
import java.util.Scanner;



public class Traitement {

    private static ArrayList<Person> askUser(ArrayList<Person> persArray) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Entrez nom");
        String nom = myObj.nextLine();

        System.out.println("Entrez prénom");
        String prenom = myObj.nextLine();

        System.out.println("Entrez age");
        Integer age = myObj.nextInt();

        System.out.println("Entrez nb années permis");
        Integer anneePermis = myObj.nextInt();

        System.out.println("Entrez nb accidents");
        Integer nbAcc = myObj.nextInt();

        System.out.println("Entrez nb années fidélité");
        Integer anneeFidelite = myObj.nextInt();

        Person pers = createPersons(nom, prenom, age, anneePermis, nbAcc, anneeFidelite);
        persArray.add(pers);

        System.out.println("Pour continuer 'oui' / arreter 'non'");
        String arret = myObj.nextLine();

        if (arret.equals("oui")) {
            askUser(persArray);
        } else {
            return persArray;
        }

        return persArray;

    }

    private static Person createPersons(String nom, String prenom, Integer age, Integer nbAnneePermis, Integer nbAccident, Integer nbAnneeFidelite) {
        Person personne = new Person(nom, prenom, age, nbAccident);

        Permis permisPersonne = new Permis();
        permisPersonne.setNbAnneeObtention(nbAnneePermis);
        personne.setPermis(permisPersonne);

        Fidelite fidelitePersonne = new Fidelite();
        fidelitePersonne.setNbAnneeFidelite(nbAnneeFidelite);
        personne.setFidelite(fidelitePersonne);

        return personne;
    }

    private static String giveColor (Integer age, Integer nbAnneePermis, Integer nbAccident, Integer nbAnneeFidelite) {
        int colorInt = 0;

        if (age < 25) {
            if (nbAnneePermis < 2) {
                if (nbAccident == 0) {
                    colorInt = 0;
                } else {
                    colorInt = -1;
                }
            } else {
                if (nbAccident == 0) {
                    colorInt = 1;
                } else if (nbAccident == 1) {
                    colorInt = 0;
                } else {
                    colorInt = -1;
                }
            }

        } else {

            if (nbAnneePermis < 2) {
                if (nbAccident == 0) {
                    colorInt = 1;
                } else if (nbAccident == 1) {
                    colorInt = 0;
                }  else {
                    colorInt = -1;
                }
            } else {
                if (nbAccident == 0) {
                    colorInt = 2;
                } else if (nbAccident == 1) {
                    colorInt = 1;
                } else if (nbAccident == 2) {
                    colorInt = 0;
                } else {
                    colorInt = -1;
                }
            }

        }

        if (nbAnneeFidelite > 5) {
            colorInt++;
        }

        String color;
        switch (colorInt) {
            case 0:
                color = ANSI_RED + "rouge" + ANSI_RESET;
                break;
            case 1:
                color = ANSI_ORANGE + "orange" + ANSI_RESET;
                break;
            case 2:
                color = ANSI_GREEN + "vert" + ANSI_RESET;
                break;
            case 3:
                color = ANSI_BLUE + "bleu" + ANSI_RESET;
                break;
            default:
                color = "NA";

        }

        return color;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_ORANGE = "\u001B[91m";
    public static final String ANSI_BLUE = "\u001B[34m";


    public static void main(String[] arg) {


        ArrayList<Person> arrPersonnes = new ArrayList<>();

            //loop to let user create pers
        askUser(arrPersonnes);

        // first version
//        Person perso1 = new Person("Bennet", "Jane", 28, 0);
//
//        Permis permisPerso1 = new Permis();
//        permisPerso1.setNbAnneeObtention(10);
//        perso1.setPermis(permisPerso1);
//
//        Fidelite fidelitePerso1 = new Fidelite();
//        fidelitePerso1.setNbAnneeFidelite(7);
//        perso1.setFidelite(fidelitePerso1);
//
//        String colorPerso1 = giveColor(perso1.getAge(), perso1.getPermis().getNbAnneeObtention(), perso1.getNbAccident(), perso1.getFidelite().getNbAnneeFidelite());
//        Integer prixPay;
//
//        switch (colorPerso1){
//            case ANSI_RED + "rouge" + ANSI_RESET:
//                prixPay = 200;
//                break;
//            case ANSI_ORANGE + "orange" + ANSI_RESET:
//                prixPay = 150;
//                break;
//            case ANSI_GREEN + "vert" + ANSI_RESET:
//                prixPay = 100;
//                break;
//            case  ANSI_BLUE + "bleu" + ANSI_RESET:
//                prixPay = 50;
//                break;
//            default:
//                prixPay = -1;
//        }
//
//
//        System.out.print(perso1.getNom() + " " + perso1.getPrenom() + " a " + perso1.getPermis().getNbAnneeObtention() + " de permis et est assuré depuis " + perso1.getFidelite().getNbAnneeFidelite() + " ans.");
//        if (colorPerso1 != "NA") {
//            System.out.print("Couleur du contrat = " + colorPerso1 + ". Le prix à payer est de " + prixPay + "€");
//        } else {
//            System.out.print("Le client n'est pas assuré.");
//        }

    }

}

