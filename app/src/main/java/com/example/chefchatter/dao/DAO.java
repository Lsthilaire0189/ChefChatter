package com.example.chefchatter.dao;

import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.Filtre;
import com.example.chefchatter.modele.Recette;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class DAO {

    public static List<Recette> getRecettes(Filtre filtre) throws IOException, JSONException {
        return new HttpJsonService().RequeteFiltre(filtre);
    }

    public static List<String> getIngredientsSelonRecette(Integer idRecette) throws IOException, JSONException {
        return new HttpJsonService().getIngredientsSelonRecette(idRecette);

    }
    public static void creationCompte (Compte compte) throws IOException, JSONException {
        new HttpJsonService().requeteCreationCompte(compte);
    }
    public static void connexionCompte (Compte compte) throws IOException, JSONException {
        new HttpJsonService().requeteCreationConnexion(compte);
    }
    public static void modifierCompte (Compte compte) throws IOException, JSONException {
        new HttpJsonService().requeteModificationCompte(compte);
    }
}
