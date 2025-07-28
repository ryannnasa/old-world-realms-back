package com.example.restservice.model;

import java.io.File;

public class BattleReportPhoto {
    private int idBattleReportPhoto;
    private String nameBattleReportPhoto;
    private int battleReport_idBattleReport;

    public BattleReportPhoto() {}

    public BattleReportPhoto(int idBattleReportPhoto, String nameBattleReportPhoto, int battleReport_idBattleReport) {
        this.idBattleReportPhoto = idBattleReportPhoto;
        this.nameBattleReportPhoto = nameBattleReportPhoto;
        this.battleReport_idBattleReport = battleReport_idBattleReport;
    }

    public int getIdBattleReportPhoto() {return idBattleReportPhoto;}
    public void setIdBattleReportPhoto(int idBattleReportPhoto) {this.idBattleReportPhoto = idBattleReportPhoto;}

    public String getNameBattleReportPhoto() {return nameBattleReportPhoto;}
    public void setNameBattleReportPhoto(String nameBattleReportPhoto) {this.nameBattleReportPhoto = nameBattleReportPhoto;}

    public int getBattleReport_idBattleReport() {return battleReport_idBattleReport;}
    public void setBattleReport_idBattleReport(int battleReport_idBattleReport) {this.battleReport_idBattleReport = battleReport_idBattleReport;}

}
