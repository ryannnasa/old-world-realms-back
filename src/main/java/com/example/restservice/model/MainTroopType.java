package com.example.restservice.model;

public class MainTroopType {
    private int idMainTroopType;
    private String mainTroopTypeName;

    public MainTroopType() {
    }

    public MainTroopType(int idMainTroopType, String mainTroopTypeName) {
        this.idMainTroopType = idMainTroopType;
        this.mainTroopTypeName = mainTroopTypeName;
    }

    public int getIdMainTroopType() {return idMainTroopType;}
    public void setIdMainTroopType(int idMainTroopType) {this.idMainTroopType = idMainTroopType;}

    public String getMainTroopTypeName() {return mainTroopTypeName;}
    public void setMainTroopTypeName(String mainTroopTypeName) {this.mainTroopTypeName = mainTroopTypeName;}
}
