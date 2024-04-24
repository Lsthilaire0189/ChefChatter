package com.example.chefchatter.dao;

import com.example.chefchatter.modele.Avis;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.CompteMessage;
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

    public static List<Avis> getAvisSelonRecette(Integer idRecette) throws IOException, JSONException {
        return new HttpJsonService().getCommentairesSelonRecette(idRecette);
    }


}







