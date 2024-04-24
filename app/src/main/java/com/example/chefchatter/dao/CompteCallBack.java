package com.example.chefchatter.dao;

import com.example.chefchatter.modele.CompteMessage;
import com.example.chefchatter.modele.Recette;

import java.util.List;

public interface CompteCallBack {
    void onReponseRecieved(CompteMessage compteMessage);
}
