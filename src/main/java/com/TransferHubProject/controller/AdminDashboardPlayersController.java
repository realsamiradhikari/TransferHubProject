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
                List<PlayerModel> playerList = listPlayers();
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
            if ("insert".equals(action)) {
                insertPlayer(request);
            } else if ("update".equals(action)) {
                updatePlayer(request);
            }
            response.sendRedirect("admindashboardplayers");
        } catch (SQLException e) {
            throw new ServletException("Error processing player data", e);
        }
    }

    private List<PlayerModel> listPlayers() throws SQLException {
        List<PlayerModel> playerList = new ArrayList<>();
        
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT player_id, player_name, position, nationality, age, market_value, " +
                 "contract_duration, g_a_contributions FROM player_details");
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
                playerList.add(player);
            }
        }
        return playerList;
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT player_id, player_name, position, nationality, age, market_value, " +
                 "contract_duration, g_a_contributions FROM player_details WHERE player_id = ?")) {
            
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
                request.setAttribute("playerToEdit", player);
            }
        }
        
        List<PlayerModel> playerList = listPlayers();
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