package com.example.chefchatter.activites;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.chefchatter.R;
import com.example.chefchatter.dao.AvisCallback;
import com.example.chefchatter.dao.AvisCourrantCallback;
import com.example.chefchatter.dao.IngredientsCallback;
import com.example.chefchatter.dao.VerificationFavoriCallback;
import com.example.chefchatter.modele.Avis;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.Favoris;
import com.example.chefchatter.modele.Recette_Ingredient;
import com.example.chefchatter.presentateur.AdapterAvis;
import com.example.chefchatter.presentateur.IngredientsAdapter;
import com.example.chefchatter.presentateur.PresentateurAvis;
import com.example.chefchatter.presentateur.PresentateurCompte;
import com.example.chefchatter.presentateur.PresentateurFavoris;
import com.example.chefchatter.presentateur.PresentateurIngredients;

import java.util.ArrayList;
import java.util.List;

public class DescriptionRecette extends AppCompatActivity implements View.OnClickListener {

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
    Integer idRecette;
    List<Recette_Ingredient> ingredients = new ArrayList<>();
    private EditText etCommentaire;
    private ImageButton btnEtoile;
    private PresentateurFavoris presentateurFavoris;
    private Favoris favoris;
    List<Avis> listeCommentaires = new ArrayList<>();
    ListView lvCommentaires;
    boolean isAvisCourrant = false;
    Avis avisTemp = new Avis();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_recette);

        presentateurAvis = new PresentateurAvis(this);
        presentateurCompte = new PresentateurCompte(this);
        presentateurIngredients = new PresentateurIngredients(this);

        ratingBar = findViewById(R.id.ratingDesc);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        for (int i = 0; i < stars.getNumberOfLayers(); i++) {
            stars.getDrawable(i).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        }

        btnRetour = findViewById(R.id.btnRetourDesc);
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
        etCommentaire = findViewById(R.id.editTextText);
        btnEtoile = findViewById(R.id.drBtnImgEtoile);
        btnCompte = findViewById(R.id.btnCompteDesc);

        btnRetour.setOnClickListener(this);
