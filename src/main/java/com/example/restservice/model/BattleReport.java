package com.example.restservice.model;

public class BattleReport {
    private int idBattleReport;
    private String nameBattlereport;
    private String descriptionBattleReport;
    private int idPlayer;
    private int idAlliance;
    private int idArmyName;
    private int idArmyComposition;
    private int idBattleReportPhoto;
    private int idScenario;

    public BattleReport() {}

    public BattleReport(int idBattleReport, String nameBattlereport, String descriptionBattleReport, int idPlayer, int idAlliance, int idArmyName, int idArmyComposition, int idBattleReportPhoto, int idScenario) {
        this.descriptionBattleReport = descriptionBattleReport;
        this.idAlliance = idAlliance;
        this.idBattleReport = idBattleReport;
        this.idBattleReportPhoto = idBattleReportPhoto;
        this.idArmyName = idArmyName;
        this.idArmyComposition = idArmyComposition;
        this.idPlayer = idPlayer;
        this.idScenario = idScenario;
        this.nameBattlereport = nameBattlereport;
    }

    public int getIdAlliance() {return idAlliance;}
    public void setIdAlliance(int idAlliance) {this.idAlliance = idAlliance;}

    public int getIdArmyName() {return idArmyName;}
    public void setIdArmyName(int idArmyName) {this.idArmyName = idArmyName;}

    public int getIdBattleReport() {return idBattleReport;}
    public void setIdBattleReport(int idBattleReport) {this.idBattleReport = idBattleReport;}

    public String getDescriptionBattleReport() {return descriptionBattleReport;}
    public void setDescriptionBattleReport(String descriptionBattleReport) {this.descriptionBattleReport = descriptionBattleReport;}

    public int getIdBattleReportPhoto() {return idBattleReportPhoto;}
    public void setIdBattleReportPhoto(int idBattleReportPhoto) {this.idBattleReportPhoto = idBattleReportPhoto;}

    public int getIdArmyComposition() {return idArmyComposition;}
    public void setIdArmyComposition(int idArmyComposition) {this.idArmyComposition = idArmyComposition;}

    public int getIdPlayer() {return idPlayer;}
    public void setIdPlayer(int idPlayer) {this.idPlayer = idPlayer;}

    public int getIdScenario() {return idScenario;}
    public void setIdScenario(int idScenario) {this.idScenario = idScenario;}

    public String getNameBattlereport() {return nameBattlereport;}
    public void setNameBattlereport(String nameBattlereport) {this.nameBattlereport = nameBattlereport;}
}
