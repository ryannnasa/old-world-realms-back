package com.example.restservice.model;

public class Unit {
    private int idUnit;
    private int idUnitName;
    private int idPrincipalUnitName;
    private int idArmyName;
    private int idArmyComposition;
    private int idUnitType;
    private int idUnitDescription;

    public Unit() {
    }

    public Unit(int idUnit, int idUnitName, int idPrincipalUnitName, int idArmyName,
                int idArmyComposition, int idUnitType, int idUnitDescription) {
        this.idUnit = idUnit;
        this.idUnitName = idUnitName;
        this.idPrincipalUnitName = idPrincipalUnitName;
        this.idArmyName = idArmyName;
        this.idArmyComposition = idArmyComposition;
        this.idUnitType = idUnitType;
        this.idUnitDescription = idUnitDescription;
    }

    public int getIdUnit() { return idUnit; }
    public void setIdUnit(int idUnit) { this.idUnit = idUnit; }

    public int getIdUnitName() { return idUnitName; }
    public void setIdUnitName(int idUnitName) { this.idUnitName = idUnitName; }

    public int getIdPrincipalUnitName() { return idPrincipalUnitName; }
    public void setIdPrincipalUnitName(int idPrincipalUnitName) { this.idPrincipalUnitName = idPrincipalUnitName; }

    public int getIdArmyName() { return idArmyName; }
    public void setIdArmyName(int idArmyName) { this.idArmyName = idArmyName; }

    public int getIdArmyComposition() { return idArmyComposition; }
    public void setIdArmyComposition(int idArmyComposition) { this.idArmyComposition = idArmyComposition; }

    public int getIdUnitType() { return idUnitType; }
    public void setIdUnitType(int idUnitType) { this.idUnitType = idUnitType; }

    public int getIdUnitDescription() { return idUnitDescription; }
    public void setIdUnitDescription(int idUnitDescription) { this.idUnitDescription = idUnitDescription; }
}
