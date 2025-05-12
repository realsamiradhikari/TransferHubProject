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

    public ProfileController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String clubUsername = null;
        String clubEmail = null;
        int clubId = -1;

        try (Connection conn = DBConfig.getConnection()) {
            if (conn != null) {
                String sql = "SELECT club_id, club_username, club_email_id FROM club_registration WHERE club_username = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, username);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        clubId = rs.getInt("club_id");
                        clubUsername = rs.getString("club_username");
                        clubEmail = rs.getString("club_email_id");
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error while fetching profile", e);
        }

        request.setAttribute("clubUsername", clubUsername);
        request.setAttribute("clubEmail", clubEmail);
        request.setAttribute("clubId", clubId);
        request.getRequestDispatcher("WEB-INF/Pages/profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String currentUsername = (session != null) ? (String) session.getAttribute("username") : null;

        if (currentUsername == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String newUsername = request.getParameter("newUsername");

        if (newUsername != null && !newUsername.trim().isEmpty()) {
            try (Connection conn = DBConfig.getConnection()) {
                if (conn != null) {
                    String sql = "UPDATE club_registration SET club_username = ? WHERE club_username = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setString(1, newUsername);
                        stmt.setString(2, currentUsername);
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