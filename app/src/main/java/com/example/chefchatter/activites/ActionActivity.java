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

public class ActionActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvBonjour;
    private ImageButton imgBtnHome;
    private Button btnDeconnexion;
    private  Button btnParcourirRecettes;
    private Button btnModifierCompte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        tvBonjour = findViewById(R.id.tvBonjour);
        imgBtnHome = findViewById(R.id.ibHome);
        btnDeconnexion = findViewById(R.id.btnDeconnexion);
        btnParcourirRecettes = findViewById(R.id.btnParcourirRecettes);
        btnModifierCompte = findViewById(R.id.btnModifierCompte);

        imgBtnHome.setOnClickListener(this);
        btnDeconnexion.setOnClickListener(this);
        btnParcourirRecettes.setOnClickListener(this);
        btnModifierCompte.setOnClickListener(this);

        String nomUtilisateur = getIntent().getStringExtra("username");
        String messaqeBienvenue = getResources().getString(R.string.bonjour, nomUtilisateur);
        tvBonjour.setText(messaqeBienvenue);



    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ibHome){
            finish();
        }
        else if(v.getId() == R.id.btnDeconnexion){
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
    }
}
