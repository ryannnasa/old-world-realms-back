package com.example.restservice.model;

public class NomArmee {
    private int idNomArmee;
    private String nomArmee;

    public NomArmee() {
    }

    public NomArmee(int idNomArmee, String nomArmee) {
        this.idNomArmee = idNomArmee;
        this.nomArmee = nomArmee;
    }

    public int getIdNomArmee() {return idNomArmee;}
    public void setIdNomArmee(int idNomArmee) {this.idNomArmee = idNomArmee;}

    public String getNomArmee() {return nomArmee;}
    public void setNomArmee(String nomArmee) {this.nomArmee = nomArmee;}
}
