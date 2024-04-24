package com.example.chefchatter.dao;

import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.CompteMessage;
import com.example.chefchatter.modele.Filtre;
import com.example.chefchatter.modele.Recette;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DAO {
static Compte compte ;
    public static List<Recette> getRecettes(Filtre filtre) throws IOException, JSONException {
        return new HttpJsonService().RequeteFiltre(filtre);
    }

    public static List<String> getIngredientsSelonRecette(Integer idRecette) throws IOException, JSONException {
        return new HttpJsonService().getIngredientsSelonRecette(idRecette);

    }
    public static CompteMessage creationCompte (Compte compte) throws IOException, JSONException {
        return new HttpJsonService().requeteCreationCompte(compte);
    }
    public static CompteMessage connexionCompte (Compte compte) throws IOException, JSONException {
        return new  HttpJsonService().requeteCreationConnexion(compte);
    }
    public static void modifierCompte (Compte compte) throws IOException, JSONException {
        new HttpJsonService().requeteModificationCompte(compte);
    }
    public static void supprimerCompte (Compte compte) throws IOException, JSONException {
        new HttpJsonService().requeteSuppressionCompte(compte);
    }
    public static void setCompte (Compte compte) {
       DAO.compte = compte;
    }
    public  static Compte  getCompte (Compte compte)  {
       return DAO.compte;
    }
}
