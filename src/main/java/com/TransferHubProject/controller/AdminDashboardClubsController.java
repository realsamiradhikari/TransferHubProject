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

    public AdminDashboardClubsController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<ClubModel> clubList = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT club_id, club_username, club_email_id FROM club_registration");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ClubModel club = new ClubModel(
                    rs.getInt("club_id"),
                    rs.getString("club_username"),
                    rs.getString("club_email_id"),
                    null
                );
                clubList.add(club);
            }
        } catch (SQLException e) {
            throw new ServletException("Error fetching club list", e);
        }
        request.setAttribute("clubList", clubList);
        request.getRequestDispatcher("WEB-INF/Pages/admin/adminDashboardClubs.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}