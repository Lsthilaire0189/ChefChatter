package com.example.chefchatter.modele;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Avis {

    @JsonProperty("id")
    private int id;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("recetteId")
    private int recetteId;
    @JsonProperty("rating")
    private int rating;
    @JsonProperty("commentaire")
    private String commentaire;
    @JsonProperty("username")
    private String username;

    public Avis() {
        // Default constructor
    }

    public Avis(int id, String userId, int recetteId, int rating, String commentaire, String username) {
        this.id = id;
        this.userId = userId;
        this.recetteId = recetteId;
        this.rating = rating;
        this.commentaire = commentaire;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRecetteId() {
        return recetteId;
    }

    public void setRecetteId(int recetteId) {
        this.recetteId = recetteId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
