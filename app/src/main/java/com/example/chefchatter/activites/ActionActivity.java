package com.example.chefchatter.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chefchatter.R;
import com.example.chefchatter.activites.ListeRecette;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.presentateur.PresentateurCompte;

public class ActionActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvBonjour;
    private ImageButton imgBtnHome;
    private Button btnDeconnexion;
    private  Button btnParcourirRecettes;
    private Button btnModifierCompte;

    private Button btnFavoris;
    private Compte compte;
    private PresentateurCompte presentateurCompte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        tvBonjour = findViewById(R.id.tvBonjour);
        btnDeconnexion = findViewById(R.id.btnDeconnexion);
        btnParcourirRecettes = findViewById(R.id.btnParcourirRecettes);
        btnModifierCompte = findViewById(R.id.btnModifierCompte);
        btnFavoris = findViewById(R.id.btnParcourirFavoris);

        btnDeconnexion.setOnClickListener(this);
        btnParcourirRecettes.setOnClickListener(this);
        btnModifierCompte.setOnClickListener(this);
        btnFavoris.setOnClickListener(this);

        presentateurCompte = new PresentateurCompte(this);

    }
    protected void onResume() {
        super.onResume();
        compte = presentateurCompte.getCompte();
        if (compte != null) {
            String nomUtilisateur = compte.getNomUtilisateur();
            String messaqeBienvenue = getResources().getString(R.string.bonjour, nomUtilisateur);
            tvBonjour.setText(messaqeBienvenue);
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnDeconnexion){
            finish();
        }
        else if(v.getId() == R.id.btnParcourirRecettes){
            Intent intent = new Intent(this, ListeRecette.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.btnModifierCompte){

            Intent intent = new Intent(this, ModifierCompteActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.btnParcourirFavoris){
            Intent intent = new Intent(this, ListeFavorisActivity.class);
            startActivity(intent);
        }
    }
}
