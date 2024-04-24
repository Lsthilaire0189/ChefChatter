package com.example.chefchatter.modele;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Compte implements Serializable {

    @JsonProperty("prenom")
    private String prenom;

    @JsonProperty("nomDeFamille")
    private String nom;

    @JsonProperty("email")
    private String email;
    @JsonProperty("username")
    private String nomUtilisateur;
    @JsonProperty("dateDeNaissance")
    private String dateNaissance;
    @JsonProperty("password")
    private String password;

    public Compte(String prenom, String nom, String courriel, String nomUtilisateur, String dateNaissance, String mdp) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = courriel;
        this.dateNaissance = dateNaissance;
        this.nomUtilisateur = nomUtilisateur;
        this.password = mdp;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getCourriel() {
        return email;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getMdp() {
        return password;
    }

}
