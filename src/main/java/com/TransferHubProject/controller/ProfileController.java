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

@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileController() {
        super();
    }

    /**
     * Handles HTTP GET requests.
     * Fetches the user's profile details from the database and displays them on the profile page.
     * Redirects to login if the user is not logged in.
     * 
     * @param request The HttpServletRequest object representing the request.
     * @param response The HttpServletResponse object representing the response.
     * @throws ServletException If the servlet encounters a ServletException.
     * @throws IOException If an I/O error occurs while processing the request.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is logged in by fetching the session
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        // If no user is logged in, redirect to the login page
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
                // SQL query to get club details based on the username
                String sql = "SELECT club_id, club_username, club_email_id FROM club_registration WHERE club_username = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, username);
                    // Execute query to retrieve profile data
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        // Extract club details from the result set
                        clubId = rs.getInt("club_id");
                        clubUsername = rs.getString("club_username");
                        clubEmail = rs.getString("club_email_id");
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error while fetching profile", e);
        }

        // Add profile details to the request for display in JSP
        request.setAttribute("clubUsername", clubUsername);
        request.setAttribute("clubEmail", clubEmail);
        request.setAttribute("clubId", clubId);
        // Forward to the profile page to display the details
        request.getRequestDispatcher("WEB-INF/pages/profile.jsp").forward(request, response);
    }

    /**
     * Handles HTTP POST requests.
     * Updates the username in the database if a new username is provided, then redirects to the profile page.
     * Redirects to login if the user is not logged in.
     * 
     * @param request The HttpServletRequest object representing the request.
     * @param response The HttpServletResponse object representing the response.
     * @throws ServletException If the servlet encounters a ServletException.
     * @throws IOException If an I/O error occurs while processing the request.
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is logged in by fetching the session
        HttpSession session = request.getSession(false);
        String currentUsername = (session != null) ? (String) session.getAttribute("username") : null;

        // If no user is logged in, redirect to the login page
        if (currentUsername == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get the new username from the request
        String newUsername = request.getParameter("newUsername");

        // Check if a new username is provided and not empty
        if (newUsername != null && !newUsername.trim().isEmpty()) {
            // Update the username in the database
            try (Connection conn = DBConfig.getConnection()) {
                if (conn != null) {
                    // SQL query to update the username in the club_registration table
                    String sql = "UPDATE club_registration SET club_username = ? WHERE club_username = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setString(1, newUsername);
                        stmt.setString(2, currentUsername);
                        // Execute the update query and check if it was successful
                        int rowsUpdated = stmt.executeUpdate();
                        if (rowsUpdated > 0) {
                            // Update the session with the new username
                            session.setAttribute("username", newUsername);
                        }
                    }
                }
            } catch (SQLException e) {
                throw new ServletException("Database error while updating username", e);
            }
        }

        // Redirect to the profile page to refresh the displayed data
        response.sendRedirect(request.getContextPath() + "/profile");
    }
}