package com.example.restservice.model;

public class AttachedUnit {
    private int unit_idUnit;
    private int unit_idUnit1;

    public AttachedUnit() {
    }

    public AttachedUnit(int unit_idUnit, int unit_idUnit1) {
        this.unit_idUnit = unit_idUnit;
        this.unit_idUnit1 = unit_idUnit1;
    }

    public int getUnit_idUnit() { return unit_idUnit; }
    public void setUnit_idUnit(int unit_idUnit) { this.unit_idUnit = unit_idUnit; }

    public int getUnit_idUnit1() { return unit_idUnit1; }
    public void setUnit_idUnit1(int unit_idUnit1) { this.unit_idUnit1 = unit_idUnit1; }
}

