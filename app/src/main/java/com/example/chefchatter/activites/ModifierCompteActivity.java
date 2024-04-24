package com.example.chefchatter.activites;

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

public class ModifierCompteActivity extends AppCompatActivity implements View.OnClickListener {

    private DatePicker datePicker;
    private EditText courriel;
    private EditText nomUtilisateur;
    private EditText mdp;
    private EditText prenom;
    private EditText nom;
    private Button btnRetour;
    private Button btnModifier;
    private Button btnSupprimer;
    private PresentateurCompte presentateurCompte;
    private Compte compteModifie;
    private Compte compteInitial;
    private CompteMessage compteMessage;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_compte);

        courriel = findViewById(R.id.mcPtCourriel);
        nomUtilisateur = findViewById(R.id.mcPtNomUtilisateur);
        mdp = findViewById(R.id.mcPtpassword);
        prenom = findViewById(R.id.mcPtPrenom);
        nom = findViewById(R.id.mcPtNomFamille);
        datePicker = findViewById(R.id.mcDateNaissance);

        btnRetour = findViewById(R.id.mcBtnRetour);
        btnModifier = findViewById(R.id.mcBtnModifier);
        btnSupprimer = findViewById(R.id.mcBtnSupprimer);

        btnRetour.setOnClickListener(this);
        btnModifier.setOnClickListener(this);
        btnSupprimer.setOnClickListener(this);

        presentateurCompte = new PresentateurCompte(this);
     //   Compte compteInitial = (Compte) getIntent().getSerializableExtra("compte");
        compteInitial = presentateurCompte.getCompte();

        courriel.setText(compteInitial.getCourriel());
        nomUtilisateur.setText(compteInitial.getNomUtilisateur());
        mdp.setText(compteInitial.getMdp());
        prenom.setText(compteInitial.getPrenom());
        nom.setText(compteInitial.getNom());

        String[] date = compteInitial.getDateNaissance().split("-");
        datePicker.updateDate(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]));



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mcBtnRetour) {
            finish();
        } else if (v.getId() == R.id.mcBtnModifier) {
            String date = datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear();
            Compte compteModifie = new Compte(prenom.getText().toString(), nom.getText().toString(), courriel.getText().toString(), nomUtilisateur.getText().toString(), date, mdp.getText().toString());
              presentateurCompte.modifierCompte(compteModifie, new CompteCallBack() {
                @Override
                public void onReponseRecieved(CompteMessage reponse) {
                    if(reponse.getMessage().equals("Compte modifié avec succès")) {
                        presentateurCompte.setCompte(reponse.getCompte());
                    }
                }

            });
              finish();
        } else if (v.getId() == R.id.mcBtnSupprimer) {
            presentateurCompte.supprimerCompte(presentateurCompte.getCompte());

        }

    }

    public void afficherMessage(CompteMessage compteMessage) {
        Toast.makeText(this,compteMessage.getMessage(),Toast.LENGTH_SHORT).show();
    }
}
