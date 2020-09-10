package fr.cda.data;

import java.util.ArrayList;
import java.util.Scanner;

public class Operations {
    /**
     * Attributes
     */
    private Integer numero;
    private String date;
    private Double montant;
    private String type;


    /**
     * Lists of getters and setters
     */
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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



    /**
     * Method to credit a selected account of an amount given by user
     * Including saving operations into an array
     */
    public static void versement(ArrayList<Comptes> arrayComptes) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Index du compte");
        Integer indexCompteChoisi = myObj.nextInt();

        System.out.println("Montant de versement");
        Double montantVersement = myObj.nextDouble();

        Comptes compteChoisi = arrayComptes.get(indexCompteChoisi);
        Double soldePrecedent = compteChoisi.getSolde();
        String soldePrec = soldePrecedent < 0 ? "\u001B[31m" + soldePrecedent + "€" + "\u001B[0m" : soldePrecedent + "€";
        compteChoisi.setSolde(soldePrecedent + montantVersement);
        Double nouveauSolde = compteChoisi.getSolde();

        // save operations into array
        Operations op = new Operations();
        op.setDate("10/09/2020");
        op.setMontant(montantVersement);
        op.setNumero(144754524);
        op.setType("versement");

        compteChoisi.getOperationsArrayList().add(op);

