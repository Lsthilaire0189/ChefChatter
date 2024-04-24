package com.example.chefchatter.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chefchatter.dao.CompteCallBack;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.R;
import com.example.chefchatter.modele.CompteMessage;
import com.example.chefchatter.presentateur.PresentateurCompte;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ConnexionCompteActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnRetour;
    private Button creationCompte;
    private Button mdpOublie;
    private Button btnConnexion;
    private TextInputEditText courriel;
    private TextInputEditText mdp;
    private  JSONObject jsonResponse;
    private PresentateurCompte presentateurCompte;
    private Compte compte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        courriel = findViewById(R.id.inputAdrCourrielMain2);
        mdp = findViewById(R.id.inputMdpMain2);

        btnRetour = findViewById(R.id.btnRetourActMain2);
        btnRetour.setOnClickListener(this);

        creationCompte = findViewById(R.id.btnPasCompteMain2);
        creationCompte.setOnClickListener(this);

        mdpOublie = findViewById(R.id.btnMdpOublieMain2);
        mdpOublie.setOnClickListener(this);

        btnConnexion = findViewById(R.id.btnSeConnecterMain2);
        btnConnexion.setOnClickListener(this);

        presentateurCompte = new PresentateurCompte(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnRetour) {
            finish();
        } else if (v == creationCompte) {
            startActivity(new Intent(ConnexionCompteActivity.this, CreationCompteActivity.class));
        } else if (v == mdpOublie) {
            startActivity(new Intent(ConnexionCompteActivity.this, MdpOublie.class));
        } else if (v == btnConnexion) {
            compte = new Compte("", "", courriel.getText().toString(), "", "", mdp.getText().toString());

            presentateurCompte.connexionCompte(compte, new CompteCallBack() {
                @Override
                public void onReponseRecieved( CompteMessage reponse) {
                    if (reponse.getMessage().equals("Connexion réussie")) {
                        presentateurCompte.setCompte(reponse.getCompte());
                    }
                }
            });
            Intent intent = new Intent(ConnexionCompteActivity.this, ActionActivity.class);
            startActivity(intent);

        }
    }


    public void afficherMessage(CompteMessage compteMessage) {
        Toast.makeText(this,compteMessage.getMessage(),Toast.LENGTH_SHORT).show();
    }
}