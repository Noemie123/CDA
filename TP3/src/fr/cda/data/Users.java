package fr.cda.data;

import java.util.ArrayList;
import java.util.Scanner;

public class Users {
    /**
     * Attributes
     */
    protected String nom;
    protected String prenom;
    protected String identifiant;
    protected String motDePasse;

    protected ArrayList<Comptes> comptesUserList = new ArrayList<>();


    /**
     * Constructor
     */
    public Users (String surname, String name, String id, String password) {
        this.nom = surname;
        this.prenom = name;
        this.identifiant = id;
        this.motDePasse = password;
    }



    /**
     * Lists of getters and setters
     */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public ArrayList<Comptes> getComptesUserList() {
        return comptesUserList;
    }

    public void setComptesUserList(ArrayList<Comptes> comptesUserList) {
        this.comptesUserList = comptesUserList;
    }





    public static void createUser(ArrayList<Users> arrayUsers) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Nom de l'utilisateur");
        String nomUser = myObj.next();

        System.out.println("Prénom de l'utilisateur");
        String prenomUser = myObj.next();

        System.out.println("Identifiant de l'utilisateur");
        String idUser = myObj.next();

        Integer indexUser = findIndexUser(arrayUsers, idUser);

        if (indexUser == -1 ) {

            System.out.println("Mot de passe");
            String mdpUser1 = myObj.next();

            System.out.println("Répéter votre mot de passe");
            String mdpUser2 = myObj.next();

            if (mdpUser1.equals(mdpUser2)) {
                Users newUser = new Users(nomUser, prenomUser, idUser, mdpUser1);
                arrayUsers.add(newUser);

            } else {
                System.out.println("Les mots de passe ne correspondent pas.");
                createUser(arrayUsers);
            }

        } else {
            System.out.println("Identifiant déjà utilisé.");
            createUser(arrayUsers);
        }

    }


    public static Integer findIndexUser(ArrayList<Users> arrayUsers, String identifiant) {

        if (arrayUsers.size() > 0) {
            for (int i = 0; i < arrayUsers.size(); i++) {
                if (arrayUsers.get(i).getIdentifiant().equals(identifiant)) {
                    return i;
                }
            }
        }
        return -1;

    }

}
