package com.example.restservice.model;

public class Mount {
    private int idMount;
    private String mountName;

    public Mount() {
    }

    public Mount(int idMount, String mountName) {
        this.idMount = idMount;
        this.mountName = mountName;
    }

    public int getIdMount() {return idMount;}
    public void setIdMount(int idMount) {this.idMount = idMount;}

    public String getMountName() {return mountName;}
    public void setMountName(String mountName) {this.mountName = mountName;}
}
