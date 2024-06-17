package com.example.chefchatter.activites;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chefchatter.R;
import com.example.chefchatter.dao.CompteCallBack;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.CompteMessage;
import com.example.chefchatter.presentateur.PresentateurCompte;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class ModifierCompteActivity extends AppCompatActivity implements View.OnClickListener {
    private DatePicker datePicker;
    private TextInputEditText courriel;
    private TextInputEditText nomUtilisateur;
    private TextInputEditText mdp;
    private TextInputEditText prenom;
    private TextInputEditText nom;
    private Button btnRetour;
    private Button btnModifier;
    private Button btnSupprimer;
    private PresentateurCompte presentateurCompte;
    private Compte compteModifie;
    private Compte compteInitial;
    private CompteMessage compteMessage;
    private Button btnDateNaissance;
    private String dateNaissance;
    private DatePickerDialog datePickerDialog;
    private int year, month, day;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_compte2);

        courriel = findViewById(R.id.mcTIFCourriel);
        nomUtilisateur = findViewById(R.id.mcTIFUsername);
        mdp = findViewById(R.id.mcTIFPassword);
        prenom = findViewById(R.id.mcTIFPrenom);
        nom = findViewById(R.id.mcTIFNom);
        btnDateNaissance = findViewById(R.id.btnModifDateNaissance);
        btnRetour = findViewById(R.id.mcBtnRetour);
        btnModifier = findViewById(R.id.mcBtnModifier);
        btnSupprimer = findViewById(R.id.mcBtnSupprimer);

        btnRetour.setOnClickListener(this);
        btnModifier.setOnClickListener(this);
        btnSupprimer.setOnClickListener(this);
        btnDateNaissance.setOnClickListener(this);

        presentateurCompte = new PresentateurCompte(this);
        if(presentateurCompte.getCompte() != null) {
            compteInitial = presentateurCompte.getCompte();
            courriel.setText(compteInitial.getCourriel());
            nomUtilisateur.setText(compteInitial.getNomUtilisateur());
            mdp.setText(compteInitial.getMdp());
            prenom.setText(compteInitial.getPrenom());
            nom.setText(compteInitial.getNom());
            String[] date = compteInitial.getDateNaissance().split("-");
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1])-1;
            day = Integer.parseInt(date[2]);
            datePickerDialog = new DatePickerDialog(ModifierCompteActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            dateNaissance = year + "-" + (month - 1) + "-" + day;
                        }
                    }, year, month, day);
        }

            btnDateNaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mcBtnRetour) {
            finish();
        } else if (v.getId() == R.id.mcBtnModifier) {
            String date = datePickerDialog.getDatePicker().getYear() + "-" + (datePickerDialog.getDatePicker().getMonth()+1) + "-" + datePickerDialog.getDatePicker().getDayOfMonth();
            compteModifie = new Compte(prenom.getText().toString(), nom.getText().toString(), courriel.getText().toString(), nomUtilisateur.getText().toString(), date, mdp.getText().toString());
            presentateurCompte.modifierCompte(compteModifie, new CompteCallBack() {
                @Override
                public void onReponseRecieved(CompteMessage reponse) {
                    if (reponse.getMessage().equals("Compte modifié avec succès")) {
                        presentateurCompte.setCompte(reponse.getCompte());
                        finish();
                    }
                }

            });
        } else if (v.getId() == R.id.mcBtnSupprimer) {
            presentateurCompte.supprimerCompte(presentateurCompte.getCompte());
            Intent intent = new Intent(this, ConnexionCompteActivity.class);
            startActivity(intent);
            finish();
        }
        else if (v == btnDateNaissance){

        }

    }

    public void afficherMessage(CompteMessage compteMessage) {
        Toast.makeText(this, compteMessage.getMessage(), Toast.LENGTH_SHORT).show();
    }
}