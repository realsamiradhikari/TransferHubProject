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
import com.TransferHubProject.model.ClubModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/admindashboardclubs" })
public class AdminDashboardClubsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboardClubsController() {
        super();
    }

    /**
     * Handles HTTP GET requests.
     * Fetches the list of clubs from the database and forwards it to the admin dashboard clubs page.
     * 
     * @param request The HttpServletRequest object representing the request.
     * @param response The HttpServletResponse object representing the response.
     * @throws ServletException If the servlet encounters a ServletException.
     * @throws IOException If an I/O error occurs while processing the request.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Create a list to store club data
        List<ClubModel> clubList = new ArrayList<>();
        // Fetch club details from the database
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT club_id, club_username, club_email_id FROM club_registration");
             ResultSet rs = stmt.executeQuery()) {
            // Loop through the result set to create ClubModel objects
            while (rs.next()) {
                // Create a new ClubModel object with data from the database
                ClubModel club = new ClubModel(
                    rs.getInt("club_id"),
                    rs.getString("club_username"),
                    rs.getString("club_email_id"),
                    null
                );
                // Add the club to the list
                clubList.add(club);
            }
        } catch (SQLException e) {
            // Handle database errors by throwing a ServletException
            throw new ServletException("Error fetching club list", e);
        }
        // Add the club list to the request for display in JSP
        request.setAttribute("clubList", clubList);
        // Forward the request to adminDashboardClubs.jsp to show the club list
        request.getRequestDispatcher("WEB-INF/pages/admin/adminDashboardClubs.jsp").forward(request, response);
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