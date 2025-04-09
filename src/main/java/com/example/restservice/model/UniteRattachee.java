package com.example.restservice.model;

public class UniteRattachee {
    private int idUnite;
    private int idUnite1;

    public UniteRattachee() {
    }

    public UniteRattachee(int idUnite, int idUnite1) {
        this.idUnite = idUnite;
        this.idUnite1 = idUnite1;
    }

    public int getIdUnite() { return idUnite; }
    public void setIdUnite(int idUnite) { this.idUnite = idUnite; }

    public int getIdUnite1() { return idUnite1; }
    public void setIdUnite1(int idUnite1) { this.idUnite1 = idUnite1; }
}

