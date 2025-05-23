package com.example.restservice.model;

public class MountedUnitPhoto {
    private int idMountedUnitPhoto;
    private String nameOfMountedUnitPhoto;
    private int mountUnit_mount_idMount;
    private int mountUnit_unit_idUnit;
    private int mountUnit_mountRule_idMountRule;
    private int mountUnit_Unit_idMountedUnit;
    private int mountUnit_idMountDescription;

    public MountedUnitPhoto() {}

    public MountedUnitPhoto(int idMountedUnitPhoto, String nameOfMountedUnitPhoto, int mountUnit_mount_idMount, int mountUnit_unit_idUnit, int mountUnit_mountRule_idMountRule, int mountUnit_Unit_idMountedUnit, int idMountDescription) {
        this.mountUnit_mount_idMount = mountUnit_mount_idMount;
        this.mountUnit_idMountDescription = idMountDescription;
        this.mountUnit_Unit_idMountedUnit = mountUnit_Unit_idMountedUnit;
        this.mountUnit_unit_idUnit = mountUnit_unit_idUnit;
        this.mountUnit_mountRule_idMountRule = mountUnit_mountRule_idMountRule;
        this.idMountedUnitPhoto = idMountedUnitPhoto;
        this.nameOfMountedUnitPhoto = nameOfMountedUnitPhoto;
    }

    public int getMountUnit_mount_idMount() {return mountUnit_mount_idMount;}
    public void setMountUnit_mount_idMount(int mountUnit_mount_idMount) {this.mountUnit_mount_idMount = mountUnit_mount_idMount;}

    public int getIdMountedUnitPhoto() {return idMountedUnitPhoto;}
    public void setIdMountedUnitPhoto(int idMountedUnitPhoto) {this.idMountedUnitPhoto = idMountedUnitPhoto;}

    public int getMountUnit_Unit_idMountedUnit() {return mountUnit_Unit_idMountedUnit;}
    public void setMountUnit_Unit_idMountedUnit(int mountUnit_Unit_idMountedUnit) {this.mountUnit_Unit_idMountedUnit = mountUnit_Unit_idMountedUnit;}

    public int getMountUnit_unit_idUnit() {return mountUnit_unit_idUnit;}
    public void setMountUnit_unit_idUnit(int mountUnit_unit_idUnit) {this.mountUnit_unit_idUnit = mountUnit_unit_idUnit;}

    public int getMountUnit_idMountDescription() {return mountUnit_idMountDescription;}
    public void setMountUnit_idMountDescription(int mountUnit_idMountDescription) {this.mountUnit_idMountDescription = mountUnit_idMountDescription;}

    public int getMountUnit_mountRule_idMountRule() {return mountUnit_mountRule_idMountRule;}
    public void setMountUnit_mountRule_idMountRule(int mountUnit_mountRule_idMountRule) {this.mountUnit_mountRule_idMountRule = mountUnit_mountRule_idMountRule;}

    public String getNameOfMountedUnitPhoto() {return nameOfMountedUnitPhoto;}
    public void setNameOfMountedUnitPhoto(String nameOfMountedUnitPhoto) {this.nameOfMountedUnitPhoto = nameOfMountedUnitPhoto;}
}
