public class Player {
    private int idPlayer;
    private String playerName;
    private String playerScore;
    private int idAlliance;
    private int idArmyName;
    private int idArmyComposition;

    public Player() {}

    public Player(int idPlayer, String playerName, String playerScore, int idAlliance, int idArmyName, int idArmyComposition) {
        this.idPlayer = idPlayer;
        this.playerName = playerName;
        this.playerScore = playerScore;
        this.idAlliance = idAlliance;
        this.idArmyName = idArmyName;
        this.idArmyComposition = idArmyComposition;
    }

    public int getIdPlayer() {return idPlayer;}
    public void setIdPlayer(int idPlayer) {this.idPlayer = idPlayer;}

    public String getPlayerName() {return playerName;}
    public void setPlayerName(String playerName) {this.playerName = playerName;}

    public int getIdAlliance() {return idAlliance;}
    public void setIdAlliance(int idAlliance) {this.idAlliance = idAlliance;}

    public String getPlayerScore() {return playerScore;}
    public void setPlayerScore(String playerScore) {this.playerScore = playerScore;}

    public int getIdArmyName() {return idArmyName;}
    public void setIdArmyName(int idArmyName) {this.idArmyName = idArmyName;}

    public int getIdArmyComposition() {return idArmyComposition;}
    public void setIdArmyComposition(int idArmyComposition) {this.idArmyComposition = idArmyComposition;}
}