package com.example.chefchatter.presentateur;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.chefchatter.activites.ActionActivity;
import com.example.chefchatter.activites.MainActivity2;
import com.example.chefchatter.dao.DAO;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.Modele;
import com.example.chefchatter.modele.ModeleManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PresentateurCompte {
    private Activity activity;

    private Modele modele;

    public PresentateurCompte(Activity activity) {
        this.activity = activity;
        this.modele = ModeleManager.getInstance();
    }
    public void connexionCompte(Compte compte) {
        new Thread(){
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    DAO.connexionCompte(compte);

                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }
    public void creationCompte(Compte compte) {
        new Thread(){
            @Override
            public void run() {
                try {
                    modele = ModeleManager.getInstance();
                    DAO.creationCompte(compte);

                } catch (JSONException e) {
                } catch (IOException e) {
                }
            }
        }.start();
    }
    public void modifierCompte(Compte compte) {
        new Thread(){
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
}
