package com.example.restservice.model;

public class PointsType {
    private int idPointsType;
    private String pointsType;
    private String diminutiveOfPointsType;

    public PointsType() {
    }

    public PointsType(int idPointsType, String pointsType, String diminutiveOfPointsType) {
        this.idPointsType = idPointsType;
        this.pointsType = pointsType;
        this.diminutiveOfPointsType = diminutiveOfPointsType;
    }

    public int getIdPointsType() {return idPointsType;}
    public void setIdPointsType(int idPointsType) {this.idPointsType = idPointsType;}

    public String getPointsType() {return pointsType;}
    public void setPointsType(String pointsType) {this.pointsType = pointsType;}

    public String getDiminutiveOfPointsType() {return diminutiveOfPointsType;}
    public void setDiminutiveOfPointsType(String diminutiveOfPointsType) {this.diminutiveOfPointsType = diminutiveOfPointsType;}
}
