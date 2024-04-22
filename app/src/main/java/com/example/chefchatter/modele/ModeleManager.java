package com.example.chefchatter.modele;

import com.example.chefchatter.modele.Modele;

public class ModeleManager {

    private static Modele instance = null;

    public static Modele getInstance() {
        if (instance == null)
            instance = new Modele();

        return instance;
    }
}
