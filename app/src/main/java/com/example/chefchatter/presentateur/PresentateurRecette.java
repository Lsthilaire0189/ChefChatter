package com.example.chefchatter.presentateur;

import android.app.Activity;

import com.example.chefchatter.activites.ListeRecette;
import com.example.chefchatter.activites.MainActivity;
import com.example.chefchatter.dao.DAO;
import com.example.chefchatter.dao.RecettesCallback;
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

    public void obtenirRecettes(Filtre filtre, RecettesCallback callback) {
        new Thread(){
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    List<Recette> liste = null;
                    liste = DAO.getRecettes(filtre);
                    modele.setRecettes(liste);
                    callback.onRecettesReceived(liste);
                    ((ListeRecette)activity).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((ListeRecette) activity).raffraichirListe();
                        }
                    });
                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }
    public int getNbRecettes() {
        return modele.getRecettes().size();
    }
    public Recette getRecette(int position) {
        return modele.getRecettes().get(position);
    }

}
