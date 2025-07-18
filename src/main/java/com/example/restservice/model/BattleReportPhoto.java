package com.example.restservice.model;

import java.io.File;

public class BattleReportPhoto {
    private int idBattleReportPhoto;
    private String nameBattleReportPhoto;
    private int battleReport_idBattleReport;
    private byte[] fileBattleReportPhoto;

    public BattleReportPhoto() {}

    public BattleReportPhoto(int idBattleReportPhoto, String nameBattleReportPhoto, int battleReport_idBattleReport, byte[] fileBattleReportPhoto) {
        this.idBattleReportPhoto = idBattleReportPhoto;
        this.nameBattleReportPhoto = nameBattleReportPhoto;
        this.battleReport_idBattleReport = battleReport_idBattleReport;
        this.fileBattleReportPhoto = fileBattleReportPhoto;
    }

    public int getIdBattleReportPhoto() {return idBattleReportPhoto;}
    public void setIdBattleReportPhoto(int idBattleReportPhoto) {this.idBattleReportPhoto = idBattleReportPhoto;}

    public String getNameBattleReportPhoto() {return nameBattleReportPhoto;}
    public void setNameBattleReportPhoto(String nameBattleReportPhoto) {this.nameBattleReportPhoto = nameBattleReportPhoto;}

    public int getBattleReport_idBattleReport() {return battleReport_idBattleReport;}
    public void setBattleReport_idBattleReport(int battleReport_idBattleReport) {this.battleReport_idBattleReport = battleReport_idBattleReport;}

    public byte[] getFileBattleReportPhoto() {return fileBattleReportPhoto;}
    public void setFileBattleReportPhoto(byte[] fileBattleReportPhoto) {this.fileBattleReportPhoto = fileBattleReportPhoto;}
}
