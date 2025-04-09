package com.example.restservice.model;

public class TypeTroupe {
    private int idTypeTroupe;
    private String nomTypeTroupe;
    private String figurinesParRang;
    private String bonusDeRangMaximum;
    private String puissanceUniteParFigurine;
    private int idTypeTroupePrincipal;

    public TypeTroupe() {
    }

    public TypeTroupe(int idTypeTroupe, String nomTypeTroupe, String figurinesParRang,
                      String bonusDeRangMaximum, String puissanceUniteParFigurine, int idTypeTroupePrincipal) {
        this.idTypeTroupe = idTypeTroupe;
        this.nomTypeTroupe = nomTypeTroupe;
        this.figurinesParRang = figurinesParRang;
        this.bonusDeRangMaximum = bonusDeRangMaximum;
        this.puissanceUniteParFigurine = puissanceUniteParFigurine;
        this.idTypeTroupePrincipal = idTypeTroupePrincipal;
    }

    public int getIdTypeTroupe() { return idTypeTroupe; }
    public void setIdTypeTroupe(int idTypeTroupe) { this.idTypeTroupe = idTypeTroupe; }

    public String getNomTypeTroupe() { return nomTypeTroupe; }
    public void setNomTypeTroupe(String nomTypeTroupe) { this.nomTypeTroupe = nomTypeTroupe; }

    public String getFigurinesParRang() { return figurinesParRang; }
    public void setFigurinesParRang(String figurinesParRang) { this.figurinesParRang = figurinesParRang; }

    public String getBonusDeRangMaximum() { return bonusDeRangMaximum; }
    public void setBonusDeRangMaximum(String bonusDeRangMaximum) { this.bonusDeRangMaximum = bonusDeRangMaximum; }

    public String getPuissanceUniteParFigurine() { return puissanceUniteParFigurine; }
    public void setPuissanceUniteParFigurine(String puissanceUniteParFigurine) { this.puissanceUniteParFigurine = puissanceUniteParFigurine; }

    public int getIdTypeTroupePrincipal() { return idTypeTroupePrincipal; }
    public void setIdTypeTroupePrincipal(int idTypeTroupePrincipal) { this.idTypeTroupePrincipal = idTypeTroupePrincipal; }
}
