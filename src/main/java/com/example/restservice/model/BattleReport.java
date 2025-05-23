package com.example.restservice.model;

public class BattleReport {
    private int idBattleReport;
    private String nameBattlereport;
    private String descriptionBattleReport;
    private int player_idPlayer;
    private int player_alliance_idAlliance;
    private int player_armyName_idArmyName;
    private int player_armyComposition_idArmyComposition;
    private int battleReportPhoto_idBattleReportPhoto;
    private int scenario_idScenario;

    public BattleReport() {}

    public BattleReport(int idBattleReport, String nameBattlereport, String descriptionBattleReport, int player_idPlayer, int player_alliance_idAlliance, int player_armyName_idArmyName, int player_armyComposition_idArmyComposition, int battleReportPhoto_idBattleReportPhoto, int scenario_idScenario) {
        this.descriptionBattleReport = descriptionBattleReport;
        this.player_alliance_idAlliance = player_alliance_idAlliance;
        this.idBattleReport = idBattleReport;
        this.battleReportPhoto_idBattleReportPhoto = battleReportPhoto_idBattleReportPhoto;
        this.player_armyName_idArmyName = player_armyName_idArmyName;
        this.player_armyComposition_idArmyComposition = player_armyComposition_idArmyComposition;
        this.player_idPlayer = player_idPlayer;
        this.scenario_idScenario = scenario_idScenario;
        this.nameBattlereport = nameBattlereport;
    }

    public int getPlayer_alliance_idAlliance() {return player_alliance_idAlliance;}
    public void setPlayer_alliance_idAlliance(int player_alliance_idAlliance) {this.player_alliance_idAlliance = player_alliance_idAlliance;}

    public int getPlayer_armyName_idArmyName() {return player_armyName_idArmyName;}
    public void setPlayer_armyName_idArmyName(int player_armyName_idArmyName) {this.player_armyName_idArmyName = player_armyName_idArmyName;}

    public int getIdBattleReport() {return idBattleReport;}
    public void setIdBattleReport(int idBattleReport) {this.idBattleReport = idBattleReport;}

    public String getDescriptionBattleReport() {return descriptionBattleReport;}
    public void setDescriptionBattleReport(String descriptionBattleReport) {this.descriptionBattleReport = descriptionBattleReport;}

    public int getBattleReportPhoto_idBattleReportPhoto() {return battleReportPhoto_idBattleReportPhoto;}
    public void setBattleReportPhoto_idBattleReportPhoto(int battleReportPhoto_idBattleReportPhoto) {this.battleReportPhoto_idBattleReportPhoto = battleReportPhoto_idBattleReportPhoto;}

    public int getPlayer_armyComposition_idArmyComposition() {return player_armyComposition_idArmyComposition;}
    public void setPlayer_armyComposition_idArmyComposition(int player_armyComposition_idArmyComposition) {this.player_armyComposition_idArmyComposition = player_armyComposition_idArmyComposition;}

    public int getPlayer_idPlayer() {return player_idPlayer;}
    public void setPlayer_idPlayer(int player_idPlayer) {this.player_idPlayer = player_idPlayer;}

    public int getScenario_idScenario() {return scenario_idScenario;}
    public void setScenario_idScenario(int scenario_idScenario) {this.scenario_idScenario = scenario_idScenario;}

    public String getNameBattlereport() {return nameBattlereport;}
    public void setNameBattlereport(String nameBattlereport) {this.nameBattlereport = nameBattlereport;}
}
