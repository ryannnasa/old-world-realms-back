package com.example.restservice.model;

public class MountUnit {
    private int mount_idMount;
    private int unit_idMountedUnit;
    private int unit_idUnit;
    private int mountRule_idMountRule;
    private int mountDescription_idMountDescription;
    private int idMountUnit;

    public MountUnit() {
    }

    public MountUnit(int mount_idMount, int unit_idMountedUnit, int unit_idUnit, int mountRule_idMountRule, int mountDescription_idMountDescription, int idMountUnit) {
        this.mount_idMount = mount_idMount;
        this.unit_idMountedUnit = unit_idMountedUnit;
        this.unit_idUnit = unit_idUnit;
        this.mountRule_idMountRule = mountRule_idMountRule;
        this.mountDescription_idMountDescription = mountDescription_idMountDescription;
        this.idMountUnit = idMountUnit;
    }

    public int getMount_idMount() { return mount_idMount; }
    public void setMount_idMount(int mount_idMount) { this.mount_idMount = mount_idMount; }

    public int getUnit_idMountedUnit() { return unit_idMountedUnit; }
    public void setUnit_idMountedUnit(int unit_idMountedUnit) { this.unit_idMountedUnit = unit_idMountedUnit; }

    public int getUnit_idUnit() { return unit_idUnit; }
    public void setUnit_idUnit(int unit_idUnit) { this.unit_idUnit = unit_idUnit; }

    public int getMountRule_idMountRule() { return mountRule_idMountRule; }
    public void setMountRule_idMountRule(int mountRule_idMountRule) { this.mountRule_idMountRule = mountRule_idMountRule; }

    public int getMountDescription_idMountDescription() { return mountDescription_idMountDescription; }
    public void setMountDescription_idMountDescription(int mountDescription_idMountDescription) { this.mountDescription_idMountDescription = mountDescription_idMountDescription; }

    public int getIdMountUnit() {return idMountUnit;}
    public void setIdMountUnit(int idMountUnit) {this.idMountUnit = idMountUnit;}
}
