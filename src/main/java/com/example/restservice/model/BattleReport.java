package com.example.restservice.model;

import java.util.ArrayList;
import java.util.List;

public class BattleReport {
    private int idBattleReport;
    private String nameBattleReport;
    private String descriptionBattleReport;
    private int battleReportPhoto_idBattleReportPhoto;
    private int scenario_idScenario;
    private List<Player> players = new ArrayList<>();
    private int armyPoints;

    public BattleReport() {}

    public BattleReport(int idBattleReport, String nameBattleReport, String descriptionBattleReport, int battleReportPhoto_idBattleReportPhoto, int scenario_idScenario, List<Player> players, int armyPoints) {
        this.descriptionBattleReport = descriptionBattleReport;
        this.idBattleReport = idBattleReport;
        this.battleReportPhoto_idBattleReportPhoto = battleReportPhoto_idBattleReportPhoto;
        this.scenario_idScenario = scenario_idScenario;
        this.nameBattleReport = nameBattleReport;
        this.players = players;
        this.armyPoints = armyPoints;
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

    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }

    public int getArmyPoints() {return armyPoints;}

    public void setArmyPoints(int armyPoints) {this.armyPoints = armyPoints;}
}
