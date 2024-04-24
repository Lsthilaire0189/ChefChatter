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
import com.example.chefchatter.modele.Avis;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.presentateur.PresentateurAvis;
import com.example.chefchatter.presentateur.PresentateurCompte;
import com.example.chefchatter.presentateur.PresentateurIngredients;

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
    private RatingBar ratingBar;
    private Button btnEnvoyerAvis;
    private PresentateurAvis presentateurAvis;
    private PresentateurCompte presentateurCompte;
    private PresentateurIngredients presentateurIngredients;
    private Compte compte;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_recette);

        presentateurAvis = new PresentateurAvis(this);
        presentateurCompte = new PresentateurCompte(this);
        presentateurIngredients = new PresentateurIngredients(this);

        presentateurIngredients.obtenirIngredients(28);
        presentateurIngredients.getIngredients();

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
        btnEnvoyerAvis = findViewById(R.id.btnEnvoyerDesc);

        btnRetour.setOnClickListener(this);
        btnChefChatter.setOnClickListener(this);
        btnCompte.setOnClickListener(this);

        Intent intent = getIntent();
        String srcRecette = intent.getStringExtra("SRC");
        String nomRecette = intent.getStringExtra("NOM");
        String tmpsCuisson = intent.getStringExtra("TEMPS_CUISSON");
        String tmpsPrepa = intent.getStringExtra("TEMPS_PREPARATION");
        String nbPortions = intent.getStringExtra("PORTIONS");
        String descPlat = intent.getStringExtra("DESCRIPTION");
        String origine = intent.getStringExtra("ORIGINE");
        String regime = intent.getStringExtra("REGIME");
        String type = intent.getStringExtra("TYPE");
        String etapes = intent.getStringExtra("ETAPES");

        Glide.with(this)
                .load(srcRecette)
                .into(imgRecette);

        txtNomRecette.setText(nomRecette);
        txtMotsClefs.setText(String.format("Origine: #%s | Régime: #%s | Type: #%s", origine, regime, type));
        txtTmpsCuisson.setText(String.format("Temps de cuisson: %s", tmpsCuisson.toString()));
        txtTmpsPrepa.setText(String.format("Temps de préparation: %s", tmpsPrepa.toString()));
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
        else if(v == btnCompte){
            Intent intent = new Intent(this, Compte.class);
            startActivity(intent);
        }
        else if(v == btnEnvoyerAvis){
            Avis avis = new Avis();
            compte = presentateurCompte.getCompte();
            avis.setUserId(compte.getCourriel());
            presentateurAvis.CreationAvis(avis);
        }

    }
}
