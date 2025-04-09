package com.example.restservice.model;

public class UniteMonture {
    private int idMonture;
    private int idUniteMontee;
    private int idUnite;
    private int idRoleMonture;
    private int idDescriptionMonture;

    public UniteMonture() {
    }

    public UniteMonture(int idMonture, int idUniteMontee, int idUnite, int idRoleMonture, int idDescriptionMonture) {
        this.idMonture = idMonture;
        this.idUniteMontee = idUniteMontee;
        this.idUnite = idUnite;
        this.idRoleMonture = idRoleMonture;
        this.idDescriptionMonture = idDescriptionMonture;
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
}
