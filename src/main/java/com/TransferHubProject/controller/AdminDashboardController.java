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

    public AdminDashboardController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int totalClubs = 0;
        int totalPlayers = 0;

        // Fetch total clubs from club_registration table
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM club_registration");
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
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
                totalPlayers = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new ServletException("Error fetching total players", e);
        }

        // Set attributes to be used in JSP
        request.setAttribute("totalClubs", totalClubs);
        request.setAttribute("totalPlayers", totalPlayers);

        request.getRequestDispatcher("WEB-INF/Pages/admin/adminDashboard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}