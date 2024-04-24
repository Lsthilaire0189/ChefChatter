package com.example.chefchatter.modele;

public class Filtre {
    private String choixOrigine;
    private  String choixRegime;
    private String choixType;
    private String[] choixIngredients;

    public Filtre(String choixOrigine, String choixRegime, String choixType, String[] choixIngredients) {
        this.choixOrigine = choixOrigine;
        this.choixRegime = choixRegime;
        this.choixType = choixType;
        this.choixIngredients = choixIngredients;
    }
    public String getChoixOrigine() {
        return choixOrigine;
    }

    public String getChoixRegime() {
        return choixRegime;
    }

    public String getChoixType() {
        return choixType;
    }

    public String[] getChoixIngredients() {
        return choixIngredients;
    }
}
