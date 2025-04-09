package com.example.restservice.model;

public class NomUnite {
    private int idNomUnite;
    private String nomUnite;

    public NomUnite() {
    }

    public NomUnite(int idNomUnite, String nomUnite) {
        this.idNomUnite = idNomUnite;
        this.nomUnite = nomUnite;
    }

    public int getIdNomUnite() {return idNomUnite;}
    public void setIdNomUnite(int idNomUnite) {this.idNomUnite = idNomUnite;}

    public String getNomUnite() {return nomUnite;}
    public void setNomUnite(String nomUnite) {this.nomUnite = nomUnite;}
}
