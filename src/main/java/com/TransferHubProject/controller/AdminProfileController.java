package com.TransferHubProject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.TransferHubProject.config.DBConfig;

@WebServlet(asyncSupported = true, urlPatterns = { "/adminprofile" })
public class AdminProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProfileController() {
        super();
    }

    /**
     * Handles HTTP GET requests.
     * Fetches the admin's profile details from the database and forwards them to the admin profile page.
     * Redirects to login if no session exists.
     * 
     * @param request The HttpServletRequest object representing the request.
     * @param response The HttpServletResponse object representing the response.
     * @throws ServletException If the servlet encounters a ServletException.
     * @throws IOException If an I/O error occurs while processing the request.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the session without creating a new one if it doesn't exist
        HttpSession session = request.getSession(false);
        // Get the username from the session if it exists
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        // Redirect to login page if no user is logged in
        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Variables to store profile details
        String clubUsername = null;
        String clubEmail = null;
        int clubId = -1;

        // Fetch profile details from the database
        try (Connection conn = DBConfig.getConnection()) {
            if (conn != null) {
                // SQL query to retrieve admin profile details
                String sql = "SELECT club_id, club_username, club_email_id FROM club_registration WHERE club_username = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    // Set the username parameter for the query
                    stmt.setString(1, username);
                    // Execute the query to get the profile data
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        // Extract profile details from the result set
                        clubId = rs.getInt("club_id");
                        clubUsername = rs.getString("club_username");
                        clubEmail = rs.getString("club_email_id");
                    }
                }
            }
        } catch (SQLException e) {
            // Handle database errors by throwing a ServletException
            throw new ServletException("Database error while fetching admin profile", e);
        }

        // Add profile details to the request for display in JSP
        request.setAttribute("clubUsername", clubUsername);
        request.setAttribute("clubEmail", clubEmail);
        request.setAttribute("clubId", clubId);
        // Forward the request to the admin profile page
        request.getRequestDispatcher("WEB-INF/pages/adminProfile.jsp").forward(request, response);
    }

    /**
     * Handles HTTP POST requests.
     * Delegates the request to the doGet method to display the admin profile.
     * 
     * @param request The HttpServletRequest object representing the request.
     * @param response The HttpServletResponse object representing the response.
     * @throws ServletException If the servlet encounters a ServletException.
     * @throws IOException If an I/O error occurs while processing the request.
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pass the POST request to doGet to handle it
        doGet(request, response);
    }
}