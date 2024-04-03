package com.example.chefchatter;

public class Compte {

    private String prenom;
    private String nom;
    private String courriel;
    private String nomUtilisateur;
    private String dateNaissance;
    private String mdp;

    public Compte(String prenom, String nom, String courriel, String nomUtilisateur, String dateNaissance, String mdp) {
        this.prenom = prenom;
        this.nom = nom;
        this.courriel = courriel;
        this.dateNaissance = dateNaissance;
        this.nomUtilisateur = nomUtilisateur;
        this.mdp = mdp;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getMdp() {
        return mdp;
    }

}
