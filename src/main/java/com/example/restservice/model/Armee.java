package com.example.restservice.model;

public class Armee {
    private String conditionTypeUnite;
    private int idNomArmee;
    private int idCompositionArmee;
    private int idTypeUnite;

    public Armee() {}

    public Armee(int idNomArmee, int idCompositionArmee, int idTypeUnite, String conditionTypeUnite) {
        this.idNomArmee = idNomArmee;
        this.idCompositionArmee = idCompositionArmee;
        this.idTypeUnite = idTypeUnite;
        this.conditionTypeUnite = conditionTypeUnite;
    }

    public int getIdNomArmee() {return idNomArmee;}
    public void setIdNomArmee(int idNomArmee) {this.idNomArmee = idNomArmee;}

    public int getIdCompositionArmee() { return idCompositionArmee; }
    public void setIdCompositionArmee(int idCompositionArmee) { this.idCompositionArmee = idCompositionArmee; }

    public int getIdTypeUnite() { return idTypeUnite; }
    public void setIdTypeUnite(int idTypeUnite) { this.idTypeUnite = idTypeUnite; }

    public String getConditionTypeUnite() { return conditionTypeUnite; }
    public void setConditionTypeUnite(String conditionTypeUnite) { this.conditionTypeUnite = conditionTypeUnite; }
}
