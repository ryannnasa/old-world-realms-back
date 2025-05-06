package com.example.restservice.model;

public class TroopType {
    private int idTroopType;
    private String troopTypeName;
    private String figurinesByRank;
    private String maxRankBonus;
    private String unitPowerPerFigurine;
    private int idMainTroopType;

    public TroopType() {
    }

    public TroopType(int idTroopType, String troopTypeName, String figurinesByRank,
                     String maxRankBonus, String unitPowerPerFigurine, int idMainTroopType) {
        this.idTroopType = idTroopType;
        this.troopTypeName = troopTypeName;
        this.figurinesByRank = figurinesByRank;
        this.maxRankBonus = maxRankBonus;
        this.unitPowerPerFigurine = unitPowerPerFigurine;
        this.idMainTroopType = idMainTroopType;
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

    public int getIdMainTroopType() { return idMainTroopType; }
    public void setIdMainTroopType(int idMainTroopType) { this.idMainTroopType = idMainTroopType; }
}
