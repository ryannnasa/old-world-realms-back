package com.example.restservice.model;

public class DescriptionMonture {
    private int idDescriptionMonture;
    private String descriptionMonture;

    public DescriptionMonture() {
    }

    public DescriptionMonture(int idDescriptionMonture, String descriptionMonture) {
        this.idDescriptionMonture = idDescriptionMonture;
        this.descriptionMonture = descriptionMonture;
    }

    public int getIdDescriptionMonture() {return idDescriptionMonture;}
    public void setIdDescriptionMonture(int idDescriptionMonture) {this.idDescriptionMonture = idDescriptionMonture;}

    public String getDescriptionMonture() {return descriptionMonture;}
    public void setDescriptionMonture(String descriptionMonture) {this.descriptionMonture = descriptionMonture;}
}
