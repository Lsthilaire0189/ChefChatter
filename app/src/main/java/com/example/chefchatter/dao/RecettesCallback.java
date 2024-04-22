package com.example.chefchatter.dao;

import com.example.chefchatter.modele.Recette;

import java.util.List;

public interface RecettesCallback {
   public void onRecettesReceived(List<Recette> recettes);
}
