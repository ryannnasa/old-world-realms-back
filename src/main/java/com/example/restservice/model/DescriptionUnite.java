package com.example.restservice.model;

public class DescriptionUnite {
    private int idDescriptionUnite;
    private String descriptionUnite;

    public DescriptionUnite() {
    }

    public DescriptionUnite(int idDescriptionUnite, String descriptionUnite) {
        this.idDescriptionUnite = idDescriptionUnite;
        this.descriptionUnite = descriptionUnite;
    }

    public int getIdDescriptionUnite() {
        return idDescriptionUnite;
    }

    public void setIdDescriptionUnite(int idDescriptionUnite) {
        this.idDescriptionUnite = idDescriptionUnite;
    }

    public String getDescriptionUnite() {
        return descriptionUnite;
    }

    public void setDescriptionUnite(String descriptionUnite) {
        this.descriptionUnite = descriptionUnite;
    }
}