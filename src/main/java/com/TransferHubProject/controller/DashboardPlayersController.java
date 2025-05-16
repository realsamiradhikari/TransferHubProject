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

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardPlayersController() {
        super();
    }

    /**
     * Fetches the list of players from the database based on a search name.
     * Queries the database and returns a list of PlayerModel objects.
     * 
     * @param searchName The name to search for in the player names.
     * @return A list of PlayerModel objects matching the search criteria.
     * @throws SQLException If a database error occurs during the query.
     */
    private List<PlayerModel> getPlayerList(String searchName) throws SQLException {
        List<PlayerModel> playerList = new ArrayList<>();
        // Establish database connection
        try (Connection conn = DBConfig.getConnection()) {
            if (conn == null) {
                throw new SQLException("Failed to establish database connection");
            }
            // Prepare SQL query to fetch player details from the player_details table
            String sql = "SELECT player_id, player_name, position, nationality, age, market_value, contract_duration, g_a_contributions, club_id " +
                        "FROM player_details";
            if (searchName != null && !searchName.trim().isEmpty()) {
                sql += " WHERE player_name LIKE ?";
            }
            // Prepare the statement for execution
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                if (searchName != null && !searchName.trim().isEmpty()) {
                    // Set the search parameter for the LIKE clause
                    stmt.setString(1, "%" + searchName + "%");
                }
                // Execute query to retrieve player data
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    // Create a new PlayerModel object with data from the result set
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

    /**
     * Handles HTTP GET requests.
     * Fetches the player list based on the search term and forwards to the dashboardplayers.jsp page.
     * 
     * @param request The HttpServletRequest object representing the request.
     * @param response The HttpServletResponse object representing the response.
     * @throws ServletException If the servlet encounters a ServletException.
     * @throws IOException If an I/O error occurs while processing the request.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String searchName = request.getParameter("searchName");
            // Fetch the list of players using the search term
            List<PlayerModel> playerList = getPlayerList(searchName);
            // Add the player list to the request for JSP to display
            request.setAttribute("playerList", playerList);
            // Pass the search term back to JSP for display purposes
            request.setAttribute("searchName", searchName); 
            // Forward the request to dashboardplayers.jsp to show the player list
            request.getRequestDispatcher("WEB-INF/pages/club/dashboardplayers.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error while fetching players", e);
        }
    }

    /**
     * Handles HTTP POST requests.
     * Takes the request and passes it to doGet method for processing.
     * 
     * @param request The HttpServletRequest object representing the request.
     * @param response The HttpServletResponse object representing the response.
     * @throws ServletException If the servlet encounters a ServletException.
     * @throws IOException If an I/O error occurs while processing the request.
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Pass the POST request to doGet to handle it
        doGet(request, response);
    }
}