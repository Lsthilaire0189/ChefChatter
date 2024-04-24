package com.example.chefchatter.modele;

public class CompteMessage {
    String message;
    Compte compte;

    public CompteMessage(String message, Compte compte) {
        this.message = message;
        this.compte = compte;
    }
    public String getMessage() {
        return message;
    }
    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
