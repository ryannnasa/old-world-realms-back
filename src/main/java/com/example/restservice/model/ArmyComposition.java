package com.example.restservice.model;

public class ArmyComposition {
    private int idArmyComposition;
    private String nameArmyComposition;

    public ArmyComposition() {
    }

    public ArmyComposition(int idArmyComposition, String nameArmyComposition) {
        this.idArmyComposition = idArmyComposition;
        this.nameArmyComposition = nameArmyComposition;
    }

    public int getIdArmyComposition() {return idArmyComposition;}
    public void setIdArmyComposition(int idArmyComposition) {this.idArmyComposition = idArmyComposition;}

    public String getNameArmyComposition() {return nameArmyComposition;}
    public void setNameArmyComposition(String nameArmyComposition) {this.nameArmyComposition = nameArmyComposition;}
}

