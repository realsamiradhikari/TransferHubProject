package com.TransferHubProject.model;

public class PlayerModel {
    private int playerId;
    private String playerName;
    private String position;
    private String nationality;
    private int age;
    private double marketValue;
    private int contractDuration;
    private int gAContributions;
    private int clubId;

    // Constructor
    public PlayerModel(int playerId, String playerName, String position, String nationality, 
                       int age, double marketValue, int contractDuration, int gAContributions) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.position = position;
        this.nationality = nationality;
        this.age = age;
        this.marketValue = marketValue;
        this.contractDuration = contractDuration;
        this.gAContributions = gAContributions;
    }

    // Getters
    public int getPlayerId() {
        return playerId;
    }
    

    public String getPlayerName() {
        return playerName;
    }

    public String getPosition() {
        return position;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAge() {
        return age;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public int getContractDuration() {
        return contractDuration;
    }

    public int getGAContributions() {
        return gAContributions;
    }
    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    // Setters
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public void setContractDuration(int contractDuration) {
        this.contractDuration = contractDuration;
    }

    public void setGAContributions(int gAContributions) {
        this.gAContributions = gAContributions;
    }
}