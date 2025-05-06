package com.example.restservice.model;

public class MountUnit {
    private int idMount;
    private int idMountedUnit;
    private int idUnit;
    private int idMountRule;
    private int idMountDescription;

    public MountUnit() {
    }

    public MountUnit(int idMount, int idMountedUnit, int idUnit, int idMountRule, int idMountDescription) {
        this.idMount = idMount;
        this.idMountedUnit = idMountedUnit;
        this.idUnit = idUnit;
        this.idMountRule = idMountRule;
        this.idMountDescription = idMountDescription;
    }

    public int getIdMount() { return idMount; }
    public void setIdMount(int idMount) { this.idMount = idMount; }

    public int getIdMountedUnit() { return idMountedUnit; }
    public void setIdMountedUnit(int idMountedUnit) { this.idMountedUnit = idMountedUnit; }

    public int getIdUnit() { return idUnit; }
    public void setIdUnit(int idUnit) { this.idUnit = idUnit; }

    public int getIdMountRule() { return idMountRule; }
    public void setIdMountRule(int idMountRule) { this.idMountRule = idMountRule; }

    public int getIdMountDescription() { return idMountDescription; }
    public void setIdMountDescription(int idMountDescription) { this.idMountDescription = idMountDescription; }
}
