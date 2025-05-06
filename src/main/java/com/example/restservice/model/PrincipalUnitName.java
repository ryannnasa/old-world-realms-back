package com.example.restservice.model;

public class PrincipalUnitName {
    private int idPrincipalUnitName;
    private String principalUnitName;
    private String unitSize;
    private int idTroopType;

    public PrincipalUnitName() {
    }

    public PrincipalUnitName(int idPrincipalUnitName, String PrincipalUnitName, String unitSize, int idTroopType) {
        this.idPrincipalUnitName = idPrincipalUnitName;
        this.principalUnitName = PrincipalUnitName;
        this.unitSize = unitSize;
        this.idTroopType = idTroopType;
    }

    public int getIdPrincipalUnitName() { return idPrincipalUnitName; }
    public void setIdPrincipalUnitName(int idPrincipalUnitName) { this.idPrincipalUnitName = idPrincipalUnitName; }

    public String getPrincipalUnitName() { return principalUnitName; }
    public void setPrincipalUnitName(String principalUnitName) { this.principalUnitName = principalUnitName; }

    public String getUnitSize() { return unitSize; }
    public void setUnitSize(String unitSize) { this.unitSize = unitSize; }

    public int getIdTroopType() { return idTroopType; }
    public void setIdTroopType(int idTroopType) { this.idTroopType = idTroopType; }
}
