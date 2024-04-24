package com.example.chefchatter.dao;

import com.example.chefchatter.modele.Avis;

import java.util.List;

public interface AvisCallback {
    void onAvisReceived(List<Avis> avis);
}
