package com.example.chefchatter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ListeRecette extends AppCompatActivity implements View.OnClickListener{

    private Button btnRetour;
    private Button btnRechercher;

    private ListView listeRecette;

    private Spinner origine;
    private Spinner regime;
    private Spinner type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste_recette);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        origine = findViewById(R.id.sChoixOrigine);
        regime = findViewById(R.id.sChoixRegime);
        type = findViewById(R.id.sChoixType);

        ArrayAdapter<CharSequence> adapterOrigine = ArrayAdapter.createFromResource(this, R.array.spinner_choice_origine, android.R.layout.simple_spinner_item);
        adapterOrigine.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        origine.setAdapter(adapterOrigine);

        ArrayAdapter<CharSequence> adapterRegime = ArrayAdapter.createFromResource(this, R.array.spinner_choice_regime, android.R.layout.simple_spinner_item);
        adapterRegime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regime.setAdapter(adapterRegime);

        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this, R.array.spinner_choice_type, android.R.layout.simple_spinner_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapterType);

        listeRecette = findViewById(R.id.lvFiltre);

        btnRechercher = findViewById(R.id.btnRecherche);
        btnRechercher.setOnClickListener(this);

        btnRetour = findViewById(R.id.btnRet);
        btnRetour.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnRetour){
            finish();
        }
        else if(v == btnRechercher){

            Filtre filtre = new Filtre(origine.getSelectedItem().toString(), regime.getSelectedItem().toString(), type.getSelectedItem().toString());
            (new Thread() {
                public void run() {
                    RequeteFiltre(filtre);
                }
            }).start();
        }
    }
    private void RequeteFiltre(Filtre filtre){
        final String URL_POINT_ENTREE = "https://equipe500.tch099.ovh/projet1/api";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject obj = new JSONObject();
        try {
            obj.put("origine", filtre.getChoixOrigine());
            obj.put("regime", filtre.getChoixRegime());
            obj.put("type", filtre.getChoixType());

        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON);
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/filtrer").post(corpsRequete).build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {

                String responseBody = response.body().string();
                JSONArray jsonResponse = new JSONArray(responseBody);

                // Loop through the array of results
                for (int i = 0; i < jsonResponse.length(); i++) {
                    JSONObject result = jsonResponse.getJSONObject(i);
                    // Access each field in the result
                    String email = result.getString("email");
                    String prenom = result.getString("prenom");
                    // ... access other fields as needed ...
                }


                finish();
            } else {
                System.out.println("Request not successful. Response Code: " + response.code());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}