package com.example.restservice.model;

public class CompositionArmee {
    private int idCompositionArmee;
    private String nomCompositionArmee;

    public CompositionArmee() {
    }

    public CompositionArmee(int idCompositionArmee, String nomCompositionArmee) {
        this.idCompositionArmee = idCompositionArmee;
        this.nomCompositionArmee = nomCompositionArmee;
    }

    public int getIdCompositionArmee() {return idCompositionArmee;}
    public void setIdCompositionArmee(int idCompositionArmee) {this.idCompositionArmee = idCompositionArmee;}

    public String getNomCompositionArmee() {return nomCompositionArmee;}
    public void setNomCompositionArmee(String nomCompositionArmee) {this.nomCompositionArmee = nomCompositionArmee;}
}

