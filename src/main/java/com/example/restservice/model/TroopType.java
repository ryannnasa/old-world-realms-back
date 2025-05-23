package com.example.restservice.model;

public class TroopType {
    private int idTroopType;
    private String troopTypeName;
    private String figurinesByRank;
    private String maxRankBonus;
    private String unitPowerPerFigurine;
    private int mainTroopType_idMainTroopType;

    public TroopType() {
    }

    public TroopType(int idTroopType, String troopTypeName, String figurinesByRank,
                     String maxRankBonus, String unitPowerPerFigurine, int mainTroopType_idMainTroopType) {
        this.idTroopType = idTroopType;
        this.troopTypeName = troopTypeName;
        this.figurinesByRank = figurinesByRank;
        this.maxRankBonus = maxRankBonus;
        this.unitPowerPerFigurine = unitPowerPerFigurine;
        this.mainTroopType_idMainTroopType = mainTroopType_idMainTroopType;
    }

    public int getIdTroopType() { return idTroopType; }
    public void setIdTroopType(int idTroopType) { this.idTroopType = idTroopType; }

    public String getTroopTypeName() { return troopTypeName; }
    public void setTroopTypeName(String troopTypeName) { this.troopTypeName = troopTypeName; }

    public String getFigurinesByRank() { return figurinesByRank; }
    public void setFigurinesByRank(String figurinesByRank) { this.figurinesByRank = figurinesByRank; }

    public String getMaxRankBonus() { return maxRankBonus; }
    public void setMaxRankBonus(String maxRankBonus) { this.maxRankBonus = maxRankBonus; }

    public String getUnitPowerPerFigurine() { return unitPowerPerFigurine; }
    public void setUnitPowerPerFigurine(String unitPowerPerFigurine) { this.unitPowerPerFigurine = unitPowerPerFigurine; }

    public int getMainTroopType_idMainTroopType() { return mainTroopType_idMainTroopType; }
    public void setMainTroopType_idMainTroopType(int mainTroopType_idMainTroopType) { this.mainTroopType_idMainTroopType = mainTroopType_idMainTroopType; }
}
