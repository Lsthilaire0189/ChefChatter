package com.example.chefchatter.dao;

import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.Filtre;
import com.example.chefchatter.modele.Recette;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpJsonService {

    final String URL_POINT_ENTREE = "https://equipe500.tch099.ovh/projet1/api";

    public List<Recette> RequeteFiltre(Filtre filtre) throws IOException, JSONException{

        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject obj = new JSONObject();
        try {
            JSONArray j= new JSONArray();
            j.put("");
            obj.put("origine", filtre.getChoixOrigine());
            obj.put("regime", filtre.getChoixRegime());
            obj.put("type", filtre.getChoixType());
            obj.put("ingredients",j);


        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON); // Use obj.toString()
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/filtrer").post(corpsRequete).build();
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = response.body().string();
        if(isValidJSON(responseBody)){
            ObjectMapper objectMapper = new ObjectMapper();
            List<Recette> recettes = Arrays.asList(objectMapper.readValue(responseBody, Recette[].class));
            return recettes;
        }
        else{
            return null;
        }

    }

    public List<String> getIngredientsSelonRecette(Integer idRecette) throws IOException, JSONException{
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/recette/" + idRecette + "/ingredients").build();
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = response.body().string();
        if(isValidJSON(responseBody)){
            JSONArray jsonArray = new JSONArray(responseBody);
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                ingredients.add(jsonArray.getString(i));
            }
            return ingredients;
        }
        else{
            return null;
        }
    }

    private boolean isValidJSON(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
    public void requeteCreationConnexion(Compte compte){
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
    public void requeteCreationCompte(Compte compte) {

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

    public void requeteModificationCompte(Compte compte) {

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
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/modifierCompte/"+ compte.getCourriel()).put(corpsRequete).build();
}
}
