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

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboardPlayersController() {
        super();
    }

    /**
     * Handles HTTP GET requests.
     * Manages actions like displaying the player list, editing, or deleting a player.
     * Fetches player data from the database and forwards to the admin dashboard players page.
     * 
     * @param request The HttpServletRequest object representing the request.
     * @param response The HttpServletResponse object representing the response.
     * @throws ServletException If the servlet encounters a ServletException.
     * @throws IOException If an I/O error occurs while processing the request.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get the action parameter to determine what operation to perform (edit, delete, or list)
        String action = request.getParameter("action");

        try {
            if ("edit".equals(action)) {
                // If action is edit, show the edit form for the selected player
                showEditForm(request, response);
            } else if ("delete".equals(action)) {
                // If action is delete, remove the player from the database
                deletePlayer(request, response);
            } else {
                // Default action: fetch and display the list of all players
                List<PlayerModel> playerList = new ArrayList<>();
                // Establish database connection to fetch player details
                try (Connection conn = DBConfig.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(
                         "SELECT player_id, player_name, position, nationality, age, market_value, " +
                         "contract_duration, g_a_contributions, club_id FROM player_details");
                     ResultSet rs = stmt.executeQuery()) {
                    // Loop through the result set to create PlayerModel objects
                    while (rs.next()) {
                        // Create a new PlayerModel object with data from the database
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
                        // Set the club ID for the player
                        player.setClubId(rs.getInt("club_id"));
                        // Add the player to the list
                        playerList.add(player);
                    }
                }
                // Add the player list to the request for display in JSP
                request.setAttribute("playerList", playerList);
                // Forward the request to adminDashboardPlayers.jsp to show the player list
                request.getRequestDispatcher("WEB-INF/pages/admin/adminDashboardPlayers.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            // Handle database errors by throwing a ServletException
            throw new ServletException("Error fetching player list or handling action", e);
        }
    }

    /**
     * Handles HTTP POST requests.
     * Processes actions like inserting or updating a player in the database.
     * Validates input data, handles errors, and redirects to the player list page.
     * 
     * @param request The HttpServletRequest object representing the request.
     * @param response The HttpServletResponse object representing the response.
     * @throws ServletException If the servlet encounters a ServletException.
     * @throws IOException If an I/O error occurs while processing the request.
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get the action parameter to determine if it's an insert or update operation
        String action = request.getParameter("action");

        try {
            // Extract form parameters as strings from the request
            String playerIdStr = request.getParameter("playerId");
            String playerName = request.getParameter("playerName");
            String position = request.getParameter("position");
            String nationality = request.getParameter("nationality");
            String ageStr = request.getParameter("age");
            String marketValueStr = request.getParameter("marketValue");
            String contractDurationStr = request.getParameter("contractDuration");
            String gAContributionsStr = request.getParameter("gAContributions");
            String clubIdStr = request.getParameter("clubId");

            // Parse numeric fields with default values if empty (HTML validation ensures valid input)
            int playerId = playerIdStr != null && !playerIdStr.isEmpty() ? Integer.parseInt(playerIdStr) : 0;
            int age = ageStr != null && !ageStr.isEmpty() ? Integer.parseInt(ageStr) : 0;
            double marketValue = marketValueStr != null && !marketValueStr.isEmpty() ? Double.parseDouble(marketValueStr) : 0.0;
            int contractDuration = contractDurationStr != null && !contractDurationStr.isEmpty() ? Integer.parseInt(contractDurationStr) : 0;
            int gAContributions = gAContributionsStr != null && !gAContributionsStr.isEmpty() ? Integer.parseInt(gAContributionsStr) : 0;
            int clubId = clubIdStr != null && !clubIdStr.isEmpty() ? Integer.parseInt(clubIdStr) : 0;

            // Create a PlayerModel object with the parsed data
            PlayerModel player = new PlayerModel(playerId, playerName, position, nationality, age, marketValue, contractDuration, gAContributions);
            player.setClubId(clubId);

            // List to store validation errors
            List<String> errors = new ArrayList<>();
            // Establish database connection
            Connection conn = DBConfig.getConnection();

            // Check playerId uniqueness in the database
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT COUNT(*) FROM player_details WHERE player_id = ?")) {
                stmt.setInt(1, playerId);
                // Execute query to check if player ID already exists
                ResultSet rs = stmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0 && (action.equals("insert") || (action.equals("update") && !isEditingExistingPlayer(playerId, conn)))) {
                    // Add error if player ID is not unique during insert or update
                    errors.add("playerId:Player ID must be unique.");
                }
            }

            // Check clubId validity (must exist in club_registration table)
            if (clubId > 0) {
                try (PreparedStatement stmt = conn.prepareStatement(
                        "SELECT COUNT(*) FROM club_registration WHERE club_id = ?")) {
                    stmt.setInt(1, clubId);
                    // Execute query to check if club ID exists
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next() && rs.getInt(1) == 0) {
                        // Add error if club ID does not exist
                        errors.add("clubId:Club ID does not exist in the database.");
                    }
                }
            }

            // If there are validation errors, show the form again with error messages
            if (!errors.isEmpty()) {
                // Fetch the current player list to display
                List<PlayerModel> playerList = new ArrayList<>();
                try (PreparedStatement stmt = conn.prepareStatement(
                        "SELECT player_id, player_name, position, nationality, age, market_value, " +
                        "contract_duration, g_a_contributions, club_id FROM player_details");
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        // Create PlayerModel objects for the current player list
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
                // Add the player list, form data, and errors to the request
                request.setAttribute("playerList", playerList);
                request.setAttribute("formPlayer", player); // Use formPlayer to repopulate the form
                request.setAttribute("isAdding", true); // Indicate this is an add operation
                request.setAttribute("errors", errors.toArray(new String[0]));
                // Forward to the JSP page to show the form with errors
                request.getRequestDispatcher("WEB-INF/pages/admin/adminDashboardPlayers.jsp").forward(request, response);
                conn.close();
                return;
            }

            // Perform the requested action (insert or update)
            if ("insert".equals(action)) {
                insertPlayer(request);
            } else if ("update".equals(action)) {
                updatePlayer(request);
            }
            // Close the database connection
            conn.close();
            // Redirect to the player list page after successful operation
            response.sendRedirect("admindashboardplayers");
        } catch (SQLException e) {
            // Handle database errors by throwing a ServletException
            throw new ServletException("Error processing player data", e);
        } catch (NumberFormatException e) {
            // Handle parsing errors (shouldn't happen with HTML validation)
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
            // Add the player list to the request and forward to JSP
            request.setAttribute("playerList", playerList);
            request.getRequestDispatcher("WEB-INF/pages/admin/adminDashboardPlayers.jsp").forward(request, response);
        }
    }

    /**
     * Checks if a player ID already exists in the database.
     * Used to verify if the player ID is being edited or if it's a new entry.
     * 
     * @param playerId The ID of the player to check.
     * @param conn The database connection.
     * @return True if the player ID exists, false otherwise.
     * @throws SQLException If a database error occurs.
     */
    private boolean isEditingExistingPlayer(int playerId, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT COUNT(*) FROM player_details WHERE player_id = ?")) {
            stmt.setInt(1, playerId);
            // Execute query to check if the player ID exists
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    /**
     * Displays the edit form for a specific player.
     * Fetches the player details by ID and forwards to the JSP page for editing.
     * 
     * @param request The HttpServletRequest object representing the request.
     * @param response The HttpServletResponse object representing the response.
     * @throws ServletException If the servlet encounters a ServletException.
     * @throws IOException If an I/O error occurs while processing the request.
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get the player ID from the request parameter
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        
        // Fetch the player details from the database
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT player_id, player_name, position, nationality, age, market_value, " +
                 "contract_duration, g_a_contributions, club_id FROM player_details WHERE player_id = ?")) {
            
            stmt.setInt(1, playerId);
            // Execute query to get the player details
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Create a PlayerModel object with the fetched data
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
                // Add the player to the request for editing in the form
                request.setAttribute("playerToEdit", player);
                request.setAttribute("isAdding", false);
            }
        } catch (SQLException e) {
            throw new ServletException("Error fetching player for edit", e);
        }

        // Fetch the current player list to display alongside the edit form
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
        // Add the player list to the request and forward to JSP
        request.setAttribute("playerList", playerList);
        request.getRequestDispatcher("WEB-INF/pages/admin/adminDashboardPlayers.jsp").forward(request, response);
    }

    /**
     * Inserts a new player into the database.
     * Takes player data from the request and adds it to the player_details table.
     * 
     * @param request The HttpServletRequest object containing the player data.
     * @throws SQLException If a database error occurs during insertion.
     */
    private void insertPlayer(HttpServletRequest request) throws SQLException {
        // Extract and parse player data from the request
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        String playerName = request.getParameter("playerName");
        String position = request.getParameter("position");
        String nationality = request.getParameter("nationality");
        int age = Integer.parseInt(request.getParameter("age"));
        double marketValue = Double.parseDouble(request.getParameter("marketValue"));
        int contractDuration = Integer.parseInt(request.getParameter("contractDuration"));
        int gAContributions = Integer.parseInt(request.getParameter("gAContributions"));
        int clubId = Integer.parseInt(request.getParameter("clubId"));

        // Insert the new player into the database
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
            // Execute the insert query
            stmt.executeUpdate();
        }
    }

    /**
     * Updates an existing player in the database.
     * Takes updated player data from the request and modifies the corresponding record.
     * 
     * @param request The HttpServletRequest object containing the updated player data.
     * @throws SQLException If a database error occurs during the update.
     */
    private void updatePlayer(HttpServletRequest request) throws SQLException {
        // Extract and parse updated player data from the request
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        String playerName = request.getParameter("playerName");
        String position = request.getParameter("position");
        String nationality = request.getParameter("nationality");
        int age = Integer.parseInt(request.getParameter("age"));
        double marketValue = Double.parseDouble(request.getParameter("marketValue"));
        int contractDuration = Integer.parseInt(request.getParameter("contractDuration"));
        int gAContributions = Integer.parseInt(request.getParameter("gAContributions"));
        int clubId = Integer.parseInt(request.getParameter("clubId"));

        // Update the player details in the database
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
            // Execute the update query
            stmt.executeUpdate();
        }
    }

    /**
     * Deletes a player from the database.
     * Removes the player record based on the player ID and redirects to the player list page.
     * 
     * @param request The HttpServletRequest object containing the player ID.
     * @param response The HttpServletResponse object for redirection.
     * @throws SQLException If a database error occurs during deletion.
     * @throws IOException If an I/O error occurs during redirection.
     */
    private void deletePlayer(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        // Get the player ID to delete
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        
        // Delete the player from the database
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM player_details WHERE player_id = ?")) {
            
            stmt.setInt(1, playerId);
            // Execute the delete query
            stmt.executeUpdate();
        }
        
        // Redirect to the player list page after deletion
        response.sendRedirect("admindashboardplayers");
    }
}