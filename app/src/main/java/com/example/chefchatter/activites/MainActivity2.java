package com.example.chefchatter.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    private Button btnRetour;
    private Button creationCompte;
    private Button mdpOublie;
    private Button btnConnexion;
    private TextInputEditText courriel;
    private TextInputEditText mdp;
    private  JSONObject jsonResponse;

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
    }

    @Override
    public void onClick(View v) {
        if(v == btnRetour){
            finish();
        }
        else if(v == creationCompte){
            startActivity(new Intent(MainActivity2.this, MainActivity3.class));
        }
        else if(v == mdpOublie){
            startActivity(new Intent(MainActivity2.this, MdpOublie.class));
        }
        else if(v == btnConnexion){
            (new Thread() {
                public void run() {
                    requeteCreationConnexion();
                }
            }).start();
        }
    }
    private void requeteCreationConnexion(){
        final String URL_POINT_ENTREE = "https://equipe500.tch099.ovh/projet1/api";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject obj = new JSONObject();
        Compte compte = new Compte("","", courriel.getText().toString(),"" , "", mdp.getText().toString());
        try {
            obj.put("email", compte.getCourriel());
            obj.put("username", compte.getNomUtilisateur());
            obj.put("password", compte.getMdp());
            obj.put("prenom", compte.getPrenom());
            obj.put("nom", compte.getNom());
            obj.put("dateNaissance", compte.getDateNaissance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON);
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/connexion").post(corpsRequete).build();


        try {
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                 jsonResponse = new JSONObject(responseBody);

                String username = jsonResponse.getString("username");
                String connexion = jsonResponse.getString("connexion");

                // Display the message in a Toast on the UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), connexion, Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = new Intent(MainActivity2.this, ActionActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);

            } else {
                String message = jsonResponse.getString("message");
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}