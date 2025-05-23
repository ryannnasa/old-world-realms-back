package com.example.restservice.model;

public class PointsTypeHasMountUnit {
    private int mountUnit_mount_idMount;
    private int mountUnit_unit_idMountedUnit;
    private int mountUnit_unit_idUnit;
    private int mountUnit_mountRule_idMountRule;
    private int mountUnit_mountDescription_idMountDescription;
    private int pointsType_idPointsType;
    private int pointsNumber;
    private int pointsCases_idPointsCases;

    public PointsTypeHasMountUnit() {
    }

    public PointsTypeHasMountUnit(int mountUnit_mount_idMount, int mountUnit_unit_idMountedUnit, int mountUnit_unit_idUnit, int mountUnit_mountRule_idMountRule,
                                  int mountUnit_mountDescription_idMountDescription, int pointsType_idPointsType, int pointsNumber, int pointsCases_idPointsCases) {
        this.mountUnit_mount_idMount = mountUnit_mount_idMount;
        this.mountUnit_unit_idMountedUnit = mountUnit_unit_idMountedUnit;
        this.mountUnit_unit_idUnit = mountUnit_unit_idUnit;
        this.mountUnit_mountRule_idMountRule = mountUnit_mountRule_idMountRule;
        this.mountUnit_mountDescription_idMountDescription = mountUnit_mountDescription_idMountDescription;
        this.pointsType_idPointsType = pointsType_idPointsType;
        this.pointsNumber = pointsNumber;
        this.pointsCases_idPointsCases = pointsCases_idPointsCases;
    }

    public int getMountUnit_mount_idMount() { return mountUnit_mount_idMount; }
    public void setMountUnit_mount_idMount(int mountUnit_mount_idMount) { this.mountUnit_mount_idMount = mountUnit_mount_idMount; }

    public int getMountUnit_unit_idMountedUnit() { return mountUnit_unit_idMountedUnit; }
    public void setMountUnit_unit_idMountedUnit(int mountUnit_unit_idMountedUnit) { this.mountUnit_unit_idMountedUnit = mountUnit_unit_idMountedUnit; }

    public int getMountUnit_unit_idUnit() { return mountUnit_unit_idUnit; }
    public void setMountUnit_unit_idUnit(int mountUnit_unit_idUnit) { this.mountUnit_unit_idUnit = mountUnit_unit_idUnit; }

    public int getMountUnit_mountRule_idMountRule() { return mountUnit_mountRule_idMountRule; }
    public void setMountUnit_mountRule_idMountRule(int mountUnit_mountRule_idMountRule) { this.mountUnit_mountRule_idMountRule = mountUnit_mountRule_idMountRule; }

    public int getMountUnit_mountDescription_idMountDescription() { return mountUnit_mountDescription_idMountDescription; }
    public void setMountUnit_mountDescription_idMountDescription(int mountUnit_mountDescription_idMountDescription) { this.mountUnit_mountDescription_idMountDescription = mountUnit_mountDescription_idMountDescription; }

    public int getPointsType_idPointsType() { return pointsType_idPointsType; }
    public void setPointsType_idPointsType(int pointsType_idPointsType) { this.pointsType_idPointsType = pointsType_idPointsType; }

    public int getPointsNumber() { return pointsNumber; }
    public void setPointsNumber(int pointsNumber) { this.pointsNumber = pointsNumber; }

    public int getPointsCases_idPointsCases() { return pointsCases_idPointsCases; }
    public void setPointsCases_idPointsCases(int pointsCases_idPointsCases) { this.pointsCases_idPointsCases = pointsCases_idPointsCases; }
}
