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

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

    /**
     * Handles HTTP GET requests.
     * Takes the request and forwards it to the login.jsp file for display.
     * 
     * @param request The HttpServletRequest object representing the request.
     * @param response The HttpServletResponse object representing the response.
     * @throws ServletException If the servlet encounters a ServletException.
     * @throws IOException If an I/O error occurs while processing the request.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
    }

    /**
     * Handles HTTP POST requests.
     * Processes login data, validates input, checks password, and redirects to appropriate dashboard.
     * Shows error on login page if login fails.
     * 
     * @param request The HttpServletRequest object representing the request.
     * @param response The HttpServletResponse object representing the response.
     * @throws ServletException If the servlet encounters a ServletException.
     * @throws IOException If an I/O error occurs while processing the request.
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Input validation
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("error", "Username and password are required.");
            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DBConfig.getConnection()) {
            String sql = "SELECT password FROM club_registration WHERE club_username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                // Execute query to retrieve password from database
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    // Check if provided password matches the hashed password
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        HttpSession session = request.getSession(); //Creates a new session if one doesn't exist
                        session.setAttribute("username", username); //Stores the session for the respective user using their username. The string 'username' stores the session object
                        // Redirect based on username
                        if ("admin".equalsIgnoreCase(username)) {
                            response.sendRedirect("admindashboard");
                        } else {
                            response.sendRedirect("dashboard");
                        }
                    } else {
                        request.setAttribute("error", "Invalid password for the given username");
                        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "Invalid username and password.");
                    request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Login failed due to a server error.");
            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
        }
    }
}