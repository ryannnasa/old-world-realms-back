package com.example.restservice.model;

public class TypePointsHasUniteMonture {
    private int idMonture;
    private int idUniteMontee;
    private int idUnite;
    private int idRoleMonture;
    private int idDescriptionMonture;
    private int idTypePoints;
    private int nombrePoints;
    private int idCasPoints;

    public TypePointsHasUniteMonture() {
    }

    public TypePointsHasUniteMonture(int idMonture, int idUniteMontee, int idUnite, int idRoleMonture,
                                     int idDescriptionMonture, int idTypePoints, int nombrePoints, int idCasPoints) {
        this.idMonture = idMonture;
        this.idUniteMontee = idUniteMontee;
        this.idUnite = idUnite;
        this.idRoleMonture = idRoleMonture;
        this.idDescriptionMonture = idDescriptionMonture;
        this.idTypePoints = idTypePoints;
        this.nombrePoints = nombrePoints;
        this.idCasPoints = idCasPoints;
    }

    public int getIdMonture() { return idMonture; }
    public void setIdMonture(int idMonture) { this.idMonture = idMonture; }

    public int getIdUniteMontee() { return idUniteMontee; }
    public void setIdUniteMontee(int idUniteMontee) { this.idUniteMontee = idUniteMontee; }

    public int getIdUnite() { return idUnite; }
    public void setIdUnite(int idUnite) { this.idUnite = idUnite; }

    public int getIdRoleMonture() { return idRoleMonture; }
    public void setIdRoleMonture(int idRoleMonture) { this.idRoleMonture = idRoleMonture; }

    public int getIdDescriptionMonture() { return idDescriptionMonture; }
    public void setIdDescriptionMonture(int idDescriptionMonture) { this.idDescriptionMonture = idDescriptionMonture; }

    public int getIdTypePoints() { return idTypePoints; }
    public void setIdTypePoints(int idTypePoints) { this.idTypePoints = idTypePoints; }

    public int getNombrePoints() { return nombrePoints; }
    public void setNombrePoints(int nombrePoints) { this.nombrePoints = nombrePoints; }

    public int getIdCasPoints() { return idCasPoints; }
    public void setIdCasPoints(int idCasPoints) { this.idCasPoints = idCasPoints; }
}
