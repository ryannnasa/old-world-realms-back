package com.example.restservice.model;

public class UnitName {
    private int idUnitName;
    private String unitName;

    public UnitName() {
    }

    public UnitName(int idUnitName, String unitName) {
        this.idUnitName = idUnitName;
        this.unitName = unitName;
    }

    public int getIdUnitName() {return idUnitName;}
    public void setIdUnitName(int idUnitName) {this.idUnitName = idUnitName;}

    public String getUnitName() {return unitName;}
    public void setUnitName(String unitName) {this.unitName = unitName;}
}
