package com.example.restservice.model;

public class Unite {
    private int idUnite;
    private int idNomUnite;
    private int idNomPrincipalUnite;
    private int idNomArmee;
    private int idCompositionArmee;
    private int idTypeUnite;
    private int idDescriptionUnite;

    public Unite() {
    }

    public Unite(int idUnite, int idNomUnite, int idNomPrincipalUnite, int idNomArmee,
                 int idCompositionArmee, int idTypeUnite, int idDescriptionUnite) {
        this.idUnite = idUnite;
        this.idNomUnite = idNomUnite;
        this.idNomPrincipalUnite = idNomPrincipalUnite;
        this.idNomArmee = idNomArmee;
        this.idCompositionArmee = idCompositionArmee;
        this.idTypeUnite = idTypeUnite;
        this.idDescriptionUnite = idDescriptionUnite;
    }

    public int getIdUnite() { return idUnite; }
    public void setIdUnite(int idUnite) { this.idUnite = idUnite; }

    public int getIdNomUnite() { return idNomUnite; }
    public void setIdNomUnite(int idNomUnite) { this.idNomUnite = idNomUnite; }

    public int getIdNomPrincipalUnite() { return idNomPrincipalUnite; }
    public void setIdNomPrincipalUnite(int idNomPrincipalUnite) { this.idNomPrincipalUnite = idNomPrincipalUnite; }

    public int getIdNomArmee() { return idNomArmee; }
    public void setIdNomArmee(int idNomArmee) { this.idNomArmee = idNomArmee; }

    public int getIdCompositionArmee() { return idCompositionArmee; }
    public void setIdCompositionArmee(int idCompositionArmee) { this.idCompositionArmee = idCompositionArmee; }

    public int getIdTypeUnite() { return idTypeUnite; }
    public void setIdTypeUnite(int idTypeUnite) { this.idTypeUnite = idTypeUnite; }

    public int getIdDescriptionUnite() { return idDescriptionUnite; }
    public void setIdDescriptionUnite(int idDescriptionUnite) { this.idDescriptionUnite = idDescriptionUnite; }
}
