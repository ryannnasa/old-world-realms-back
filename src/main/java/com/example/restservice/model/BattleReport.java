package com.example.restservice.model;

import java.util.ArrayList;
import java.util.List;

public class BattleReport {
    private int idBattleReport;
    private String nameBattleReport;
    private String descriptionBattleReport;
    private List<BattleReportPhoto> battleReportPhotos;
    private int scenario_idScenario;
    private List<Player> players = new ArrayList<>();
    private int armyPoints;
    private String idUser;

    public BattleReport() {}

    public BattleReport(int idBattleReport, String nameBattleReport, String descriptionBattleReport, List<BattleReportPhoto> battleReportPhoto, int scenario_idScenario, List<Player> players, int armyPoints, String idUser) {
        this.descriptionBattleReport = descriptionBattleReport;
        this.idBattleReport = idBattleReport;
        this.battleReportPhotos = battleReportPhotos;
        this.scenario_idScenario = scenario_idScenario;
        this.nameBattleReport = nameBattleReport;
        this.players = players;
        this.armyPoints = armyPoints;
        this.idUser = idUser;
    }

    public int getIdBattleReport() {return idBattleReport;}
    public void setIdBattleReport(int idBattleReport) {this.idBattleReport = idBattleReport;}

    public String getDescriptionBattleReport() {return descriptionBattleReport;}
    public void setDescriptionBattleReport(String descriptionBattleReport) {this.descriptionBattleReport = descriptionBattleReport;}

    public List<BattleReportPhoto> getBattleReportPhotos() {return battleReportPhotos;}

    public void setBattleReportPhotos(List<BattleReportPhoto> battleReportPhotos) {this.battleReportPhotos = battleReportPhotos;}

    public int getScenario_idScenario() {return scenario_idScenario;}
    public void setScenario_idScenario(int scenario_idScenario) {this.scenario_idScenario = scenario_idScenario;}

    public String getNameBattleReport() {return nameBattleReport;}
    public void setNameBattleReport(String nameBattleReport) {this.nameBattleReport = nameBattleReport;}

    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }

    public int getArmyPoints() {return armyPoints;}
    public void setArmyPoints(int armyPoints) {this.armyPoints = armyPoints;}

    public String getIdUser() {return idUser;}
    public void setIdUser(String idUser) {this.idUser = idUser;}
}
