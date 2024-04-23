package com.example.chefchatter.activites;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.chefchatter.R;

public class DescriptionRecette extends AppCompatActivity implements View.OnClickListener{

    private Button btnRetour;
    private Button btnChefChatter;
    private Button btnCompte;
    private ImageView imgRecette;
    private TextView txtMotsClefs;
    private TextView txtNomRecette;
    private TextView txtTmpsCuisson;
    private TextView txtTmpsPrepa;
    private TextView txtNbPortions;
    private TextView txtDescPlat;
    private ListView listeIngredients;
    private TextView listeEtapes;
    RatingBar ratingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_recette);

        ratingBar = findViewById(R.id.ratingDesc);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        for(int i = 0; i < stars.getNumberOfLayers(); i++){
            stars.getDrawable(i).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        }

        btnRetour = findViewById(R.id.btnRetourDesc);
        btnChefChatter = findViewById(R.id.btnChefChatterDesc);
        btnCompte = findViewById(R.id.btnCompteDesc);
        imgRecette = findViewById(R.id.ivRecetteDesc);
        txtMotsClefs = findViewById(R.id.tvMotsClefsDesc);
        txtNomRecette = findViewById(R.id.tvNomRecetteDesc);
        txtTmpsCuisson = findViewById(R.id.tvTmpsCuissonDesc);
        txtTmpsPrepa = findViewById(R.id.tvTmpsPrepaDesc);
        txtNbPortions = findViewById(R.id.tvNbPortionsDesc);
        txtDescPlat = findViewById(R.id.tvDescPlatDesc);
        listeIngredients = findViewById(R.id.lvIngredientsDesc);
        listeEtapes = findViewById(R.id.tvPrepaDesc);

        btnRetour.setOnClickListener(this);
        btnChefChatter.setOnClickListener(this);
        btnCompte.setOnClickListener(this);

        Intent intent = getIntent();
        String srcRecette = intent.getStringExtra("SRC");
        String nomRecette = intent.getStringExtra("NOM");
        Integer tmpsCuisson = intent.getIntExtra("TEMPS_CUISSON", 0);
        Integer tmpsPrepa = intent.getIntExtra("TEMPS_PREPARATION", 0);
        Integer nbPortions = intent.getIntExtra("PORTIONS", 0);
        String descPlat = intent.getStringExtra("DESCRIPTION");
        String origine = intent.getStringExtra("ORIGINE");
        String regime = intent.getStringExtra("REGIME");
        String type = intent.getStringExtra("TYPE");
        String etapes = intent.getStringExtra("ETAPES");

        Glide.with(this)
                .load(srcRecette)
                .into(imgRecette);

        txtNomRecette.setText(nomRecette);
        txtMotsClefs.setText(String.format("Origine: %s | Régime: %s | Type: %s", origine, regime, type));
        txtTmpsCuisson.setText(String.format("Temps de cuisson: %smin", tmpsCuisson.toString()));
        txtTmpsPrepa.setText(String.format("Temps de préparation: %smin", tmpsPrepa.toString()));
        txtNbPortions.setText(String.format("Nombre de portion(s): %s", nbPortions.toString()));
        txtDescPlat.setText(descPlat);
        listeEtapes.setText(etapes);
    }

    @Override
    public void onClick(View v) {
        if(v == btnRetour){
            finish();
        }
        else if(v == btnChefChatter){
            Intent intent = new Intent(this, ActionActivity.class);
            startActivity(intent);
        }

    }
}
