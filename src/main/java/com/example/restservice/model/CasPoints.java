package com.example.restservice.model;

public class CasPoints {
    private int idCasPoints;
    private String typeCasPoints;

    public CasPoints() {}

    public CasPoints(int idCasPoints, String typeCasPoints) {
        this.idCasPoints = idCasPoints;
        this.typeCasPoints = typeCasPoints;
    }

    public int getIdCasPoints() {return idCasPoints;}
    public void setIdCasPoints(int idCasPoints) {this.idCasPoints = idCasPoints;}

    public String getTypeCasPoints() {return typeCasPoints;}
    public void setTypeCasPoints(String typeCasPoints) {this.typeCasPoints = typeCasPoints;}
}



