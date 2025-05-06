package com.example.restservice.model;

public class MountRule {
    private int idMountRule;
    private String mountRule;

    public MountRule() {
    }

    public MountRule(int idMountRule, String mountRule) {
        this.idMountRule = idMountRule;
        this.mountRule = mountRule;
    }

    public int getIdMountRule() {return idMountRule;}
    public void setIdMountRule(int idMountRule) {this.idMountRule = idMountRule;}

    public String getMountRule() {return mountRule;}
    public void setMountRule(String mountRule) {this.mountRule = mountRule;}
}
