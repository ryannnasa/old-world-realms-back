package com.example.restservice.model;

public class NomPrincipalUnite {
    private int idNomPrincipalUnite;
    private String nomPrincipalUnite;
    private String tailleUnite;
    private int idTypeTroupe;

    public NomPrincipalUnite() {
    }

    public NomPrincipalUnite(int idNomPrincipalUnite, String nomPrincipalUnite, String tailleUnite, int idTypeTroupe) {
        this.idNomPrincipalUnite = idNomPrincipalUnite;
        this.nomPrincipalUnite = nomPrincipalUnite;
        this.tailleUnite = tailleUnite;
        this.idTypeTroupe = idTypeTroupe;
    }

    public int getIdNomPrincipalUnite() { return idNomPrincipalUnite; }
    public void setIdNomPrincipalUnite(int idNomPrincipalUnite) { this.idNomPrincipalUnite = idNomPrincipalUnite; }

    public String getNomPrincipalUnite() { return nomPrincipalUnite; }
    public void setNomPrincipalUnite(String nomPrincipalUnite) { this.nomPrincipalUnite = nomPrincipalUnite; }

    public String getTailleUnite() { return tailleUnite; }
    public void setTailleUnite(String tailleUnite) { this.tailleUnite = tailleUnite; }

    public int getIdTypeTroupe() { return idTypeTroupe; }
    public void setIdTypeTroupe(int idTypeTroupe) { this.idTypeTroupe = idTypeTroupe; }
}
