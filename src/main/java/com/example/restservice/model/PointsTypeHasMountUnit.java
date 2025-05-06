package com.example.restservice.model;

public class PointsTypeHasMountUnit {
    private int idMount;
    private int idMountedUnit;
    private int idUnit;
    private int idMountRule;
    private int idMountDescription;
    private int idPointsType;
    private int pointsNumber;
    private int idPointsCases;

    public PointsTypeHasMountUnit() {
    }

    public PointsTypeHasMountUnit(int idMount, int idMountedUnit, int idUnit, int idMountRule,
                                  int idMountDescription, int idPointsType, int pointsNumber, int idPointsCases) {
        this.idMount = idMount;
        this.idMountedUnit = idMountedUnit;
        this.idUnit = idUnit;
        this.idMountRule = idMountRule;
        this.idMountDescription = idMountDescription;
        this.idPointsType = idPointsType;
        this.pointsNumber = pointsNumber;
        this.idPointsCases = idPointsCases;
    }

    public int getIdMount() { return idMount; }
    public void setIdMount(int idMount) { this.idMount = idMount; }

    public int getIdMountedUnit() { return idMountedUnit; }
    public void setIdMountedUnit(int idMountedUnit) { this.idMountedUnit = idMountedUnit; }

    public int getIdUnit() { return idUnit; }
    public void setIdUnit(int idUnit) { this.idUnit = idUnit; }

    public int getIdMountRule() { return idMountRule; }
    public void setIdMountRule(int idMountRule) { this.idMountRule = idMountRule; }

    public int getIdMountDescription() { return idMountDescription; }
    public void setIdMountDescription(int idMountDescription) { this.idMountDescription = idMountDescription; }

    public int getIdPointsType() { return idPointsType; }
    public void setIdPointsType(int idPointsType) { this.idPointsType = idPointsType; }

    public int getPointsNumber() { return pointsNumber; }
    public void setPointsNumber(int pointsNumber) { this.pointsNumber = pointsNumber; }

    public int getIdPointsCases() { return idPointsCases; }
    public void setIdPointsCases(int idPointsCases) { this.idPointsCases = idPointsCases; }
}
