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

    public AdminProfileController() {
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
            throw new ServletException("Database error while fetching admin profile", e);
        }

        request.setAttribute("clubUsername", clubUsername);
        request.setAttribute("clubEmail", clubEmail);
        request.setAttribute("clubId", clubId);
        request.getRequestDispatcher("WEB-INF/Pages/adminProfile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}