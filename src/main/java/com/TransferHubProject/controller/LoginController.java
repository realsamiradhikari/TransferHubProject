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
import org.mindrot.jbcrypt.BCrypt;
import com.TransferHubProject.config.DBConfig;

@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Input validation
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("error", "Username and password are required.");
            request.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DBConfig.getConnection()) {
            String sql = "SELECT password FROM club_registration WHERE club_username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String hashedPassword = rs.getString("password");

                    if (BCrypt.checkpw(password, hashedPassword)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("username", username);
                        // Redirect based on username
                        if ("admin".equalsIgnoreCase(username)) {
                            response.sendRedirect("admindashboard");
                        } else {
                            response.sendRedirect("dashboard");
                        }
                    } else {
                        request.setAttribute("error", "Invalid username or password.");
                        request.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "Invalid username or password.");
                    request.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Login failed due to a server error.");
            request.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(request, response);
        }
    }
}