package com.TransferHubProject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import com.TransferHubProject.config.DBConfig;

@WebServlet(urlPatterns = { "/register" })
public class RegistrationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public RegistrationController() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Pages/registration.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clubUsername = request.getParameter("clubid");
        String clubEmail = request.getParameter("email");
        String password = request.getParameter("password");
        
        Map<String, String> errors = new HashMap<>();
        boolean hasErrors = false;
        
        // Input validation
        if (clubUsername == null || clubUsername.trim().isEmpty()) {
            errors.put("clubid", "Club username is required.");
            hasErrors = true;
        }
        
        if (clubEmail == null || clubEmail.trim().isEmpty()) {
            errors.put("email", "Club email is required.");
            hasErrors = true;
        } else if (!clubEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.put("email", "Invalid email format.");
            hasErrors = true;
        }
        
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", "Password is required.");
            hasErrors = true;
        } else if (password.length() < 6) {
            errors.put("password", "Password must be at least 6 characters.");
            hasErrors = true;
        }
        
        if (hasErrors) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/Pages/registration.jsp").forward(request, response);
            return;
        }
        
        // Hash the password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        
        try (Connection conn = DBConfig.getConnection()) {
            String sql = "INSERT INTO club_registration (club_username, club_email_id, password) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, clubUsername);
                stmt.setString(2, clubEmail);
                stmt.setString(3, hashedPassword);
                stmt.executeUpdate();
                response.sendRedirect("login");
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                if (e.getMessage().contains("club_username")) {
                    errors.put("clubid", "Username already exists.");
                } else if (e.getMessage().contains("club_email_id")) {
                    errors.put("email", "Email already exists.");
                }
            } else {
                request.setAttribute("error", "Registration failed. Database error.");
            }
            
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/Pages/registration.jsp").forward(request, response);
        }
    }
}