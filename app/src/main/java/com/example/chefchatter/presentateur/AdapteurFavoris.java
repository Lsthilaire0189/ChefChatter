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
import com.example.chefchatter.modele.Favoris;
import com.example.chefchatter.modele.Recette;

public class AdapteurFavoris extends ArrayAdapter{

        private Context contexte;
        private int viewResourceId;
        private Resources resources;
        private PresentateurFavoris presentateur;
        public AdapteurFavoris(@NonNull Context context, int resource, PresentateurFavoris presentateur) {
            super(context, resource);
            this.contexte = context;
            this.viewResourceId = resource;
            this.resources = contexte.getResources();
            this.presentateur = presentateur;
        }
        @Override
        public int getCount() {
            return this.presentateur.getNbRecettesFavorites();
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
            final Recette recette = this.presentateur.getRecetteFavorie(position);
            if (recette != null) {
                final TextView tvNomRecette = (TextView) view.findViewById(R.id.lrTvNomRecette);
                final TextView tvPrenomChef = (TextView) view.findViewById(R.id.lrTvPrenomChef);
                final ImageView imageRecette = (ImageView) view.findViewById(R.id.lrIvPhoto);

                tvNomRecette.setText(String.format("Nom de la recette: %s", recette.getNom()));
                tvPrenomChef.setText(String.format("Chef: %s", recette.getPrenom()));
                Glide.with(this.contexte)
                        .load(recette.getSrc())
                        .into(imageRecette);
            }
            return view;
        }



}
