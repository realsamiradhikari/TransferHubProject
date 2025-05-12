package com.TransferHubProject.util;

import com.TransferHubProject.model.PlayerModel;

public class PlayerValidationUtil {

    public static String[] validatePlayer(PlayerModel player, boolean isInsert) {
        String[] errors = new String[7]; // Reduced to 7 fields: playerId, playerName, contractDuration, age, marketValue, gAContributions, clubId
        int errorIndex = 0;

        // Player ID validation
        if (isInsert && player.getPlayerId() <= 0) {
            errors[errorIndex] = "playerId:Player ID must be a positive number.";
            errorIndex++;
        }

        // Player Name validation
        if (player.getPlayerName() == null || player.getPlayerName().trim().isEmpty() || player.getPlayerName().length() < 2) {
            errors[errorIndex] = "playerName:Player Name must be at least 2 characters long.";
            errorIndex++;
        }

        // Contract Duration validation
        if (player.getContractDuration() <= 0) {
            errors[errorIndex] = "contractDuration:Contract Duration must be a positive number.";
            errorIndex++;
        }

        // Age validation
        if (player.getAge() < 15 || player.getAge() > 50) {
            errors[errorIndex] = "age:Age must be between 15 and 50.";
            errorIndex++;
        }

        // Market Value validation
        if (player.getMarketValue() < 0) {
            errors[errorIndex] = "marketValue:Market Value must be a positive number.";
            errorIndex++;
        }

        // G/A Contributions validation
        if (player.getGAContributions() < 0) {
            errors[errorIndex] = "gAContributions:G/A Contributions must be a positive number.";
            errorIndex++;
        }

        // Club ID validation (allowing 0 as per your JSP default)
        if (player.getClubId() < 0) {
            errors[errorIndex] = "clubId:Club ID must be a positive number or zero.";
            errorIndex++;
        }

        // Trim the array to the actual number of errors
        String[] result = new String[errorIndex];
        for (int i = 0; i < errorIndex; i++) {
            result[i] = errors[i];
        }
        return result;
    }

    // Helper method to get error message for a specific field
    public static String getErrorForField(String[] errors, String field) {
        if (errors == null) return "";
        for (int i = 0; i < errors.length; i++) {
            if (errors[i] != null && errors[i].startsWith(field + ":")) {
                return errors[i].substring(field.length() + 1);
            }
        }
        return "";
    }
}