package com.example.restservice.model;

public class Player {
    private int idPlayer;
    private String playerName;
    private String playerScore;
    private int alliance_idAlliance;
    private int armyName_idArmyName;
    private int armyComposition_idArmyComposition;

    public Player() {}

    public Player(int idPlayer, String playerName, String playerScore, int alliance_idAlliance, int armyName_idArmyName, int armyComposition_idArmyComposition) {
        this.idPlayer = idPlayer;
        this.playerName = playerName;
        this.playerScore = playerScore;
        this.alliance_idAlliance = alliance_idAlliance;
        this.armyName_idArmyName = armyName_idArmyName;
        this.armyComposition_idArmyComposition = armyComposition_idArmyComposition;
    }

    public int getIdPlayer() {return idPlayer;}
    public void setIdPlayer(int idPlayer) {this.idPlayer = idPlayer;}

    public String getPlayerName() {return playerName;}
    public void setPlayerName(String playerName) {this.playerName = playerName;}

    public int getAlliance_idAlliance() {return alliance_idAlliance;}
    public void setAlliance_idAlliance(int alliance_idAlliance) {this.alliance_idAlliance = alliance_idAlliance;}

    public String getPlayerScore() {return playerScore;}
    public void setPlayerScore(String playerScore) {this.playerScore = playerScore;}

    public int getArmyName_idArmyName() {return armyName_idArmyName;}
    public void setArmyName_idArmyName(int armyName_idArmyName) {this.armyName_idArmyName = armyName_idArmyName;}

    public int getArmyComposition_idArmyComposition() {return armyComposition_idArmyComposition;}
    public void setArmyComposition_idArmyComposition(int armyComposition_idArmyComposition) {this.armyComposition_idArmyComposition = armyComposition_idArmyComposition;}
}