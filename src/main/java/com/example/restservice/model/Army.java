package com.example.restservice.model;

public class Army {
    private String unitTypeCondition;
    private int idArmyName;
    private int idArmyComposition;
    private int idUnitType;

    public Army() {}

    public Army(int idArmyName, int idArmyComposition, int idUnitType, String unitTypeCondition) {
        this.idArmyName = idArmyName;
        this.idArmyComposition = idArmyComposition;
        this.idUnitType = idUnitType;
        this.unitTypeCondition = unitTypeCondition;
    }

    public int getIdArmyName() {return idArmyName;}
    public void setIdArmyName(int idArmyName) {this.idArmyName = idArmyName;}

    public int getIdArmyComposition() { return idArmyComposition; }
    public void setIdArmyComposition(int idArmyComposition) { this.idArmyComposition = idArmyComposition; }

    public int getIdUnitType() { return idUnitType; }
    public void setIdUnitType(int idUnitType) { this.idUnitType = idUnitType; }

    public String getUnitTypeCondition() { return unitTypeCondition; }
    public void setUnitTypeCondition(String unitTypeCondition) { this.unitTypeCondition = unitTypeCondition; }
}
