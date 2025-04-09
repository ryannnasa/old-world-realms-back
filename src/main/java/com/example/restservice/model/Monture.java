package com.example.restservice.model;

public class Monture {
    private int idMonture;
    private String nomMonture;

    public Monture() {
    }

    public Monture(int idMonture, String nomMonture) {
        this.idMonture = idMonture;
        this.nomMonture = nomMonture;
    }

    public int getIdMonture() {return idMonture;}
    public void setIdMonture(int idMonture) {this.idMonture = idMonture;}

    public String getNomMonture() {return nomMonture;}
    public void setNomMonture(String nomMonture) {this.nomMonture = nomMonture;}
}
