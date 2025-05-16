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
import com.TransferHubProject.config.DBConfig;

@WebServlet(asyncSupported = true, urlPatterns = { "/admindashboard" })
public class AdminDashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboardController() {
        super();
    }

    /**
     * Handles HTTP GET requests.
     * Fetches the total number of clubs and players from the database and displays them on the admin dashboard.
     * 
     * @param request The HttpServletRequest object representing the request.
     * @param response The HttpServletResponse object representing the response.
     * @throws ServletException If the servlet encounters a ServletException.
     * @throws IOException If an I/O error occurs while processing the request.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int totalClubs = 0;
        int totalPlayers = 0;

        // Fetch total clubs from club_registration table
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM club_registration");
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Retrieve the total count of clubs from the result set
                totalClubs = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new ServletException("Error fetching total clubs", e);
        }

        // Fetch total players from player_details table
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM player_details");
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Retrieve the total count of players from the result set
                totalPlayers = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new ServletException("Error fetching total players", e);
        }

        // Set attributes to be used in JSP
        request.setAttribute("totalClubs", totalClubs);
        request.setAttribute("totalPlayers", totalPlayers);

        // Forward the request to adminDashboard.jsp to display the admin dashboard
        request.getRequestDispatcher("WEB-INF/pages/admin/adminDashboard.jsp").forward(request, response);
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