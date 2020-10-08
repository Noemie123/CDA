package fr.cda.data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Finder {

    /**************************
     *                        *
     *        METHODS         *
     *                        *
     **************************/

    /**
     * Method to find index of a user in the ArrayList of all users thanks to its id
     */
    public static Integer findIndexUser(String idUser) {
        for (int i = 0; i < Banque.listeUsers.size(); i++) {
            if (Banque.listeUsers.get(i).getIdentifiant().equals(idUser)) {
                return i;
            }
        }

        return -1;
    }


    /**
     * Method to find index of an account in the ArrayList of all accounts thanks to its code
     */
    public static Integer findIndexCompte(String codeCompte) {
        for (int i = 0; i < Banque.getListeComptes().size(); i++) {
            if (Banque.getListeComptes().get(i).getCode().equals(codeCompte)) {
                return i;
            }
        }

        return -1; // if index not found means does not exists then return -1
    }


    /**
     * Method to find index of an account in the ArrayList of accounts of a specific user
     */
//    public static Integer findIndexCompte(String codeCompte, User currentUser) {
//        for (int i = 0; i < ((Client) currentUser).getListeComptesClient().size(); i++) {
//            if (((Client) currentUser).getListeComptesClient().get(i).getCode().equals(codeCompte)) {
//                return i;
//            }
//        }
//
//        return -1; // if index not found means does not exists then return -1
//    }


    public static boolean findActivatedAccount (ArrayList<Compte> arrComptes) {
        for (Compte compteInst : arrComptes) {
            if (compteInst.activated) {
                return true;
            }
        }


        return false;
    }

    public static ArrayList<Compte> findCustomerActivatedAccount (Client owner) {
        ArrayList<Compte> arrComptesActivated = new ArrayList<>();

        for (Compte comptInst : Banque.getListeComptes()) {
            if (comptInst.activated && comptInst.getOwner().identifiant.equals(owner.identifiant)) {
                arrComptesActivated.add(comptInst);
            }
        }

        return arrComptesActivated;
    }

}
