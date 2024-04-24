package com.example.chefchatter.presentateur;

import android.app.Activity;

import com.example.chefchatter.dao.DAO;
import com.example.chefchatter.dao.IngredientsCallback;
import com.example.chefchatter.modele.Recette_Ingredient;
import com.example.chefchatter.modele.Modele;
import com.example.chefchatter.modele.ModeleManager;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class PresentateurIngredients {

    private Activity activity;

    private Modele modele;

    public PresentateurIngredients(Activity activity) {
        this.activity = activity;
        this.modele = ModeleManager.getInstance();
    }

    public List<Recette_Ingredient> getIngredients() {
        return modele.getIngredients();
    }

    public void obtenirIngredients(Integer idRecette, IngredientsCallback callback) {
        new Thread(){
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    List<Recette_Ingredient> liste = null;
                    liste = DAO.getIngredientsSelonRecette(idRecette);
                    modele.setIngredients(liste);
                    callback.onIngredientsReceived(liste);

                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }

    public int getNbIngredients() {
        return modele.getIngredients().size();
    }

    public Recette_Ingredient getIngredient(int position) {
        return modele.getIngredients().get(position);
    }
}
