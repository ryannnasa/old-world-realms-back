package com.example.restservice.model;

public class PointsTypeHasUnit {
    private int unit_idUnit;
    private int pointsType_idPointsType;
    private int pointsNumber;
    private int pointsCases_idPointsCases;

    public PointsTypeHasUnit() {
    }

    public PointsTypeHasUnit(int unit_idUnit, int pointsType_idPointsType, int pointsNumber, int pointsCases_idPointsCases) {
        this.unit_idUnit = unit_idUnit;
        this.pointsType_idPointsType = pointsType_idPointsType;
        this.pointsNumber = pointsNumber;
        this.pointsCases_idPointsCases = pointsCases_idPointsCases;
    }

    public int getUnit_idUnit() { return unit_idUnit; }
    public void setUnit_idUnit(int unit_idUnit) { this.unit_idUnit = unit_idUnit; }

    public int getPointsType_idPointsType() { return pointsType_idPointsType; }
    public void setPointsType_idPointsType(int pointsType_idPointsType) { this.pointsType_idPointsType = pointsType_idPointsType; }

    public int getPointsNumber() { return pointsNumber; }
    public void setPointsNumber(int pointsNumber) { this.pointsNumber = pointsNumber; }

    public int getPointsCases_idPointsCases() { return pointsCases_idPointsCases; }
    public void setPointsCases_idPointsCases(int pointsCases_idPointsCases) { this.pointsCases_idPointsCases = pointsCases_idPointsCases; }
}
