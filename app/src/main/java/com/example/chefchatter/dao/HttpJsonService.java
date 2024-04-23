package com.example.chefchatter.dao;

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
            obj.put("origine", filtre.getChoixOrigine());
            obj.put("regime", filtre.getChoixRegime());
            obj.put("type", filtre.getChoixType());


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
}
