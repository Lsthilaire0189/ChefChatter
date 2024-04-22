package com.example.chefchatter.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chefchatter.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button boutonCreationCompte;
    Button boutonConnexion;
    Button boutonAfficherRecette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boutonCreationCompte=findViewById(R.id.btnConnexionMain);
        boutonConnexion=findViewById(R.id.btnCreerCompteMain);
        boutonAfficherRecette=findViewById(R.id.btnParcourtRecetteMain);
        boutonCreationCompte.setOnClickListener(this);
        boutonConnexion.setOnClickListener(this);
        boutonAfficherRecette.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view == boutonCreationCompte) {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }
        if(view==boutonConnexion)
        {
            Intent intent=new Intent(this, MainActivity3.class);
            startActivity(intent);
        }
        if(view==boutonAfficherRecette)
        {
            Intent intent= new Intent(this, AffichageRecette.class);
            startActivity(intent);
        }
    }
}