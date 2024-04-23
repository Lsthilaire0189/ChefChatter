package com.example.chefchatter.activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chefchatter.R;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.presentateur.PresentateurCompte;
import com.example.chefchatter.presentateur.PresentateurRecette;

public class ModifierCompteActivity extends AppCompatActivity implements View.OnClickListener {

    private CalendarView calendarView;
    private EditText courriel;
    private EditText nomUtilisateur;
    private EditText mdp;
    private EditText prenom;
    private EditText nom;
    private Button btnRetour;
    private PresentateurCompte presentateurCompte;
    Compte compteModifie;


protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_compte);

        courriel = findViewById(R.id.mcPtCourriel);
        nomUtilisateur = findViewById(R.id.mcPtNomUtilisateur);
        mdp = findViewById(R.id.mcPtpassword);
        prenom = findViewById(R.id.mcPtPrenom);
        nom = findViewById(R.id.mcPtNomFamille);
        btnRetour = findViewById(R.id.mcBtnRetour);
        calendarView = findViewById(R.id.mcDateNaissance);

        btnRetour.setOnClickListener(this);
        compteModifie = new Compte(prenom.getText().toString(), nom.getText().toString(),  courriel.getText().toString(), nomUtilisateur.getText().toString(),String.valueOf(calendarView.getDate()) ,mdp.getText().toString());

        presentateurCompte = new PresentateurCompte(this);


        }

@Override
public void onClick(View v) {
        if(v.getId() == R.id.mcBtnRetour){
            presentateurCompte.modifierCompte(compteModifie);
            finish();
        }

        }
        }