//        btnChefChatter.setOnClickListener(this);
        btnCompte.setOnClickListener(this);
        btnEtoile.setOnClickListener(this);
        lvCommentaires = findViewById(R.id.lvCommentaires);

        btnRetour.setOnClickListener(this);

        Intent intent = getIntent();
        idRecette = intent.getIntExtra("ID", 0);
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

        presentateurFavoris = new PresentateurFavoris(this);
        presentateurIngredients.obtenirIngredients(idRecette, new IngredientsCallback() {
            @Override
            public void onIngredientsReceived(List<Recette_Ingredient> ingredientsList) {
                ingredients.clear();
                ingredients.addAll(ingredientsList);
                runOnUiThread(() -> {
                    IngredientsAdapter adapteurIngredients = new IngredientsAdapter(DescriptionRecette.this, R.layout.layout_ingredients, presentateurIngredients);
                    listeIngredients.setAdapter(adapteurIngredients);
                });
            }
        });

        presentateurAvis.obtenirAvis(idRecette, new AvisCallback() {
            @Override
            public void onAvisReceived(List<Avis> avis) {
                listeCommentaires.clear();
                listeCommentaires.addAll(avis);
                runOnUiThread(() -> {
                    AdapterAvis adapteurAvis = new AdapterAvis(DescriptionRecette.this, R.layout.layout_commentaire, presentateurAvis);
                    lvCommentaires.setAdapter(adapteurAvis);
                });
            }
        });

        presentateurAvis.obtenirAvisCourrant(presentateurCompte.getCompte().getCourriel(), idRecette, new AvisCourrantCallback() {
            @Override
            public void onAvisCourrantReceived(Avis avis) {
                runOnUiThread(() -> {
                    if (avis != null) {
                        etCommentaire.setText(avis.getCommentaire());
                        ratingBar.setRating(avis.getRating());
                        btnEnvoyerAvis.setText("Modifier l'avis");
                        isAvisCourrant = true;
                        avisTemp = avis;
                    }
                });
            }
        });

        btnEnvoyerAvis.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnRetour) {
            finish();
//        } else if (v == btnChefChatter) {
//            Intent intent = new Intent(this, ActionActivity.class);
//            startActivity(intent);
//        }
        }else if (v == btnCompte) {
            Intent intent = new Intent(this, Compte.class);
            startActivity(intent);
        } else if (v == btnEtoile) {

            compte = presentateurCompte.getCompte();
            favoris = new Favoris(compte.getCourriel(), idRecette);
            presentateurFavoris.ajouterFavoris(favoris);

            presentateurFavoris.verificationFavoris(favoris, new
                    VerificationFavoriCallback() {
                        @Override
                        public void onRecettesReceived(String reponse) {
                            if (reponse.equals("faux")) {
                                presentateurFavoris.ajouterFavoris(favoris);
                            }
                        }
                    });
        }
        else if (v == btnEnvoyerAvis) {
            if (isAvisCourrant) {
                updateListView();
                int rating = Math.round(ratingBar.getRating());
                avisTemp.setRating(rating);
                avisTemp.setCommentaire(etCommentaire.getText().toString());
                btnEnvoyerAvis.setText("Modifier l'avis");
                presentateurAvis.modifAvis(avisTemp);
                Toast.makeText(DescriptionRecette.this, "Avis modifié", Toast.LENGTH_SHORT).show();
                presentateurAvis.obtenirAvisCourrant(presentateurCompte.getCompte().getCourriel(), idRecette, new AvisCourrantCallback() {
                    @Override
                    public void onAvisCourrantReceived(Avis avis) {
                        runOnUiThread(() -> {
                            if (avis != null) {
                                etCommentaire.setText(avisTemp.getCommentaire());
                                ratingBar.setRating(avisTemp.getRating());
                                btnEnvoyerAvis.setText("Modifier l'avis");
                                isAvisCourrant = true;
                                avisTemp = avis;
                            }
                        });
                    }
                });
                updateListView();
            } else {
                Avis avis = new Avis();
                compte = presentateurCompte.getCompte();
                avis.setRecetteId(idRecette);
                avis.setUserId(compte.getCourriel());
                int rating = Math.round(ratingBar.getRating());
                avis.setRating(rating);
                avis.setCommentaire(etCommentaire.getText().toString());
                avis.setUsername(compte.getNomUtilisateur());
                presentateurAvis.CreationAvis(avis);
                isAvisCourrant = true;
                btnEnvoyerAvis.setText("Modifier l'avis");
                Toast.makeText(this, "Avis envoyé", Toast.LENGTH_SHORT).show();
                presentateurAvis.obtenirAvisCourrant(presentateurCompte.getCompte().getCourriel(), idRecette, new AvisCourrantCallback() {
                    @Override
                    public void onAvisCourrantReceived(Avis avis) {
                        runOnUiThread(() -> {
                            if (avis != null) {
                                etCommentaire.setText(avis.getCommentaire());
                                ratingBar.setRating(avis.getRating());
                                btnEnvoyerAvis.setText("Modifier l'avis");
                                isAvisCourrant = true;
                                avisTemp = avis;
                            }
                        });
                    }
                });
                updateListView();
            }
            updateListView();
        }
    }
    public void  afficherMessage( String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void updateListView() {
        presentateurAvis.obtenirAvis(idRecette, new AvisCallback() {
            @Override
            public void onAvisReceived(List<Avis> avis) {
                runOnUiThread(() -> {

                    listeCommentaires.clear();
                    listeCommentaires.addAll(avis);

                    // Ensure adapter is connected and updated
                    AdapterAvis adapter = (AdapterAvis) lvCommentaires.getAdapter();
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    } else {
                        adapter = new AdapterAvis(DescriptionRecette.this, R.layout.layout_commentaire, listeCommentaires);
                        lvCommentaires.setAdapter(adapter);
                    }
                });
            }
        });
    }



}
