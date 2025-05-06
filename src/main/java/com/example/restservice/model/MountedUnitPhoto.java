package com.example.restservice.model;

public class MountedUnitPhoto {
    private int idMountedUnitPhoto;
    private String nameOfMountedUnitPhoto;
    private int idMount;
    private int idUnit;
    private int idMountRule;
    private int idMountedUnit;
    private int idMountDescription;

    public MountedUnitPhoto() {}

    public MountedUnitPhoto(int idMountedUnitPhoto, String nameOfMountedUnitPhoto, int idMount, int idUnit, int idMountRule, int idMountedUnit, int idMountDescription) {
        this.idMount = idMount;
        this.idMountDescription = idMountDescription;
        this.idMountedUnit = idMountedUnit;
        this.idUnit = idUnit;
        this.idMountRule = idMountRule;
        this.idMountedUnitPhoto = idMountedUnitPhoto;
        this.nameOfMountedUnitPhoto = nameOfMountedUnitPhoto;
    }

    public int getIdMount() {return idMount;}
    public void setIdMount(int idMount) {this.idMount = idMount;}

    public int getIdMountedUnitPhoto() {return idMountedUnitPhoto;}
    public void setIdMountedUnitPhoto(int idMountedUnitPhoto) {this.idMountedUnitPhoto = idMountedUnitPhoto;}

    public int getIdMountedUnit() {return idMountedUnit;}
    public void setIdMountedUnit(int idMountedUnit) {this.idMountedUnit = idMountedUnit;}

    public int getIdUnit() {return idUnit;}
    public void setIdUnit(int idUnit) {this.idUnit = idUnit;}

    public int getIdMountDescription() {return idMountDescription;}
    public void setIdMountDescription(int idMountDescription) {this.idMountDescription = idMountDescription;}

    public int getIdMountRule() {return idMountRule;}
    public void setIdMountRule(int idMountRule) {this.idMountRule = idMountRule;}

    public String getNameOfMountedUnitPhoto() {return nameOfMountedUnitPhoto;}
    public void setNameOfMountedUnitPhoto(String nameOfMountedUnitPhoto) {this.nameOfMountedUnitPhoto = nameOfMountedUnitPhoto;}
}
