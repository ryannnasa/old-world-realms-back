package com.example.restservice.model;

public class UnitPhoto {
    private int idUnitPhoto;
    private String unitPhotoName;
    private int idPrincipalUnitName;

    public UnitPhoto() {}

    public UnitPhoto(int idUnitPhoto, String unitPhotoName, int idPrincipalUnitName) {
        this.idPrincipalUnitName = idPrincipalUnitName;
        this.idUnitPhoto = idUnitPhoto;
        this.unitPhotoName = unitPhotoName;
    }

    public int getIdUnitPhoto() {return idUnitPhoto;}
    public void setIdUnitPhoto(int idUnitPhoto) {this.idUnitPhoto = idUnitPhoto;}

    public String getUnitPhotoName() {return unitPhotoName;}
    public void setUnitPhotoName(String unitPhotoName) {this.unitPhotoName = unitPhotoName;}

    public int getIdPrincipalUnitName() {return idPrincipalUnitName;}
    public void setIdPrincipalUnitName(int idPrincipalUnitName) {this.idPrincipalUnitName = idPrincipalUnitName;}
}