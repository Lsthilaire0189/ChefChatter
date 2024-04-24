package com.example.chefchatter.dao;

import com.example.chefchatter.modele.Avis;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.CompteMessage;
import com.example.chefchatter.modele.Filtre;
import com.example.chefchatter.modele.Recette;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.example.chefchatter.modele.Recette_Ingredient;


public class HttpJsonService {

    final String URL_POINT_ENTREE = "https://equipe500.tch099.ovh/projet1/api";

    public List<Recette> RequeteFiltre(Filtre filtre) throws IOException, JSONException {

        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject obj = new JSONObject();
        try {
            JSONArray j = new JSONArray();
            j.put("");
            obj.put("origine", filtre.getChoixOrigine());
            obj.put("regime", filtre.getChoixRegime());
            obj.put("type", filtre.getChoixType());
            obj.put("ingredients", j);


        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON); // Use obj.toString()
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/filtrer").post(corpsRequete).build();
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = response.body().string();
        if (isValidJSON(responseBody)) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Recette> recettes = Arrays.asList(objectMapper.readValue(responseBody, Recette[].class));
            return recettes;
        } else {
            return null;
        }

    }

    public List<Recette_Ingredient> getIngredientsSelonRecette(Integer idRecette) throws IOException, JSONException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/infosRecette/" + idRecette).get().build();
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = response.body().string();
        if (isValidJSON(responseBody)) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Recette_Ingredient> ingredients = Arrays.asList(objectMapper.readValue(responseBody, Recette_Ingredient[].class));
            return ingredients;
        } else {
            return null;
        }
    }

    public Avis ajouterAvis(Avis avis) throws IOException, JSONException {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject obj = new JSONObject();
        try {
            obj.put("recetteId", avis.getRecetteId());
            obj.put("email", avis.getUserId());
            obj.put("rating", avis.getRating());
            obj.put("commentaire", avis.getCommentaire());
            obj.put("username", avis.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody corpsRequete = RequestBody.create(obj.toString(), JSON);
        Request request = new Request.Builder()
                .url(URL_POINT_ENTREE + "/ratings")
                .post(corpsRequete)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = response.body().string();
        if (isValidJSON(responseBody)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Avis avisRetourne = objectMapper.readValue(responseBody, Avis.class);
            return avisRetourne;
        } else {
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

    public CompteMessage requeteCreationConnexion(Compte compte) {
        CompteMessage compteMessage = null;
        Compte compteRetourne = null;
        String username = "";
        String email = "";
        String prenom = "";
        String nom = "";
        String dateNaissance = "";
        String mdp = "";
        String reponseFinale = "";
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
                String message = jsonResponse.getString("connexion");
                reponseFinale = message;
                if (reponseFinale.equals("Connexion réussie")) {
                    username = jsonResponse.getString("username");
                    email = jsonResponse.getString("email");
                    prenom = jsonResponse.getString("prenom");
                    nom = jsonResponse.getString("nomDeFamille");
                    dateNaissance = jsonResponse.getString("dateDeNaissance");
                    mdp = jsonResponse.getString("password");

                }
                compteRetourne = new Compte(username, email, prenom, nom, dateNaissance, mdp);
                compteMessage = new CompteMessage(reponseFinale, compteRetourne);


                // Display the message in a Toast on the UI thread
                //  String username = jsonResponse.getString("username");


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
        return compteMessage;
    }

    public CompteMessage requeteCreationCompte(Compte compte) {
        CompteMessage compteMessage = null;
        String reponseFinale = "";
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

                reponseFinale = message;
                compteMessage = new CompteMessage(reponseFinale, null);

            } else {
                System.out.println("Request not successful. Response Code: " + response.code());
                //  reponse = false;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return compteMessage;
    }

    public CompteMessage requeteModificationCompte(Compte compte) {

        final String URL_POINT_ENTREE = "https://equipe500.tch099.ovh/projet1/api";
        CompteMessage compteMessage = null;
        OkHttpClient okHttpClient = new OkHttpClient();
        String responseBody;
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject obj = new JSONObject();
        try {
            obj.put("docMail", compte.getCourriel());
            obj.put("docUsername", compte.getNomUtilisateur());
            obj.put("docPassword", compte.getMdp());
            obj.put("docPrenom", compte.getPrenom());
            obj.put("docNomFamille", compte.getNom());
            obj.put("docDateNaissance", compte.getDateNaissance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON);
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/pushModification").post(corpsRequete).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                String message = jsonResponse.getString("message");

                compteMessage = new CompteMessage(message, null);

            } else {
                System.out.println("Request not successful. Response Code: " + response.code());
                //  reponse = false;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return compteMessage;
    }

    public CompteMessage requeteSuppressionCompte(Compte compte) {
        CompteMessage compteMessage = null;
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject obj = new JSONObject();
        try {
            obj.put("email", compte.getCourriel());

        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON);
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/deleteAccountAndroid").post(corpsRequete).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                String message = jsonResponse.getString("message");

                compteMessage = new CompteMessage(message, null);

            } else {
                System.out.println("Request not successful. Response Code: " + response.code());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return compteMessage;
    }
}
