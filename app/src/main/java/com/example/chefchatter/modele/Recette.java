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
    private int preparation;
    @JsonProperty("cuisson")
    private int cuisson;
    @JsonProperty("portion")
    private int portion;

    public Recette() {
        // Default constructor
    }

    public Recette(int id, String nom, String origine, String regime, String type, String description, String etape, String src, String email, int preparation, int cuisson, int portion) {
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

    public int getPreparation() {
        return preparation;
    }

    public void setPreparation(int preparation) {
        this.preparation = preparation;
    }

    public int getCuisson() {
        return cuisson;
    }

    public void setCuisson(int cuisson) {
        this.cuisson = cuisson;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }
}
