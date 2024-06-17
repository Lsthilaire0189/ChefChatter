package com.example.chefchatter.activites;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Calendar;
import android.os.Bundle;
import android.widget.Toast;

import com.example.chefchatter.dao.CompteCallBack;
import com.example.chefchatter.dao.DAO;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.R;
import com.example.chefchatter.modele.CompteMessage;
import com.example.chefchatter.presentateur.PresentateurCompte;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class CreationCompteActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnSelectDate;
    private Button btnRetour;

    private Button btnConfirmer;

    private TextInputEditText prenom;
    private TextInputEditText nom;
    private TextInputEditText courriel;
    private TextInputEditText nomUtilisateur;
    private TextInputEditText mdp;

    private String dateNaissance;
    Compte compte;
    PresentateurCompte presentateurCompte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnSelectDate = findViewById(R.id.btnDateNaissanceMain3);
        btnRetour = findViewById(R.id.btnRetourMain3);
        btnRetour.setOnClickListener(this);

        btnConfirmer = findViewById(R.id.btnConfirmerAct3);
        btnConfirmer.setOnClickListener(this);

        prenom = findViewById(R.id.inputPrenomMain3);
        nom = findViewById(R.id.inputNomFamilleMain3);
        courriel = findViewById(R.id.inputAdrCourrielMain3);
        nomUtilisateur = findViewById(R.id.inputNomUtilisateurMain3);
        mdp = findViewById(R.id.inputMdpMain3);

        presentateurCompte = new PresentateurCompte(this);

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreationCompteActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                                // Use the selected date (Note: Month is 0-indexed)
                                dateNaissance= selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                            }
                        }, year, month, day);

                datePickerDialog.show();
            }
        });

    }


    @Override
    public void onClick(View v) {
        if (v == btnRetour) {
            finish();
        } else if (v == btnConfirmer) {
             compte = new Compte(prenom.getText().toString(), nom.getText().toString(), courriel.getText().toString(), nomUtilisateur.getText().toString(), dateNaissance, mdp.getText().toString());
             presentateurCompte.creationCompte(compte, new CompteCallBack() {
                @Override
                public void onReponseRecieved(CompteMessage reponse) {
                    if(reponse.getMessage().equals("Compte crée avec succès")) {

                    }
                }
            });
            finish();


        }
    }

    private void requeteCreationCompte() {

        final String URL_POINT_ENTREE = "https://equipe500.tch099.ovh/projet1/api";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject obj = new JSONObject();
        Compte compte = new Compte(prenom.getText().toString(), nom.getText().toString(), courriel.getText().toString(), nomUtilisateur.getText().toString(), dateNaissance, mdp.getText().toString());
        DAO.setCompte(compte);
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
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/ajouterCompte").post(corpsRequete).build();


        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                String message = jsonResponse.getString("message");

                // Display the message in a Toast on the UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });

                finish();
            } else {
                System.out.println("Request not successful. Response Code: " + response.code());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
    public void afficherMessage(CompteMessage compteMessage) {
        Toast.makeText(this,compteMessage.getMessage(),Toast.LENGTH_SHORT).show();
    }

}