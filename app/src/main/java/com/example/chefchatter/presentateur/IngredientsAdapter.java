package com.example.chefchatter.presentateur;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.chefchatter.R;
import com.example.chefchatter.modele.Recette;
import com.example.chefchatter.modele.Recette_Ingredient;

public class IngredientsAdapter extends ArrayAdapter<Recette_Ingredient> {


    private Context contexte;
    private int viewResourceId;
    private Resources resources;
    private PresentateurIngredients presentateur;

    public IngredientsAdapter(@NonNull Context context, int resource, PresentateurIngredients presentateur) {
        super(context, resource);
        this.contexte = context;
        this.viewResourceId = resource;
        this.resources = contexte.getResources();
        this.presentateur = presentateur;
    }

    @Override
    public int getCount() {
        return this.presentateur.getNbIngredients();
    }
    @SuppressLint("NewApi")
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) contexte.getSystemService(Context.
                    LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(this.viewResourceId, parent, false);
        }
        final Recette_Ingredient ingredient = this.presentateur.getIngredient(position);
        if (ingredient != null) {
            final TextView tvIngredient = (TextView) view.findViewById(R.id.tvIngredient);
            final TextView tvQt = (TextView) view.findViewById(R.id.tvQt);
            tvIngredient.setText(String.format(ingredient.getIngredient()));
            tvQt.setText(String.format(ingredient.getQuantite()));
        }
        return view;
    }
}
