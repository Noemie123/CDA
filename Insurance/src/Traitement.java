import fr.cda.data.Fidelite;
import fr.cda.data.Permis;
import fr.cda.data.Person;

import java.util.ArrayList;
import java.util.Scanner;


public class Traitement {

    /**
     * Method to set the correct price to pay according to color and years of insurance
     */
    private static void setPriceToPay(Person personne, String colorSet, Integer fideliteNb) {
        Integer prixPay = -1;

        if (!personne.getColor().equals("NA")) {
            switch (colorSet) {
                case ANSI_RED + "rouge" + ANSI_RESET:
                    prixPay = 200;
                    break;
                case ANSI_ORANGE + "orange" + ANSI_RESET:
                    prixPay = 150;
                    break;
                case ANSI_GREEN + "vert" + ANSI_RESET:
                    prixPay = 100;
                    break;
                case ANSI_BLUE + "bleu" + ANSI_RESET:
                    prixPay = 50;
                    break;
            }

            if (prixPay != -1) {
                Integer length = 0;
                if (fideliteNb > 5) {
                    length = 5;
                } else {
                    length = fideliteNb;
                }
                for (int i = 0; i < length; i++) {
                    prixPay = prixPay - (prixPay * 5 / 100);
                }
            }
        }

        personne.setPrixPayer(prixPay);
    }


    /**
     * Array list to receive persons created by users
     */
    private static ArrayList<Person> personArray = new ArrayList<>();

    public static ArrayList<Person> getPersonArray() {
        return personArray;
    }

    public static void setPersonArray(ArrayList<Person> personArray) {
        Traitement.personArray = personArray;
    }


    /**
     * Recursive method to ask user to create person
     */
    private static void askUser() {
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

        Person personToCreate = createPersons(nom, prenom, age, anneePermis, nbAcc, anneeFidelite);
        giveColor(personToCreate);
        setPriceToPay(personToCreate, personToCreate.getColor(), personToCreate.getFidelite().getNbAnneeFidelite());
        Traitement.getPersonArray().add(personToCreate);

        Scanner myObj2 = new Scanner(System.in);
        System.out.println("Pour continuer 'o'");
        String arret = myObj2.nextLine();

        if (arret.equals("o")) {
            askUser();
        }

    }


    /**
     * Method to create a new person with arguments needed
     */
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

    /**
     * Set color to the person with Person obj as an arg
     */
    private static void giveColor(Person perso) {
        int colorInt;

        if (perso.getAge() < 25) {
            if (perso.getPermis().getNbAnneeObtention() < 2) {
                if (perso.getNbAccident() == 0) {
                    colorInt = 0;
                } else {
                    colorInt = -1;
                }
            } else {
                if (perso.getNbAccident() == 0) {
                    colorInt = 1;
                } else if (perso.getNbAccident() == 1) {
                    colorInt = 0;
                } else {
                    colorInt = -1;
                }
            }

        } else {

            if (perso.getPermis().getNbAnneeObtention() < 2) {
                if (perso.getNbAccident() == 0) {
                    colorInt = 1;
                } else if (perso.getNbAccident() == 1) {
                    colorInt = 0;
                } else {
                    colorInt = -1;
                }
            } else {
                if (perso.getNbAccident() == 0) {
                    colorInt = 2;
                } else if (perso.getNbAccident() == 1) {
                    colorInt = 1;
                } else if (perso.getNbAccident() == 2) {
                    colorInt = 0;
                } else {
                    colorInt = -1;
                }
            }

        }

        if (perso.getFidelite().getNbAnneeFidelite() > 5) {
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

        perso.setColor(color);
    }

    /**
     * List of colors
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_ORANGE = "\u001B[91m";
    public static final String ANSI_BLUE = "\u001B[34m";


    /**
     * Main treatment : asking users and displaying results
     */

    public static void main(String[] arg) {

        //loop to let user create pers
        askUser();

        //getting array created after asking the user to create the persons
        ArrayList<Person> usersArray = Traitement.getPersonArray();

        // displaying results
        for (int i = 0; i < usersArray.size(); i++) {


            // Strings to display accordingly to the results
            String inst = usersArray.get(i).getNom() + " " + usersArray.get(i).getPrenom() + ", " + usersArray.get(i).getAge() + " ans, " + usersArray.get(i).getPermis().getNbAnneeObtention() + " ans de permis, " + usersArray.get(i).getNbAccident() + " accident(s), client depuis " + usersArray.get(i).getFidelite().getNbAnneeFidelite() + " ans. ";
            String inst2;
            if (usersArray.get(i).getPrixPayer() == -1 && usersArray.get(i).getColor().equals("NA")) {
                inst2 = "La personne n'est pas assurée.";
            } else {
                inst2 = "La personne bénéficie du contrat " + usersArray.get(i).getColor() + " : " + usersArray.get(i).getPrixPayer() + "€.";
            }
            System.out.println(inst + inst2);
        }


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

