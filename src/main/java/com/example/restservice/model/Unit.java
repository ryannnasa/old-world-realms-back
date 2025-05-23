package com.example.restservice.model;

public class Unit {
    private int idUnit;
    private int unitName_idUnitName;
    private int principalUnitName_idPrincipalUnitName;
    private int army_armyName_idArmyName;
    private int army_armyComposition_idArmyComposition;
    private int army_unitType_idUnitType;
    private int unitDescription_idUnitDescription;

    public Unit() {
    }

    public Unit(int idUnit, int unitName_idUnitName, int principalUnitName_idPrincipalUnitName, int army_armyName_idArmyName,
                int army_armyComposition_idArmyComposition, int army_unitType_idUnitType, int unitDescription_idUnitDescription) {
        this.idUnit = idUnit;
        this.unitName_idUnitName = unitName_idUnitName;
        this.principalUnitName_idPrincipalUnitName = principalUnitName_idPrincipalUnitName;
        this.army_armyName_idArmyName = army_armyName_idArmyName;
        this.army_armyComposition_idArmyComposition = army_armyComposition_idArmyComposition;
        this.army_unitType_idUnitType = army_unitType_idUnitType;
        this.unitDescription_idUnitDescription = unitDescription_idUnitDescription;
    }

    public int getIdUnit() { return idUnit; }
    public void setIdUnit(int idUnit) { this.idUnit = idUnit; }

    public int getUnitName_idUnitName() { return unitName_idUnitName; }
    public void setUnitName_idUnitName(int unitName_idUnitName) { this.unitName_idUnitName = unitName_idUnitName; }

    public int getPrincipalUnitName_idPrincipalUnitName() { return principalUnitName_idPrincipalUnitName; }
    public void setPrincipalUnitName_idPrincipalUnitName(int principalUnitName_idPrincipalUnitName) { this.principalUnitName_idPrincipalUnitName = principalUnitName_idPrincipalUnitName; }

    public int getArmy_armyName_idArmyName() { return army_armyName_idArmyName; }
    public void setArmy_armyName_idArmyName(int army_armyName_idArmyName) { this.army_armyName_idArmyName = army_armyName_idArmyName; }

    public int getArmy_armyComposition_idArmyComposition() { return army_armyComposition_idArmyComposition; }
    public void setArmy_armyComposition_idArmyComposition(int army_armyComposition_idArmyComposition) { this.army_armyComposition_idArmyComposition = army_armyComposition_idArmyComposition; }

    public int getArmy_unitType_idUnitType() { return army_unitType_idUnitType; }
    public void setArmy_unitType_idUnitType(int army_unitType_idUnitType) { this.army_unitType_idUnitType = army_unitType_idUnitType; }

    public int getUnitDescription_idUnitDescription() { return unitDescription_idUnitDescription; }
    public void setUnitDescription_idUnitDescription(int unitDescription_idUnitDescription) { this.unitDescription_idUnitDescription = unitDescription_idUnitDescription; }
}
