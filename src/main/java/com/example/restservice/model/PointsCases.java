package com.example.restservice.model;

public class PointsCases {
    private int idPointsCases;
    private String pointsCasesType;

    public PointsCases() {}

    public PointsCases(int idPointsCases, String pointsCasesType) {
        this.idPointsCases = idPointsCases;
        this.pointsCasesType = pointsCasesType;
    }

    public int getIdPointsCases() {return idPointsCases;}
    public void setIdPointsCases(int idPointsCases) {this.idPointsCases = idPointsCases;}

    public String getPointsCasesType() {return pointsCasesType;}
    public void setPointsCasesType(String pointsCasesType) {this.pointsCasesType = pointsCasesType;}
}



