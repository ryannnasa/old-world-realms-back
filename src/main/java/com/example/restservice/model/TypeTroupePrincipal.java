package com.example.restservice.model;

public class TypeTroupePrincipal {
    private int idTypeTroupePrincipal;
    private String nomTypeTroupePrincipal;

    public TypeTroupePrincipal() {
    }

    public TypeTroupePrincipal(int idTypeTroupePrincipal, String nomTypeTroupePrincipal) {
        this.idTypeTroupePrincipal = idTypeTroupePrincipal;
        this.nomTypeTroupePrincipal = nomTypeTroupePrincipal;
    }

    public int getIdTypeTroupePrincipal() {return idTypeTroupePrincipal;}
    public void setIdTypeTroupePrincipal(int idTypeTroupePrincipal) {this.idTypeTroupePrincipal = idTypeTroupePrincipal;}

    public String getNomTypeTroupePrincipal() {return nomTypeTroupePrincipal;}
    public void setNomTypeTroupePrincipal(String nomTypeTroupePrincipal) {this.nomTypeTroupePrincipal = nomTypeTroupePrincipal;}
}
