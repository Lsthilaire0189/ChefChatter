package com.example.chefchatter.presentateur;

import android.app.Activity;

import com.example.chefchatter.activites.ConnexionCompteActivity;
import com.example.chefchatter.activites.DescriptionRecette;
import com.example.chefchatter.activites.ListeFavorisActivity;
import com.example.chefchatter.dao.DAO;
import com.example.chefchatter.modele.Favoris;
import com.example.chefchatter.modele.Modele;
import com.example.chefchatter.modele.ModeleManager;
import com.example.chefchatter.modele.Recette;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PresentateurFavoris {

    private Activity activity;

    private Modele modele;

    public PresentateurFavoris(Activity activity) {
        this.activity = activity;
        this.modele = ModeleManager.getInstance();
    }

    public void ajouterFavoris(Favoris favoris) {
        new Thread(){
            @Override
            public void run() {
                try {
                modele = ModeleManager.getInstance();
                 String reponse =  DAO.ajouterFavoris(favoris);
                 modele.ajouterFavoris(favoris);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ((DescriptionRecette) activity).afficherMessage(reponse);

                    }
                });
            } catch (JSONException e) {
            } catch (IOException e) {
            }
            }
        }.start();
    }
    public void supprimer(Favoris favoris) {
        new Thread(){
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    String reponse =  DAO.supprimerFavoris(favoris);
                    modele.supprimer(favoris);
                     activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((ListeFavorisActivity) activity).raffraichirListe();
                            ((ListeFavorisActivity) activity).afficherMessage(reponse);
                        }
                    });
                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }

    public List<Favoris> getListeFavoris() {
        modele = ModeleManager.getInstance();
       return modele.getListFavoris();
    }
    public List<Recette> getRecettesFavorites() {
        modele = ModeleManager.getInstance();
        List<Recette> listRecettes = modele.getRecettes();
        List<Recette> listRecettesFavoris = new ArrayList<>();

        for (Favoris favoris : modele.getListFavoris()) {
            for (Recette recette : listRecettes) {
                if (recette.getId() == favoris.getRecetteId()) {
                    listRecettesFavoris.add(recette);
                }
            }
        }
        return listRecettesFavoris;
    }

    public void obtenirRecettesFavorites(String email) {
        new Thread(){
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    List<Recette> liste = DAO.obtenirFavoris(email);
                    modele.setRecettesFavorites(liste);
                    ((ListeFavorisActivity)activity).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((ListeFavorisActivity) activity).raffraichirListe();
                        }
                    });
                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }

    public Recette getRecetteFavorie(int index){
        return this.modele.getRecetteFavorite(index);
    }
    public int getNbRecettesFavorites() {
        return modele.getRecettesFavorites().size();
    }
}
