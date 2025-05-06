package com.example.restservice.model;

public class MountDescription {
    private int idMountDescription;
    private String mountDescription;

    public MountDescription() {
    }

    public MountDescription(int idMountDescription, String MountDescription) {
        this.idMountDescription = idMountDescription;
        this.mountDescription = MountDescription;
    }

    public int getIdMountDescription() {return idMountDescription;}
    public void setIdMountDescription(int idMountDescription) {this.idMountDescription = idMountDescription;}

    public String getMountDescription() {return mountDescription;}
    public void setMountDescription(String mountDescription) {this.mountDescription = mountDescription;}
}
