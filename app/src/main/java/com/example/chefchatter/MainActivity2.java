package com.example.chefchatter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    private Button btnRetour;
    private Button creationCompte;
    private Button mdpOublie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnRetour = findViewById(R.id.btnRetourActMain2);
        btnRetour.setOnClickListener(this);

        creationCompte = findViewById(R.id.btnPasCompteMain2);
        creationCompte.setOnClickListener(this);

        mdpOublie = findViewById(R.id.btnMdpOublieMain2);
        mdpOublie.setOnClickListener(this);
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
    }
}