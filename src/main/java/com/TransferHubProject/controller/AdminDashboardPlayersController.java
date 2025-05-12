package com.TransferHubProject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.TransferHubProject.config.DBConfig;
import com.TransferHubProject.model.PlayerModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/admindashboardplayers" })
public class AdminDashboardPlayersController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminDashboardPlayersController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("edit".equals(action)) {
                showEditForm(request, response);
            } else if ("delete".equals(action)) {
                deletePlayer(request, response);
            } else {
                // Fetch player list directly
                List<PlayerModel> playerList = new ArrayList<>();
                try (Connection conn = DBConfig.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(
                         "SELECT player_id, player_name, position, nationality, age, market_value, " +
                         "contract_duration, g_a_contributions, club_id FROM player_details");
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        PlayerModel player = new PlayerModel(
                            rs.getInt("player_id"),
                            rs.getString("player_name"),
                            rs.getString("position"),
                            rs.getString("nationality"),
                            rs.getInt("age"),
                            rs.getDouble("market_value"),
                            rs.getInt("contract_duration"),
                            rs.getInt("g_a_contributions")
                        );
                        player.setClubId(rs.getInt("club_id"));
                        playerList.add(player);
                    }
                }
                request.setAttribute("playerList", playerList);
                request.getRequestDispatcher("WEB-INF/Pages/admin/adminDashboardPlayers.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error fetching player list or handling action", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            // Extract form parameters as strings
            String playerIdStr = request.getParameter("playerId");
            String playerName = request.getParameter("playerName");
            String position = request.getParameter("position");
            String nationality = request.getParameter("nationality");
            String ageStr = request.getParameter("age");
            String marketValueStr = request.getParameter("marketValue");
            String contractDurationStr = request.getParameter("contractDuration");
            String gAContributionsStr = request.getParameter("gAContributions");
            String clubIdStr = request.getParameter("clubId");

            // Parse numeric fields (HTML validation ensures these are valid)
            int playerId = playerIdStr != null && !playerIdStr.isEmpty() ? Integer.parseInt(playerIdStr) : 0;
            int age = ageStr != null && !ageStr.isEmpty() ? Integer.parseInt(ageStr) : 0;
            double marketValue = marketValueStr != null && !marketValueStr.isEmpty() ? Double.parseDouble(marketValueStr) : 0.0;
            int contractDuration = contractDurationStr != null && !contractDurationStr.isEmpty() ? Integer.parseInt(contractDurationStr) : 0;
            int gAContributions = gAContributionsStr != null && !gAContributionsStr.isEmpty() ? Integer.parseInt(gAContributionsStr) : 0;
            int clubId = clubIdStr != null && !clubIdStr.isEmpty() ? Integer.parseInt(clubIdStr) : 0;

            // Create PlayerModel
            PlayerModel player = new PlayerModel(playerId, playerName, position, nationality, age, marketValue, contractDuration, gAContributions);
            player.setClubId(clubId);

            // Check for errors
            List<String> errors = new ArrayList<>();
            Connection conn = DBConfig.getConnection();

            // Check playerId uniqueness
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT COUNT(*) FROM player_details WHERE player_id = ?")) {
                stmt.setInt(1, playerId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0 && (action.equals("insert") || (action.equals("update") && !isEditingExistingPlayer(playerId, conn)))) {
                    errors.add("playerId:Player ID must be unique.");
                }
            }

            // Check clubId validity (must exist in club_registration)
            if (clubId > 0) {
                try (PreparedStatement stmt = conn.prepareStatement(
                        "SELECT COUNT(*) FROM club_registration WHERE club_id = ?")) {
                    stmt.setInt(1, clubId);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next() && rs.getInt(1) == 0) {
                        errors.add("clubId:Club ID does not exist in the database.");
                    }
                }
            }

            if (!errors.isEmpty()) {
                // Fetch player list
                List<PlayerModel> playerList = new ArrayList<>();
                try (PreparedStatement stmt = conn.prepareStatement(
                        "SELECT player_id, player_name, position, nationality, age, market_value, " +
                        "contract_duration, g_a_contributions, club_id FROM player_details");
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        PlayerModel p = new PlayerModel(
                            rs.getInt("player_id"),
                            rs.getString("player_name"),
                            rs.getString("position"),
                            rs.getString("nationality"),
                            rs.getInt("age"),
                            rs.getDouble("market_value"),
                            rs.getInt("contract_duration"),
                            rs.getInt("g_a_contributions")
                        );
                        p.setClubId(rs.getInt("club_id"));
                        playerList.add(p);
                    }
                }
                request.setAttribute("playerList", playerList);
                request.setAttribute("formPlayer", player); // Use formPlayer for repopulation
                request.setAttribute("isAdding", true); // Ensure it's treated as an add operation
                request.setAttribute("errors", errors.toArray(new String[0]));
                request.getRequestDispatcher("WEB-INF/Pages/admin/adminDashboardPlayers.jsp").forward(request, response);
                conn.close();
                return;
            }

            if ("insert".equals(action)) {
                insertPlayer(request);
            } else if ("update".equals(action)) {
                updatePlayer(request);
            }
            conn.close();
            response.sendRedirect("admindashboardplayers");
        } catch (SQLException e) {
            throw new ServletException("Error processing player data", e);
        } catch (NumberFormatException e) {
            // If parsing fails, redirect back to the form (HTML validation should prevent this)
            List<PlayerModel> playerList = new ArrayList<>();
            try (Connection conn = DBConfig.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                     "SELECT player_id, player_name, position, nationality, age, market_value, " +
                     "contract_duration, g_a_contributions, club_id FROM player_details");
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PlayerModel player = new PlayerModel(
                        rs.getInt("player_id"),
                        rs.getString("player_name"),
                        rs.getString("position"),
                        rs.getString("nationality"),
                        rs.getInt("age"),
                        rs.getDouble("market_value"),
                        rs.getInt("contract_duration"),
                        rs.getInt("g_a_contributions")
                    );
                    player.setClubId(rs.getInt("club_id"));
                    playerList.add(player);
                }
            } catch (SQLException se) {
                throw new ServletException("Error fetching player list", se);
            }
            request.setAttribute("playerList", playerList);
            request.getRequestDispatcher("WEB-INF/Pages/admin/adminDashboardPlayers.jsp").forward(request, response);
        }
    }

    private boolean isEditingExistingPlayer(int playerId, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT COUNT(*) FROM player_details WHERE player_id = ?")) {
            stmt.setInt(1, playerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT player_id, player_name, position, nationality, age, market_value, " +
                 "contract_duration, g_a_contributions, club_id FROM player_details WHERE player_id = ?")) {
            
            stmt.setInt(1, playerId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                PlayerModel player = new PlayerModel(
                    rs.getInt("player_id"),
                    rs.getString("player_name"),
                    rs.getString("position"),
                    rs.getString("nationality"),
                    rs.getInt("age"),
                    rs.getDouble("market_value"),
                    rs.getInt("contract_duration"),
                    rs.getInt("g_a_contributions")
                );
                player.setClubId(rs.getInt("club_id"));
                request.setAttribute("playerToEdit", player);
                request.setAttribute("isAdding", false);
            }
        } catch (SQLException e) {
            throw new ServletException("Error fetching player for edit", e);
        }

        List<PlayerModel> playerList = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT player_id, player_name, position, nationality, age, market_value, " +
                 "contract_duration, g_a_contributions, club_id FROM player_details");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PlayerModel player = new PlayerModel(
                    rs.getInt("player_id"),
                    rs.getString("player_name"),
                    rs.getString("position"),
                    rs.getString("nationality"),
                    rs.getInt("age"),
                    rs.getDouble("market_value"),
                    rs.getInt("contract_duration"),
                    rs.getInt("g_a_contributions")
                );
                player.setClubId(rs.getInt("club_id"));
                playerList.add(player);
            }
        } catch (SQLException e) {
            throw new ServletException("Error fetching player list", e);
        }
        request.setAttribute("playerList", playerList);
        request.getRequestDispatcher("WEB-INF/Pages/admin/adminDashboardPlayers.jsp").forward(request, response);
    }

    private void insertPlayer(HttpServletRequest request) throws SQLException {
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        String playerName = request.getParameter("playerName");
        String position = request.getParameter("position");
        String nationality = request.getParameter("nationality");
        int age = Integer.parseInt(request.getParameter("age"));
        double marketValue = Double.parseDouble(request.getParameter("marketValue"));
        int contractDuration = Integer.parseInt(request.getParameter("contractDuration"));
        int gAContributions = Integer.parseInt(request.getParameter("gAContributions"));
        int clubId = Integer.parseInt(request.getParameter("clubId"));

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO player_details (player_id, player_name, position, nationality, age, " +
                 "market_value, contract_duration, g_a_contributions, club_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            
            stmt.setInt(1, playerId);
            stmt.setString(2, playerName);
            stmt.setString(3, position);
            stmt.setString(4, nationality);
            stmt.setInt(5, age);
            stmt.setDouble(6, marketValue);
            stmt.setInt(7, contractDuration);
            stmt.setInt(8, gAContributions);
            stmt.setInt(9, clubId);
            stmt.executeUpdate();
        }
    }

    private void updatePlayer(HttpServletRequest request) throws SQLException {
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        String playerName = request.getParameter("playerName");
        String position = request.getParameter("position");
        String nationality = request.getParameter("nationality");
        int age = Integer.parseInt(request.getParameter("age"));
        double marketValue = Double.parseDouble(request.getParameter("marketValue"));
        int contractDuration = Integer.parseInt(request.getParameter("contractDuration"));
        int gAContributions = Integer.parseInt(request.getParameter("gAContributions"));
        int clubId = Integer.parseInt(request.getParameter("clubId"));

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "UPDATE player_details SET player_name = ?, position = ?, nationality = ?, age = ?, " +
                 "market_value = ?, contract_duration = ?, g_a_contributions = ?, club_id = ? WHERE player_id = ?")) {
            
            stmt.setString(1, playerName);
            stmt.setString(2, position);
            stmt.setString(3, nationality);
            stmt.setInt(4, age);
            stmt.setDouble(5, marketValue);
            stmt.setInt(6, contractDuration);
            stmt.setInt(7, gAContributions);
            stmt.setInt(8, clubId);
            stmt.setInt(9, playerId);
            stmt.executeUpdate();
        }
    }

    private void deletePlayer(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM player_details WHERE player_id = ?")) {
            
            stmt.setInt(1, playerId);
            stmt.executeUpdate();
        }
        
        response.sendRedirect("admindashboardplayers");
    }
}