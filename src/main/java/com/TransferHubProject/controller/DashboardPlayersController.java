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

@WebServlet(urlPatterns = { "/dashboardplayers" })
public class DashboardPlayersController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DashboardPlayersController() {
        super();
    }

    private List<PlayerModel> getPlayerList(String searchName) throws SQLException {
        List<PlayerModel> playerList = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection()) {
            if (conn == null) {
                throw new SQLException("Failed to establish database connection");
            }
            String sql = "SELECT player_id, player_name, position, nationality, age, market_value, contract_duration, g_a_contributions, club_id " +
                        "FROM player_details";
            if (searchName != null && !searchName.trim().isEmpty()) {
                sql += " WHERE player_name LIKE ?";
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                if (searchName != null && !searchName.trim().isEmpty()) {
                    stmt.setString(1, "%" + searchName + "%");
                }
                ResultSet rs = stmt.executeQuery();
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
        }
        return playerList;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String searchName = request.getParameter("searchName");
            List<PlayerModel> playerList = getPlayerList(searchName);
            request.setAttribute("playerList", playerList);
            request.setAttribute("searchName", searchName); // Pass search term back to JSP
            request.getRequestDispatcher("WEB-INF/Pages/Club/dashboardplayers.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error while fetching players", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}