        System.out.println("Le compte a bien été crédité de " + montantVersement + "€. Nouveau solde : " + nouveauSolde + "€ (ancien : " + soldePrec + ").");
    }



    /**
     * Method to debit a selected account of an amount given by user - checking if withdrawal is possible (overdraft)
     * Including saving operations into an array
     */
    public static void retrait(ArrayList<Comptes> arrayComptes, ArrayList<Courants> arrayComptesCourants) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Index du compte");
        Integer indexCompteChoisi = myObj.nextInt();

        System.out.println("Montant à débiter");
        Double montantRetrait = myObj.nextDouble();

        Comptes compteChoisi = arrayComptes.get(indexCompteChoisi);
        Double soldePrecedent = compteChoisi.getSolde();

        boolean validate = false;


        Integer codeCompteChoisi = arrayComptes.get(indexCompteChoisi).getCode();
        Integer indexOfCompteCourant = 0;

        if (arrayComptes.get(indexCompteChoisi).getType() == 1) {
            // find index of chosen account from array compte courants
            for (int i = 0; i < arrayComptesCourants.size(); i++) {
                if (arrayComptesCourants.get(i).getCode().equals(codeCompteChoisi)) {
                    indexOfCompteCourant = i;
                }
            }

            // check not more than overdraft if checking account
            Double decouvertCompteChoisi = arrayComptesCourants.get(indexOfCompteCourant).getDecouvert();
            if ((arrayComptes.get(indexCompteChoisi).getSolde()-montantRetrait) > decouvertCompteChoisi)
            {
                compteChoisi.setSolde(soldePrecedent - montantRetrait);
                Double nouveauSolde = compteChoisi.getSolde();
                String solde = nouveauSolde < 0 ? "\u001B[31m" + nouveauSolde + "€" + "\u001B[0m" : nouveauSolde + "€";
                System.out.println("Le compte a bien été débité de " + montantRetrait + "€. Nouveau solde : " + solde + " (ancien : " + soldePrecedent + "€).");
                validate = true;
            } else {
                System.out.println("Opération impossible.");
            }
        } else {
            if ((arrayComptes.get(indexCompteChoisi).getSolde()-montantRetrait) >= 0)
            {
                compteChoisi.setSolde(soldePrecedent - montantRetrait);
                Double nouveauSolde = compteChoisi.getSolde();
                System.out.println("Le compte a bien été débité de " + montantRetrait + "€. Nouveau solde : " + nouveauSolde + "€ (ancien : " + soldePrecedent + "€).");
                validate = true;
            } else {
                System.out.println("Opération impossible.");
            }
        }

        if (validate) {
            // save operations into array
            Operations op = new Operations();
            op.setDate("10/09/2020");
            op.setMontant(montantRetrait);
            op.setNumero(144754524);
            op.setType("retrait");

            compteChoisi.getOperationsArrayList().add(op);
        }
    }



    /**
     * Method to credit a selected account of an amount given by user and debit another account - checking if withdrawal is possible (overdraft)
     * Including saving operations into an array
     */
    public static void virement(ArrayList<Comptes> arrayComptes, ArrayList<Courants> arrayComptesCourants) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Index du compte 1 (à débiter)");
        Integer indexCompteChoisi = myObj.nextInt();

        System.out.println("Index du compte 2 (à créditer)");
        Integer indexCompteChoisi2 = myObj.nextInt();

        System.out.println("Montant de virement");
        Double montantVirement = myObj.nextDouble();

        boolean validate = false;


        Integer codeCompteChoisi = arrayComptes.get(indexCompteChoisi).getCode();
        Integer indexOfCompteCourant = 0;

        Comptes compteChoisi = arrayComptes.get(indexCompteChoisi);
        Comptes compteChoisi2 = arrayComptes.get(indexCompteChoisi2);

        if (arrayComptes.get(indexCompteChoisi).getType() == 1) {
            // find index of chosen account from array compte courants
            for (int i = 0; i < arrayComptesCourants.size(); i++) {
                if (arrayComptesCourants.get(i).getCode().equals(codeCompteChoisi)) {
                    indexOfCompteCourant = i;
                }
            }

            // check not more than overdraft if checking account
            Double decouvertCompteChoisi = arrayComptesCourants.get(indexOfCompteCourant).getDecouvert();
            if ((arrayComptes.get(indexCompteChoisi).getSolde()-montantVirement) > decouvertCompteChoisi)
            {

                Double soldePrecedent = compteChoisi.getSolde();
                compteChoisi.setSolde(soldePrecedent - montantVirement);

                Double soldePrecedent2 = compteChoisi2.getSolde();
                compteChoisi2.setSolde(soldePrecedent2 + montantVirement);

                System.out.println("Le virement de " + montantVirement + "€ a été effectué.");
                validate = true;
            } else {
                System.out.println("Opération impossible.");
            }
        } else {
            if ((arrayComptes.get(indexCompteChoisi).getSolde()-montantVirement) >= 0)
            {
                Double soldePrecedent = compteChoisi.getSolde();
                compteChoisi.setSolde(soldePrecedent - montantVirement);


                Double soldePrecedent2 = compteChoisi2.getSolde();
                compteChoisi2.setSolde(soldePrecedent2 + montantVirement);

                System.out.println("Le virement de " + montantVirement + "€ a été effectué.");
                validate = true;
            } else {
                System.out.println("Opération impossible.");
            }
        }

        if (validate) {
            // save operations into array
            Operations op2 = new Operations();
            op2.setDate("10/09/2020");
            op2.setMontant(montantVirement);
            op2.setNumero(144754524);
            op2.setType("virement / versement");

            compteChoisi2.getOperationsArrayList().add(op2);



            Operations op = new Operations();
            op.setDate("10/09/2020");
            op.setMontant(montantVirement);
            op.setNumero(144754524);
            op.setType("virement / retrait");

            compteChoisi.getOperationsArrayList().add(op);
        }

    }


    /**
     * Method to let user change the interest rate of a selected account - if account is a saving account
     * Including saving operations into an array
     */
    public static void changeInterest(ArrayList<Comptes> arrayComptes, ArrayList<Epargnes> arrayComptesEpargnes) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Index du compte");
        Integer indexCompte = myObj.nextInt();

        Comptes compte = arrayComptes.get(indexCompte);

        if (compte.getType() == 1) {
            System.out.println("Veuillez sélectionner un compte épargne.");
            changeInterest(arrayComptes, arrayComptesEpargnes);
        } else {
            Integer codeCompteChoisi = arrayComptes.get(indexCompte).getCode();

            Integer indexOfCompteEpargne = -1;
            for (int i = 0; i < arrayComptesEpargnes.size(); i++) {
                if (arrayComptesEpargnes.get(i).getCode().equals(codeCompteChoisi)) {
                    indexOfCompteEpargne = i;
                }
            }

            Epargnes compteChoisi = arrayComptesEpargnes.get(indexOfCompteEpargne);
            Double ancienTaux = compteChoisi.getTauxInteret();

            Scanner myObj2 = new Scanner(System.in);
            System.out.println("Ancien taux : " + ancienTaux + "%. Nouveau taux ?");
            Double nouveauTaux = myObj2.nextDouble();

            compteChoisi.setTauxInteret(nouveauTaux);
            System.out.println("Le taux est désormais de " + nouveauTaux + "%.");
        }
    }


    /**
     * Method to let user change the interest rate of a selected account - if account is a checking account
     * Including saving operations into an array
     */
    public static void changeOverdraft(ArrayList<Comptes> arrayComptes, ArrayList<Courants> arrayComptesCourants) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Index du compte");
        Integer indexCompte = myObj.nextInt();

        Comptes compte = arrayComptes.get(indexCompte);

        if (compte.getType() == 2) {
            System.out.println("Veuillez sélectionner un compte courant.");
            changeOverdraft(arrayComptes, arrayComptesCourants);
        } else {
            Integer codeCompteChoisi = arrayComptes.get(indexCompte).getCode();

            Integer indexOfCompteCourant = -1;
            for (int i = 0; i < arrayComptesCourants.size(); i++) {
                if (arrayComptesCourants.get(i).getCode().equals(codeCompteChoisi)) {
                    indexOfCompteCourant = i;
                }
            }

            Courants compteChoisi = arrayComptesCourants.get(indexOfCompteCourant);
            Double ancienDecouvert = compteChoisi.getDecouvert();

            Scanner myObj2 = new Scanner(System.in);
            System.out.println("Ancien découvert : " + ancienDecouvert + "€. Nouveau découvert ?");
            Double nouveauDecouvert = myObj2.nextDouble();

            compteChoisi.setDecouvert(nouveauDecouvert);
            System.out.println("Le découvert est désormais de " + nouveauDecouvert + "€.");
        }
    }
}
