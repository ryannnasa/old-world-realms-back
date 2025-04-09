package com.example.restservice.model;

public class TypePointsHasUnite {
    private int idUnite;
    private int idTypePoints;
    private int nombrePoints;
    private int idCasPoints;

    public TypePointsHasUnite() {
    }

    public TypePointsHasUnite(int idUnite, int idTypePoints, int nombrePoints, int idCasPoints) {
        this.idUnite = idUnite;
        this.idTypePoints = idTypePoints;
        this.nombrePoints = nombrePoints;
        this.idCasPoints = idCasPoints;
    }

    public int getIdUnite() { return idUnite; }
    public void setIdUnite(int idUnite) { this.idUnite = idUnite; }

    public int getIdTypePoints() { return idTypePoints; }
    public void setIdTypePoints(int idTypePoints) { this.idTypePoints = idTypePoints; }

    public int getNombrePoints() { return nombrePoints; }
    public void setNombrePoints(int nombrePoints) { this.nombrePoints = nombrePoints; }

    public int getIdCasPoints() { return idCasPoints; }
    public void setIdCasPoints(int idCasPoints) { this.idCasPoints = idCasPoints; }
}
