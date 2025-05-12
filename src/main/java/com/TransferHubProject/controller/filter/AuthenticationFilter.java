package com.TransferHubProject.controller.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(urlPatterns = { "/dashboard", "/admindashboard", "/admindashboardclubs", "/admindashboardplayers", "/dashboardplayers" })
public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;
        String uri = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        // Check if the user is logged in
        if (username == null) {
            // If not logged in, redirect to login servlet
            httpResponse.sendRedirect(contextPath + "/login");
            return;
        }

        // Define admin and user URLs
        boolean isAdminUrl = uri.equals(contextPath + "/admindashboard") ||
                             uri.equals(contextPath + "/admindashboardclubs") ||
                             uri.equals(contextPath + "/admindashboardplayers");
        boolean isUserUrl = uri.equals(contextPath + "/dashboard") ||
                            uri.equals(contextPath + "/dashboardplayers");

        // Admin access control
        if ("admin".equals(username)) {
            if (isUserUrl) {
                // Admin cannot access user URLs like /dashboard or /dashboardplayers
                httpResponse.sendRedirect(contextPath + "/admindashboard");
                return;
            }
        } else {
            // Non-admin users (clubs) cannot access admin URLs
            if (isAdminUrl) {
                httpResponse.sendRedirect(contextPath + "/dashboard");
                return;
            }
        }

        // Valid access, proceed with the request
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {}

    public void destroy() {}
}