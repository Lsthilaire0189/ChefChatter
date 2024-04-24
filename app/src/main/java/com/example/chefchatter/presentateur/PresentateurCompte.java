package com.example.chefchatter.presentateur;

import android.app.Activity;

import com.example.chefchatter.activites.ConnexionCompteActivity;
import com.example.chefchatter.activites.CreationCompteActivity;
import com.example.chefchatter.dao.CompteCallBack;
import com.example.chefchatter.dao.DAO;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.CompteMessage;
import com.example.chefchatter.modele.Modele;
import com.example.chefchatter.modele.ModeleManager;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

public class PresentateurCompte {
    private Activity activity;

    private Modele modele;

    public PresentateurCompte(Activity activity) {
        this.activity = activity;
        this.modele = ModeleManager.getInstance();
    }

    public void connexionCompte(Compte compte, CompteCallBack callback){
        new Thread() {

            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    CompteMessage reponse = DAO.connexionCompte(compte);
                    callback.onReponseRecieved(reponse);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            ((ConnexionCompteActivity) activity).afficherMessage(reponse);
                            ((ConnexionCompteActivity) activity).afficherMessage(reponse);
                        }
                    });
                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }

    public void creationCompte(Compte compte, CompteCallBack callback) {
        new Thread() {
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    CompteMessage reponse = DAO.creationCompte(compte);
                    modele.setCompteCourrant(compte);
                    callback.onReponseRecieved(reponse);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            ((CreationCompteActivity) activity).afficherMessage(reponse);
                            ((CreationCompteActivity) activity).afficherMessage(reponse);
                        }
                    });
                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();

    }

    public void modifierCompte(Compte compte) {
        new Thread() {
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    DAO.modifierCompte(compte);
                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }

    public void supprimerCompte(Compte compteModifie) {
    }

    public void setCompte(Compte compte) {
        DAO.setCompte(compte);
    }
    public Compte getCompte() {
       return modele.getCompteCourrant();
    }
}
