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
import com.example.chefchatter.modele.Avis;
import com.example.chefchatter.modele.Recette;

public class AdapterAvis extends ArrayAdapter<Avis> {

    private Context contexte;
    private int viewResourceId;
    private Resources resources;
    private PresentateurAvis presentateur;
    public AdapterAvis(@NonNull Context context, int resource, PresentateurAvis presentateur) {
        super(context, resource);
        this.contexte = context;
        this.viewResourceId = resource;
        this.resources = contexte.getResources();
        this.presentateur = presentateur;
    }
    @Override
    public int getCount() {
        return this.presentateur.getNbAvis();
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
        final Avis avis = this.presentateur.getAvis(position);
        if (avis != null) {
            final TextView tvUsername = view.findViewById(R.id.tvUsername);
            final TextView tvCommentaire = view.findViewById(R.id.tvCommentaire);
            final TextView tvNote = view.findViewById(R.id.tvNote);

            tvUsername.setText(String.format("Username: " + avis.getUsername()));
            tvCommentaire.setText(String.format("Commentaire:" + avis.getCommentaire()));
            tvNote.setText(String.format("Note" + avis.getRating() + "/5"));
        }
        return view;
    }
}
