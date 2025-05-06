package com.example.restservice.model;

public class ArmyPhoto {
    private int idArmyPhoto;
    private int idArmyName;
    private String photoArmyName;


public ArmyPhoto () {}

public ArmyPhoto(int idArmyPhoto, int idArmyName, String photoArmyName) {
    this.idArmyPhoto = idArmyPhoto;
    this.idArmyName = idArmyName;
    this.photoArmyName = photoArmyName;
}

    public int getIdArmyPhoto() {return idArmyPhoto;}
    public void setIdArmyPhoto(int idArmyPhoto) {this.idArmyPhoto = idArmyPhoto;}

    public int getIdArmyName() {return idArmyName;}
    public void setIdArmyName(int idArmyName) {this.idArmyName = idArmyName;}

    public String getPhotoArmyName() {return photoArmyName;}
    public void setPhotoArmyName(String photoArmyName) {this.photoArmyName = photoArmyName;}

}