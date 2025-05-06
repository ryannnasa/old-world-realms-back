package com.example.restservice.model;

public class AttachedUnit {
    private int idUnit;
    private int idUnit1;

    public AttachedUnit() {
    }

    public AttachedUnit(int idUnit, int idUnit1) {
        this.idUnit = idUnit;
        this.idUnit1 = idUnit1;
    }

    public int getIdUnit() { return idUnit; }
    public void setIdUnit(int idUnit) { this.idUnit = idUnit; }

    public int getIdUnit1() { return idUnit1; }
    public void setIdUnit1(int idUnit1) { this.idUnit1 = idUnit1; }
}

