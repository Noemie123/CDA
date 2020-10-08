package fr.cda.data;

import fr.cda.data.files.filesManagement.FilesManager;

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
        ArrayList<Object> usersListAll = FilesManager.readAll("clients"); // find index from external file
        usersListAll.addAll(FilesManager.readAll("conseillers"));


        if (!usersListAll.isEmpty()) {
            for (int i = 0; i < usersListAll.size(); i++) {
                User userInst = (User) usersListAll.get(i);
                if (userInst.getIdentifiant().equals(idUser)) {
                    return i;
                }
            }
        }

        return -1;
    }


    /**
     * Method to find index of an account in the ArrayList of all accounts thanks to its code
     */
    public static Integer findIndexCompte(String codeCompte) {
        ArrayList<Object> compteListAll = FilesManager.readAll("comptes"); // find index from external file

        if (!compteListAll.isEmpty()) {
            for (int i = 0; i < compteListAll.size(); i++) {
                Compte compteInst = (Compte) compteListAll.get(i);
                if (compteInst.getCode().equals(codeCompte)) {
                    return i;
                }
            }
        }

        return -1; // if index not found means does not exists then return -1
    }


    /**
     * Method to find index of an account in the ArrayList of accounts of a specific user
     */
    public static Integer findIndexCompte(String codeCompte, User currentUser) {
        ArrayList<Object> compteListAll = FilesManager.readAll("comptes");

        if (!compteListAll.isEmpty()) {
            for (int i = 0; i < compteListAll.size(); i++) {
                Compte compteInst = (Compte) compteListAll.get(i);
                if (compteInst.getCode().equals(codeCompte) && compteInst.getOwner().getIdentifiant().equals(currentUser.getIdentifiant())) {
                    return i;
                }
            }
        }

        return -1; // if index not found means does not exists or not the owner's then return -1
    }


    public static boolean findActivatedAccount(User currentUser) {
        ArrayList<Object> accountListObj = FilesManager.readAll("comptes");

        for (Object accountObj : accountListObj) {
            Compte accountInst = (Compte) accountObj;
            if (accountInst.activated && currentUser.getIdentifiant().equals(accountInst.owner.getIdentifiant())) {
                return true;
            }
        }

        return false;
    }

}
