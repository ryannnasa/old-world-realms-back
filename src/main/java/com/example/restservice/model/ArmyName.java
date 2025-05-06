package com.example.restservice.model;

public class ArmyName {
    private int idArmyName;
    private String armyName;

    public ArmyName() {
    }

    public ArmyName(int idArmyName, String armyName) {
        this.idArmyName = idArmyName;
        this.armyName = armyName;
    }

    public int getIdArmyName() {return idArmyName;}
    public void setIdArmyName(int idArmyName) {this.idArmyName = idArmyName;}

    public String getArmyName() {return armyName;}
    public void setArmyName(String armyName) {this.armyName = armyName;}
}
