package com.example.chefchatter.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chefchatter.R;
import com.example.chefchatter.activites.ActionActivity;
import com.example.chefchatter.activites.ListeRecette;

public class AffichageRecette extends AppCompatActivity implements View.OnClickListener{

    private Button btnRetour;
    private Button btnCompte;
    private Button btnChercherRecette;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_recette);

        btnRetour = findViewById(R.id.btnRetourAR);
        btnRetour.setOnClickListener(this);

        btnCompte = findViewById(R.id.btnCompteAR);
        btnCompte.setOnClickListener(this);

        btnChercherRecette = findViewById(R.id.btnVoirTtesRecettes);
        btnChercherRecette.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnRetour){
            finish();
        }
        else if(v == btnCompte){
            Intent intent = new Intent(this, ActionActivity.class);
            startActivity(intent);
        }
        else if(v == btnChercherRecette){
            Intent intent = new Intent(this, ListeRecette.class);
            startActivity(intent);
        }
    }
}