package com.example.chefchatter.modele;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Recette_Ingredient {

    @JsonProperty("recette")
    private int recette;
    @JsonProperty("ingredient")
    private String ingredient;

    @JsonProperty("quantite")
    private String quantite;

    public Recette_Ingredient() {
    }

    public Recette_Ingredient(int recette, String ingredient, String quantite) {
        this.recette = recette;
        this.ingredient = ingredient;
        this.quantite = quantite;
    }

    public int getRecette() {
        return recette;
    }

    public void setRecette(int recette) {
        this.recette = recette;
    }


    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }
}
