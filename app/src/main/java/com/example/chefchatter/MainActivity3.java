package com.example.chefchatter;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Calendar;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener{
    private Button btnSelectDate;
    private Button btnRetour;

    private Button btnConfirmer;

    TextInputEditText prenom;
    TextInputEditText nom;
    TextInputEditText courriel;
    TextInputEditText nomUtilisateur;
    TextInputEditText mdp;

    String dateNaissance;

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

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity3.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                                // Use the selected date (Note: Month is 0-indexed)
                                dateNaissance= dateNaissance = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
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
            (new Thread() {
                public void run() {
                    requetePost();
                }
            }).start();

        }
    }

    private void requetePost () {
        final String URL_POINT_ENTREE = "https://equipe500.tch099.ovh/projet1/api";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject obj = new JSONObject();
        Compte compte = new Compte(prenom.getText().toString(), nom.getText().toString(), courriel.getText().toString(), nomUtilisateur.getText().toString(), dateNaissance, mdp.getText().toString());
        try {
            obj.put("prenom", compte.getPrenom());
            obj.put("nom", compte.getNom());
            obj.put("courriel", compte.getCourriel());
            obj.put("nomUtilisateur", compte.getNomUtilisateur());
            obj.put("dateNaissance", compte.getDateNaissance());
            obj.put("mdp", compte.getMdp());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON);
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/ajouterCompte").post(corpsRequete).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.code() == 201) {
            System.out.println("Compte inséré avec succès");
        } else {
            System.out.println("Erreur lors de l'insertion du compte");
        }
    }
}