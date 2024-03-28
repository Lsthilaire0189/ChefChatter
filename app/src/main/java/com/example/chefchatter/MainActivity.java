package com.example.chefchatter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button boutonCreationCompte;
    Button boutonConnexion;
    Button boutonAfficherRecette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boutonCreationCompte=findViewById(R.id.button2);
        boutonConnexion=findViewById(R.id.button5);
        boutonAfficherRecette=findViewById(R.id.button4);
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