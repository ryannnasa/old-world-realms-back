package com.example.restservice.model;

public class UnitPhoto {
    private int idUnitPhoto;
    private String unitPhotoName;
    private int principalUnitName_idPrincipalUnitName;

    public UnitPhoto() {}

    public UnitPhoto(int idUnitPhoto, String unitPhotoName, int principalUnitName_idPrincipalUnitName) {
        this.principalUnitName_idPrincipalUnitName = principalUnitName_idPrincipalUnitName;
        this.idUnitPhoto = idUnitPhoto;
        this.unitPhotoName = unitPhotoName;
    }

    public int getIdUnitPhoto() {return idUnitPhoto;}
    public void setIdUnitPhoto(int idUnitPhoto) {this.idUnitPhoto = idUnitPhoto;}

    public String getUnitPhotoName() {return unitPhotoName;}
    public void setUnitPhotoName(String unitPhotoName) {this.unitPhotoName = unitPhotoName;}

    public int getPrincipalUnitName_idPrincipalUnitName() {return principalUnitName_idPrincipalUnitName;}
    public void setPrincipalUnitName_idPrincipalUnitName(int principalUnitName_idPrincipalUnitName) {this.principalUnitName_idPrincipalUnitName = principalUnitName_idPrincipalUnitName;}
}