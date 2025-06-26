package com.example.restservice.model;

public class BattleReport {
    private int idBattleReport;
    private String nameBattleReport;
    private String descriptionBattleReport;
    private int battleReportPhoto_idBattleReportPhoto;
    private int scenario_idScenario;

    public BattleReport() {}

    public BattleReport(int idBattleReport, String nameBattlereport, String descriptionBattleReport, int battleReportPhoto_idBattleReportPhoto, int scenario_idScenario) {
        this.descriptionBattleReport = descriptionBattleReport;
        this.idBattleReport = idBattleReport;
        this.battleReportPhoto_idBattleReportPhoto = battleReportPhoto_idBattleReportPhoto;
        this.scenario_idScenario = scenario_idScenario;
        this.nameBattleReport = nameBattleReport;
    }

    public int getIdBattleReport() {return idBattleReport;}
    public void setIdBattleReport(int idBattleReport) {this.idBattleReport = idBattleReport;}

    public String getDescriptionBattleReport() {return descriptionBattleReport;}
    public void setDescriptionBattleReport(String descriptionBattleReport) {this.descriptionBattleReport = descriptionBattleReport;}

    public int getBattleReportPhoto_idBattleReportPhoto() {return battleReportPhoto_idBattleReportPhoto;}
    public void setBattleReportPhoto_idBattleReportPhoto(int battleReportPhoto_idBattleReportPhoto) {this.battleReportPhoto_idBattleReportPhoto = battleReportPhoto_idBattleReportPhoto;}

    public int getScenario_idScenario() {return scenario_idScenario;}
    public void setScenario_idScenario(int scenario_idScenario) {this.scenario_idScenario = scenario_idScenario;}

    public String getNameBattleReport() {return nameBattleReport;}
    public void setNameBattleReport(String nameBattleReport) {this.nameBattleReport = nameBattleReport;}
}
