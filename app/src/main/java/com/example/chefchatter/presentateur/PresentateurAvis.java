package com.example.chefchatter.presentateur;

import android.app.Activity;

import com.example.chefchatter.dao.DAO;
import com.example.chefchatter.modele.Avis;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.Modele;
import com.example.chefchatter.modele.ModeleManager;

import org.json.JSONException;

import java.io.IOException;

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
}
