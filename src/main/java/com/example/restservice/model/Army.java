package com.example.restservice.model;

public class Army {
    private String unitTypeCondition;
    private int armyName_idArmyName;
    private int armyComposition_idArmyComposition;
    private int unitType_idUnitType;

    public Army() {}

    public Army(int armyName_idArmyName, int armyComposition_idArmyComposition, int unitType_idUnitType, String unitTypeCondition) {
        this.armyName_idArmyName = armyName_idArmyName;
        this.armyComposition_idArmyComposition = armyComposition_idArmyComposition;
        this.unitType_idUnitType = unitType_idUnitType;
        this.unitTypeCondition = unitTypeCondition;
    }

    public int getArmyName_idArmyName() {return armyName_idArmyName;}
    public void setArmyName_idArmyName(int armyName_idArmyName) {this.armyName_idArmyName = armyName_idArmyName;}

    public int getArmyComposition_idArmyComposition() { return armyComposition_idArmyComposition; }
    public void setArmyComposition_idArmyComposition(int armyComposition_idArmyComposition) { this.armyComposition_idArmyComposition = armyComposition_idArmyComposition; }

    public int getUnitType_idUnitType() { return unitType_idUnitType; }
    public void setUnitType_idUnitType(int unitType_idUnitType) { this.unitType_idUnitType = unitType_idUnitType; }

    public String getUnitTypeCondition() { return unitTypeCondition; }
    public void setUnitTypeCondition(String unitTypeCondition) { this.unitTypeCondition = unitTypeCondition; }
}
