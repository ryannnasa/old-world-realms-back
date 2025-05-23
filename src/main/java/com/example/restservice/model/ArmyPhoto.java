package com.example.restservice.model;

public class ArmyPhoto {
    private int idArmyPhoto;
    private int armyName_idArmyName;
    private String photoArmyName;


public ArmyPhoto () {}

public ArmyPhoto(int idArmyPhoto, int armyName_idArmyName, String photoArmyName) {
    this.idArmyPhoto = idArmyPhoto;
    this.armyName_idArmyName = armyName_idArmyName;
    this.photoArmyName = photoArmyName;
}

    public int getIdArmyPhoto() {return idArmyPhoto;}
    public void setIdArmyPhoto(int idArmyPhoto) {this.idArmyPhoto = idArmyPhoto;}

    public int getArmyName_idArmyName() {return armyName_idArmyName;}
    public void setArmyName_idArmyName(int armyName_idArmyName) {this.armyName_idArmyName = armyName_idArmyName;}

    public String getPhotoArmyName() {return photoArmyName;}
    public void setPhotoArmyName(String photoArmyName) {this.photoArmyName = photoArmyName;}

}