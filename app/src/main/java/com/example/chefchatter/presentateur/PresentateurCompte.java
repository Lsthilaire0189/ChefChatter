package com.example.chefchatter.presentateur;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.chefchatter.activites.ActionActivity;
import com.example.chefchatter.activites.MainActivity2;
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
    private void requeteCreationConnexion(Compte compte){
        final String URL_POINT_ENTREE = "https://equipe500.tch099.ovh/projet1/api";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject obj = new JSONObject();
        try {
            obj.put("email", compte.getCourriel());
            obj.put("username", compte.getNomUtilisateur());
            obj.put("password", compte.getMdp());
            obj.put("prenom", compte.getPrenom());
            obj.put("nom", compte.getNom());
            obj.put("dateNaissance", compte.getDateNaissance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON);
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/connexion").post(corpsRequete).build();


        try {
            Response response = okHttpClient.newCall(request).execute();
            JSONObject jsonResponse = null;

            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                jsonResponse = new JSONObject(responseBody);

                String username = jsonResponse.getString("username");
                String connexion = jsonResponse.getString("connexion");

                // Display the message in a Toast on the UI thread
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(), connexion, Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                Intent intent = new Intent(MainActivity2.this, ActionActivity.class);
//                intent.putExtra("username", username);
//                startActivity(intent);

            } else {
                String message = jsonResponse.getString("message");
               // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    private void requeteCreationCompte(Compte compte) {

        final String URL_POINT_ENTREE = "https://equipe500.tch099.ovh/projet1/api";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject obj = new JSONObject();
        try {
            obj.put("email", compte.getCourriel());
            obj.put("username", compte.getNomUtilisateur());
            obj.put("password", compte.getMdp());
            obj.put("prenom", compte.getPrenom());
            obj.put("nom", compte.getNom());
            obj.put("dateNaissance", compte.getDateNaissance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON);
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/ajouterCompte").post(corpsRequete).build();


        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                String message = jsonResponse.getString("message");

                // Display the message in a Toast on the UI thread
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                finish();
            } else {
                System.out.println("Request not successful. Response Code: " + response.code());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

}
