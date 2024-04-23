package com.example.chefchatter.activites;

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

import com.example.chefchatter.dao.RecettesCallback;
import com.example.chefchatter.modele.Filtre;
import com.example.chefchatter.R;
import com.example.chefchatter.modele.Recette;
import com.example.chefchatter.presentateur.AdapteurRecette;
import com.example.chefchatter.presentateur.PresentateurRecette;

import java.util.List;

public class ListeRecette extends AppCompatActivity implements View.OnClickListener{

    private Button btnRetour;
    private Button btnRechercher;

    private ListView listeRecette;

    private Spinner origine;
    private Spinner regime;
    private Spinner type;
    PresentateurRecette presentateurRecette;
    List<Recette> recettes;
    private AdapteurRecette adaptateur;


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

        presentateurRecette = new PresentateurRecette(this);

        adaptateur = new AdapteurRecette(this,
                R.layout.layout_recettes,presentateurRecette);
        listeRecette.setAdapter(adaptateur);
    }

    @Override
    public void onClick(View v) {
        if(v == btnRetour){
            finish();
        }
        else if(v == btnRechercher){

            Filtre filtre = new Filtre(origine.getSelectedItem().toString(), regime.getSelectedItem().toString(), type.getSelectedItem().toString());
            presentateurRecette.obtenirRecettes(filtre ,new RecettesCallback() {
                @Override
                public void onRecettesReceived(List<Recette> recettes) {
                    ListeRecette.this.recettes = presentateurRecette.getRecettes();

                }

            });

    }
 }

    public void raffraichirListe() {
        this.adaptateur.notifyDataSetChanged();
        Toast.makeText(this,adaptateur.getCount()+" comptes",Toast.LENGTH_SHORT).show();
    }
}