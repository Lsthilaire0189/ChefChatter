package com.example.chefchatter.presentateur;

import android.app.Activity;

import com.example.chefchatter.activites.ListeRecette;
import com.example.chefchatter.dao.AvisCallback;
import com.example.chefchatter.dao.AvisCourrantCallback;
import com.example.chefchatter.dao.DAO;
import com.example.chefchatter.modele.Avis;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.Modele;
import com.example.chefchatter.modele.ModeleManager;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class PresentateurAvis {

    private Activity activity;

    private Modele modele;

    public PresentateurAvis(Activity activity) {
        this.activity = activity;
        this.modele = ModeleManager.getInstance();
    }
    public void CreationAvis(Avis avis) {
        new Thread(){
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    DAO.ajouterCommentaire(avis);
                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }

    public void obtenirAvis(Integer idRecette, AvisCallback callback) {
        new Thread(){
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    List<Avis> liste = null;
                    liste = DAO.getAvisSelonRecette(idRecette);
                    modele.setAvis(liste);
                    callback.onAvisReceived(liste);

                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }

    public void obtenirAvisCourrant(String email, Integer recetteId, AvisCourrantCallback callback) {
        new Thread(){
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    Avis avis = null;
                    avis = DAO.checkUserRatingExists(email, recetteId);
                    modele.setAvisCourrant(avis);
                    callback.onAvisCourrantReceived(avis);

                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }

    public void modifAvis(Avis avis) {
        new Thread(){
            @Override
            public void run() {
                try {
                    DAO.modifAvis(avis);
                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }

    public int getNbAvis() {
        return modele.getAvis().size();
    }

    public Avis getAvis(int position) {
        return modele.getAvis().get(position);
    }

    public Avis getAvisCourrant() {
        return modele.getAvisCourrant();
    }


}
