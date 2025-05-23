package com.example.restservice.model;

public class ArmyName {
    private int idArmyName;
    private String nameArmyName;

    public ArmyName() {
    }

    public ArmyName(int idArmyName, String armyName) {
        this.idArmyName = idArmyName;
        this.nameArmyName = armyName;
    }

    public int getIdArmyName() {return idArmyName;}
    public void setIdArmyName(int idArmyName) {this.idArmyName = idArmyName;}

    public String getNameArmyName() {return nameArmyName;}
    public void setNameArmyName(String nameArmyName) {this.nameArmyName = nameArmyName;}
}
