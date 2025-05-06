package com.example.restservice.model;

public class BattleReportPhoto {
    private int idBattleReportPhoto;
    private String nameBattleReportPhoto;

    public BattleReportPhoto() {}

    public BattleReportPhoto(int idBattleReportPhoto, String nameBattleReportPhoto) {
        this.idBattleReportPhoto = idBattleReportPhoto;
        this.nameBattleReportPhoto = nameBattleReportPhoto;
    }

    public int getIdBattleReportPhoto() {return idBattleReportPhoto;}
    public void setIdBattleReportPhoto(int idBattleReportPhoto) {this.idBattleReportPhoto = idBattleReportPhoto;}

    public String getNameBattleReportPhoto() {return nameBattleReportPhoto;}
    public void setNameBattleReportPhoto(String nameBattleReportPhoto) {this.nameBattleReportPhoto = nameBattleReportPhoto;}
}
