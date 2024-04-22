package com.example.chefchatter.presentateur;

import android.app.Activity;

import com.example.chefchatter.dao.DAO;
import com.example.chefchatter.modele.Filtre;
import com.example.chefchatter.modele.Modele;
import com.example.chefchatter.modele.ModeleManager;
import com.example.chefchatter.modele.Recette;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class PresentateurRecette {

    private Activity activity;

    private Modele modele;

    public PresentateurRecette(Activity activity) {
        this.activity = activity;
        this.modele = ModeleManager.getInstance();
    }

    public List<Recette> getRecettes() {
        return modele.getRecettes();
    }

    public void obtenirRecettes(Filtre filtre) {
        modele = ModeleManager.getInstance();
        new Thread(){
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    List<Recette> liste = null;
                    liste = DAO.getRecettes(filtre);
                    modele.setRecettes(liste);
                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }

}
