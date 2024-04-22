package com.example.chefchatter.activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chefchatter.R;

public class MdpOublie extends AppCompatActivity implements View.OnClickListener{
    private Button btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdp_oublie);

        btnRetour = findViewById(R.id.btnRetourMdpOublie);
        btnRetour.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnRetour){
            finish();
        }
    }
}
