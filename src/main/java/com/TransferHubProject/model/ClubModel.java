package com.TransferHubProject.model;

public class ClubModel {
    private int clubId;
    private String clubUsername;
    private String clubEmailId;
    private String password;
    private String role;

    // Constructor
    public ClubModel(int clubId, String clubUsername, String clubEmailId, String password) {
        this.clubId = clubId;
        this.clubUsername = clubUsername;
        this.clubEmailId = clubEmailId;
        this.password = password;
        
    }

    // Getters
    public int getClubId() {
        return clubId;
    }

    public String getClubUsername() {
        return clubUsername;
    }

    public String getClubEmailId() {
        return clubEmailId;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public void setClubUsername(String clubUsername) {
        this.clubUsername = clubUsername;
    }

    public void setClubEmailId(String clubEmailId) {
        this.clubEmailId = clubEmailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
