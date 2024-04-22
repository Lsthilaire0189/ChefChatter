package com.example.chefchatter.dao;

import com.example.chefchatter.modele.Filtre;
import com.example.chefchatter.modele.Recette;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class DAO {

    public static List<Recette> getRecettes(Filtre filtre) throws IOException, JSONException {
        return new HttpJsonService().RequeteFiltre(filtre);
    }
}
