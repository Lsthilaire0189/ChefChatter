package com.example.chefchatter.activites;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.chefchatter.presentateur.IngredientsAdapter;
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
    private EditText txtIngredient;

    private Filtre filtre = null;


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

        txtIngredient = findViewById(R.id.etListeIngredients);

        listeRecette.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent iRecetteDescription = new Intent(getApplicationContext(), DescriptionRecette.class);
                    Recette recetteSelectionnee = (Recette) parent.getAdapter().getItem(position);
                    String src = recetteSelectionnee.getSrc();
                    String nom = recetteSelectionnee.getNom();
                    //ajouter mots clefs
                    Integer idRecette = recetteSelectionnee.getId();
                    String tempsCuisson = recetteSelectionnee.getCuisson();
                    String tempsPreparation = recetteSelectionnee.getPreparation();
                    String portions = recetteSelectionnee.getPortion();
                    String description = recetteSelectionnee.getDescription();
                    String origine = recetteSelectionnee.getOrigine();
                    String regime = recetteSelectionnee.getRegime();
                    String type = recetteSelectionnee.getType();
                    String etape = recetteSelectionnee.getEtape();
                    iRecetteDescription.putExtra("ID", idRecette);
                    iRecetteDescription.putExtra("SRC", src);
                    iRecetteDescription.putExtra("NOM", nom);
                    iRecetteDescription.putExtra("TEMPS_CUISSON", tempsCuisson);
                    iRecetteDescription.putExtra("TEMPS_PREPARATION", tempsPreparation);
                    iRecetteDescription.putExtra("PORTIONS", portions);
                    iRecetteDescription.putExtra("DESCRIPTION", description);
                    iRecetteDescription.putExtra("ORIGINE", origine);
                    iRecetteDescription.putExtra("REGIME", regime);
                    iRecetteDescription.putExtra("TYPE", type);
                    iRecetteDescription.putExtra("ETAPES", etape);
                    startActivity(iRecetteDescription);

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == btnRetour){
            finish();
        }
        else if(v == btnRechercher){

            String[] ingredientsListe = txtIngredient.getText().toString().split(",");
            String[] origineValues = getResources().getStringArray(R.array.spinner_choice_origine_values);
            String[] regimeValues = getResources().getStringArray(R.array.spinner_choice_regime_values);
            String[] typeValues = getResources().getStringArray(R.array.spinner_choice_type_values);

            String origine = this.origine.getSelectedItemPosition() == 0 ? "" : origineValues[this.origine.getSelectedItemPosition()];
            String regime = this.regime.getSelectedItemPosition() == 0 ? "" : regimeValues[this.regime.getSelectedItemPosition()];
            String type = this.type.getSelectedItemPosition() == 0 ? "" : typeValues[this.type.getSelectedItemPosition()];

            filtre = new Filtre(origine, regime, type, ingredientsListe);
            presentateurRecette.obtenirRecettes(filtre ,new RecettesCallback() {
                @Override
                public void onRecettesReceived(List<Recette> recettes) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ListeRecette.this.recettes = presentateurRecette.getRecettes();
                            adaptateur.clear();
                            adaptateur.addAll(recettes);
                            raffraichirListe();
                        }
                    });

                }

            });

    }
 }

    public void raffraichirListe() {
        this.adaptateur.notifyDataSetChanged();
        Toast.makeText(this,adaptateur.getCount()+" recettes retrouvées",Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Initialize the adapter if null
        if (adaptateur == null) {
            adaptateur = new AdapteurRecette(this, R.layout.layout_recettes, presentateurRecette);
            listeRecette.setAdapter(adaptateur);
        } else {
            adaptateur.clear();
            adaptateur.notifyDataSetChanged();
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        int r = checkSelfPermission("android.permission.INTERNET");
//        if (r == PackageManager.PERMISSION_GRANTED) {
//            //    Toast.makeText(this, "Accès Internet permis!", Toast.LENGTH_LONG).show();
//            this.presentateurRecette.obtenirRecettes(filtre, new RecettesCallback() {
//                @Override
//                public void onRecettesReceived(List<Recette> recettes) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            ListeRecette.this.recettes = presentateurRecette.getRecettes();
//                            adaptateur.clear();
//                            adaptateur.addAll(recettes);
//                            raffraichirListe();
//                        }
//                    });
//
//                }
//            }
//            );
//        } else {
//            Toast.makeText(this, "Accès Internet non permis!", Toast.LENGTH_LONG).show();
//        }
//    }
}
