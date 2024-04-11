package com.example.chefchatter;

public class Filtre {
    private String choixOrigine;
    private  String choixRegime;
    private String choixType;

    public Filtre(String choixOrigine, String choixRegime, String choixType) {
        this.choixOrigine = choixOrigine;
        this.choixRegime = choixRegime;
        this.choixType = choixType;
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
}
