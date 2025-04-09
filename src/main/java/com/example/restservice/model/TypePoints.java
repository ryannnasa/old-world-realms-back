package com.example.restservice.model;

public class TypePoints {
    private int idTypePoints;
    private String typePoints;
    private String diminutifTypePoints;

    public TypePoints() {
    }

    public TypePoints(int idTypePoints, String typePoints, String diminutifTypePoints) {
        this.idTypePoints = idTypePoints;
        this.typePoints = typePoints;
        this.diminutifTypePoints = diminutifTypePoints;
    }

    public int getIdTypePoints() {return idTypePoints;}
    public void setIdTypePoints(int idTypePoints) {this.idTypePoints = idTypePoints;}

    public String getTypePoints() {return typePoints;}
    public void setTypePoints(String typePoints) {this.typePoints = typePoints;}

    public String getDiminutifTypePoints() {return diminutifTypePoints;}
    public void setDiminutifTypePoints(String diminutifTypePoints) {this.diminutifTypePoints = diminutifTypePoints;}
}
