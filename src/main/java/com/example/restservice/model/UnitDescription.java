package com.example.restservice.model;

public class UnitDescription {
    private int idUnitDescription;
    private String unitDescription;

    public UnitDescription() {
    }

    public UnitDescription(int idUnitDescription, String unitDescription) {
        this.idUnitDescription = idUnitDescription;
        this.unitDescription = unitDescription;
    }

    public int getIdUnitDescription() {
        return idUnitDescription;
    }

    public void setIdUnitDescription(int idUnitDescription) {
        this.idUnitDescription = idUnitDescription;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }
}