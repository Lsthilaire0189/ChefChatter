package com.example.chefchatter.modele;

import java.util.ArrayList;
import java.util.List;

public class Modele {
    private List<Recette> recettes = new ArrayList<>();

    private List<Recette_Ingredient> ingredients = new ArrayList<>();

    public List<Recette> getRecettes() {
        return recettes;
    }

    public void setRecettes(List<Recette> recettes) {
        this.recettes = recettes;
    }

    Compte compteCourrant ;


    public List<Recette_Ingredient> getIngredients() {
        return ingredients;
    }

    public Compte getCompteCourrant() {
        return compteCourrant;
    }
    public void setCompteCourrant(Compte compteCourrant) {
        this.compteCourrant = compteCourrant;
    }

    public void setIngredients(List<Recette_Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Recette getRecetteSelonId(int id) {
        for (Recette recette : this.recettes)
            if (recette.getId() == id)
                return recette;
        return null;
    }

}
