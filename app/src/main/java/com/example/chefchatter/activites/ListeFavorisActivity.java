package com.example.chefchatter.activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chefchatter.R;
import com.example.chefchatter.modele.Compte;
import com.example.chefchatter.modele.Favoris;
import com.example.chefchatter.modele.Recette;
import com.example.chefchatter.presentateur.AdapteurFavoris;
import com.example.chefchatter.presentateur.PresentateurCompte;
import com.example.chefchatter.presentateur.PresentateurFavoris;
import com.example.chefchatter.presentateur.PresentateurRecette;

public class ListeFavorisActivity extends AppCompatActivity implements View.OnClickListener{

    ListView listView;
    Button btnRetour;
    private PresentateurRecette presentateurRecette;
    private PresentateurFavoris presentateurFavoris;
    private AdapteurFavoris adapteurFavoris;
    private PresentateurCompte presentateurCompte;
    Compte compteCourant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_favoris);

        listView = findViewById(R.id.lfLv);
        btnRetour = findViewById(R.id.lfBtnRetour);
        btnRetour.setOnClickListener(this);
        presentateurCompte = new PresentateurCompte(this);

        compteCourant =  presentateurCompte.getCompte();
        if(compteCourant != null) {
            presentateurRecette = new PresentateurRecette(this);
            presentateurFavoris = new PresentateurFavoris(this);
            adapteurFavoris = new AdapteurFavoris(this, R.layout.layout_recettes, presentateurFavoris);
        }


        listView.setAdapter(adapteurFavoris);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                final Recette recetteSelectionne = presentateurFavoris.getRecetteFavorie(position);

                // Create an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ListeFavorisActivity.this);
                builder.setTitle("Choisisez une action")
                        .setItems(new String[]{"Voir Details", "Supprimer Recette"}, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    Intent iRecetteDescription = new Intent(getApplicationContext(), DescriptionRecette.class);
                                    Recette recetteSelectionnee = presentateurFavoris.getRecetteFavorie(position);
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
                                    iRecetteDescription.putExtra("ETAPE", etape);
                                    startActivity(iRecetteDescription);
                                } else if (which == 1) {
                                    // Delete
                                    Favoris favorisSelectionne = new Favoris(compteCourant.getCourriel(),recetteSelectionne.getId());
                                    presentateurFavoris.supprimer(favorisSelectionne);
                                }
                            }
                        });
                builder.create().show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        int r = checkSelfPermission("android.permission.INTERNET");
        if (r == PackageManager.PERMISSION_GRANTED) {
            //    Toast.makeText(this, "Accès Internet permis!", Toast.LENGTH_LONG).show();
            this.presentateurFavoris.obtenirRecettesFavorites (compteCourant.getCourriel());
        } else {
            Toast.makeText(this, "Accès Internet non permis!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.lfBtnRetour) {
            finish();
//        } else if () {
//            // TODO
//        }
        }
    }
    public void raffraichirListe() {
        this.adapteurFavoris.notifyDataSetChanged();
       // Toast.makeText(this,adaptateur.getCount()+" comptes",Toast.LENGTH_SHORT).show();
    }
    public void afficherMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
