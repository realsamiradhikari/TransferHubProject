package com.TransferHubProject.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.TransferHubProject.config.DBConfig;

@WebServlet("/test-db")
public class TestDb extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        try {
            Connection conn = DBConfig.getConnection();
            response.getWriter().write("Database connection successful!");
        } catch (SQLException e) {
            response.getWriter().write("Database connection failed: " + e.getMessage());
        }
    }
}