package com.example.restservice.model;

public class Alliance {
    private int idAlliance;
    private String allianceName;

    public Alliance() {}

    public Alliance(int idAlliance, String allianceName) {
        this.allianceName = allianceName;
        this.idAlliance = idAlliance;
    }

    public int getIdAlliance() {return idAlliance;}
    public void setIdAlliance(int idAlliance) {this.idAlliance = idAlliance;}

    public String getAllianceName() {return allianceName;}
    public void setAllianceName(String allianceName) {this.allianceName = allianceName;}
}