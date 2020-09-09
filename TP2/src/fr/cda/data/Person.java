package fr.cda.data;

public class Person {
    private String nom;
    private String prenom;
    private Integer age;
    private Integer nbAccident;
    private Permis permis;
    private Fidelite fidelite;

    public Person(String nom, String prenom, Integer age, Integer nbAccident) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.nbAccident = nbAccident;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNbAccident() {
        return nbAccident;
    }

    public void setNbAccident(Integer nbAccident) {
        this.nbAccident = nbAccident;
    }

    public Permis getPermis() {
        return this.permis;
    }

    public void setPermis(Permis permis) {
        this.permis = permis;
    }

    public Fidelite getFidelite() {
        return this.fidelite;
    }

    public void setFidelite(Fidelite fidelite) {
        this.fidelite = fidelite;
    }
}
