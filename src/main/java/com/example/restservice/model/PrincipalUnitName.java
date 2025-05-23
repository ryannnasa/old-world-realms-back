package com.example.restservice.model;

public class PrincipalUnitName {
    private int idPrincipalUnitName;
    private String principalUnitName;
    private String unitSize;
    private int troopType_idTroopType;

    public PrincipalUnitName() {
    }

    public PrincipalUnitName(int idPrincipalUnitName, String PrincipalUnitName, String unitSize, int troopType_idTroopType) {
        this.idPrincipalUnitName = idPrincipalUnitName;
        this.principalUnitName = PrincipalUnitName;
        this.unitSize = unitSize;
        this.troopType_idTroopType = troopType_idTroopType;
    }

    public int getIdPrincipalUnitName() { return idPrincipalUnitName; }
    public void setIdPrincipalUnitName(int idPrincipalUnitName) { this.idPrincipalUnitName = idPrincipalUnitName; }

    public String getPrincipalUnitName() { return principalUnitName; }
    public void setPrincipalUnitName(String principalUnitName) { this.principalUnitName = principalUnitName; }

    public String getUnitSize() { return unitSize; }
    public void setUnitSize(String unitSize) { this.unitSize = unitSize; }

    public int getTroopType_idTroopType() { return troopType_idTroopType; }
    public void setTroopType_idTroopType(int troopType_idTroopType) { this.troopType_idTroopType = troopType_idTroopType; }
}
