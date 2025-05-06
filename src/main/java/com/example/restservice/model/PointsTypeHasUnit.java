package com.example.restservice.model;

public class PointsTypeHasUnit {
    private int idUnit;
    private int idPointsType;
    private int pointsNumber;
    private int idPointsCases;

    public PointsTypeHasUnit() {
    }

    public PointsTypeHasUnit(int idUnit, int idPointsType, int pointsNumber, int idPointsCases) {
        this.idUnit = idUnit;
        this.idPointsType = idPointsType;
        this.pointsNumber = pointsNumber;
        this.idPointsCases = idPointsCases;
    }

    public int getIdUnit() { return idUnit; }
    public void setIdUnit(int idUnit) { this.idUnit = idUnit; }

    public int getIdPointsType() { return idPointsType; }
    public void setIdPointsType(int idPointsType) { this.idPointsType = idPointsType; }

    public int getPointsNumber() { return pointsNumber; }
    public void setPointsNumber(int pointsNumber) { this.pointsNumber = pointsNumber; }

    public int getIdPointsCases() { return idPointsCases; }
    public void setIdPointsCases(int idPointsCases) { this.idPointsCases = idPointsCases; }
}
