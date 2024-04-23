package com.example.chefchatter.modele;

import com.example.chefchatter.modele.Recette;

import java.util.ArrayList;
import java.util.List;

public class Modele {
    private List<Recette> recettes = new ArrayList<>();

    private List<String> ingredients = new ArrayList<>();

    public List<Recette> getRecettes() {
        return recettes;
    }

    public void setRecettes(List<Recette> recettes) {
        this.recettes = recettes;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Recette getRecetteSelonId(int id) {
        for (Recette recette : this.recettes)
            if (recette.getId() == id)
                return recette;
        return null;
    }

}
