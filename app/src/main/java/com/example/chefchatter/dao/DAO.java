package com.example.chefchatter.dao;

import com.example.chefchatter.modele.Avis;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.CompteMessage;
import com.example.chefchatter.modele.Favoris;
import com.example.chefchatter.modele.Filtre;
import com.example.chefchatter.modele.Recette;
import com.example.chefchatter.modele.Recette_Ingredient;
import com.example.chefchatter.modele.Recette_Ingredient;
import com.example.chefchatter.modele.Recette_Ingredient;


import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DAO {
    public static Compte compte ;
    public static List<Recette> getRecettes(Filtre filtre) throws IOException, JSONException {
        return new HttpJsonService().RequeteFiltre(filtre);
    }

    public static List<Recette_Ingredient> getIngredientsSelonRecette(Integer idRecette) throws IOException, JSONException {
        return new HttpJsonService().getIngredientsSelonRecette(idRecette);

    }
    public static CompteMessage creationCompte (Compte compte) throws IOException, JSONException {
        return new HttpJsonService().requeteCreationCompte(compte);
    }
    public static CompteMessage connexionCompte (Compte compte) throws IOException, JSONException {
        return new  HttpJsonService().requeteCreationConnexion(compte);
    }
    public static CompteMessage modifierCompte (Compte compte) throws IOException, JSONException {
        return new HttpJsonService().requeteModificationCompte(compte);
    }

    public static Avis ajouterCommentaire (Avis avis) throws IOException, JSONException {
        return new HttpJsonService().ajouterAvis(avis);
    }

    public static CompteMessage supprimerCompte (Compte compte) throws IOException, JSONException {
        return new HttpJsonService().requeteSuppressionCompte(compte);
    }
    public static void setCompte (Compte compte) {
        DAO.compte = compte;
    }
    public  static Compte  getCompte (Compte compte)  {
        return DAO.compte;
    }

<<<<<<< HEAD
    public static String  ajouterFavoris (Favoris favoris) throws IOException, JSONException {
         return new HttpJsonService().requetteAjouterFavoris(favoris);
    }
=======
    public static List<Avis> getAvisSelonRecette(Integer idRecette) throws IOException, JSONException {
        return new HttpJsonService().getCommentairesSelonRecette(idRecette);
    }

    public static Avis checkUserRatingExists(String email, Integer recetteId) throws IOException, JSONException {
        return new HttpJsonService().checkRatingByUser(email, recetteId);
    }

    public static void modifAvis(Avis avis) throws IOException, JSONException {
        new HttpJsonService().modifAvis(avis);
    }

>>>>>>> ef20dc8b0c78659aa1a9f8d070e3a7c61560d735


    public static String supprimerFavoris(Favoris favoris) throws IOException, JSONException {
        return new HttpJsonService().requetteSupprimerFavoris(favoris);
    }
    public static List<Recette> obtenirFavoris ( String email) throws IOException, JSONException {
        return new HttpJsonService().requeteObtenirFavoris(email);
    }
}







