package com.example.chefchatter.dao;

import com.example.chefchatter.modele.Recette_Ingredient;

import java.util.List;

public interface IngredientsCallback {
    public void onIngredientsReceived(List<Recette_Ingredient> ingredients);
}
