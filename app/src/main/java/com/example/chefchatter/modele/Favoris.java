package com.example.chefchatter.modele;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Favoris {
    @JsonProperty("CompteEmail")
    private String email;

    @JsonProperty("RecetteId")
    private int recetteId;
    public Favoris() {
    }
    public Favoris(String email, int recetteId) {
        this.email = email;
        this.recetteId = recetteId;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getRecetteId() {
        return recetteId;
    }
    public void setRecetteId(int recetteId) {
        this.recetteId = recetteId;
    }

}
