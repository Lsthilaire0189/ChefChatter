package com.example.chefchatter.modele;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Recette {

    @JsonProperty("id")
    private int id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("origine")
    private String origine;
    @JsonProperty("regime")
    private String regime;
    @JsonProperty("type")
    private String type;
    @JsonProperty("description")
    private String description;
    @JsonProperty("etape")
    private String etape;
    @JsonProperty("src")
    private String src;
    @JsonProperty("email")
    private String email;
    @JsonProperty("preparation")
    private String preparation;
    @JsonProperty("cuisson")
    private String cuisson;
    @JsonProperty("portion")
    private String portion;
    @JsonProperty("prenom")
    private String prenom;

    public Recette() {
        // Default constructor
    }

    public Recette(int id, String nom, String origine, String regime, String type, String description, String etape, String src, String email, String preparation, String cuisson, String portion, String prenom) {
        this.id = id;
        this.nom = nom;
        this.origine = origine;
        this.regime = regime;
        this.type = type;
        this.description = description;
        this.etape = etape;
        this.src = src;
        this.email = email;
        this.preparation = preparation;
        this.cuisson = cuisson;
        this.portion = portion;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtape() {
        return etape;
    }

    public void setEtape(String etape) {
        this.etape = etape;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getCuisson() {
        return cuisson;
    }

    public void setCuisson(String cuisson) {
        this.cuisson = cuisson;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
