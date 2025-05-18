package com.example.restservice.model;

public class Scenario {
    private int idScenario;
    private String scenarioName;

    public Scenario() {}

    public Scenario(int idScenario, String scenarioName) {
        this.idScenario = idScenario;
        this.scenarioName = scenarioName;
   }

    public int getIdScenario() {return idScenario;}
    public void setIdScenario(int idScenario) {this.idScenario = idScenario;}

    public String getScenarioName() {return scenarioName;}
    public void setScenarioName(String scenarioName) {this.scenarioName = scenarioName;}
}