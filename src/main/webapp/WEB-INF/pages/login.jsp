<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TransferHub - Football Transfer Market</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
    <div class="container">
        <!-- Header -->
        <header>
            <div class="nav-container">
                <div class="logo">
                    <svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
                        <circle cx="50" cy="50" r="45" fill="#004d98" />
                        <path d="M50 15 L75 35 L65 65 L35 65 L25 35 Z" fill="#a50044" />
                        <text x="50" y="55" font-family="Arial" font-size="12" text-anchor="middle" fill="white" font-weight="bold">TRANSFER</text>
                        <text x="50" y="67" font-family="Arial" font-size="12" text-anchor="middle" fill="white" font-weight="bold">HUB</text>
                    </svg>
                </div>
                <ul class="main-nav">
                    <li class="nav-item"><a href="${pageContext.request.contextPath}/Home" class="nav-link">Home</a></li>
                    <li class="nav-item"><a href="#" class="nav-link">About Us</a></li>
                    <li class="nav-item"><a href="#" class="nav-link">Contact Us</a></li>
                </ul>
                <div class="search-icon">🔍</div>
            </div>
        </header>

        <!-- Main Content -->
        <div class="main-content">
            <!-- Main Section with Animation and Login Form -->
            <div class="main-section">
                <!-- Hero Section (Left Side) -->
                <div class="hero">
                    <div class="hero-content">
                        <h1 class="hero-title">Transfer<span class="ball-icon">⚽</span>Hub</h1>
                        <p class="hero-subtitle">The Ultimate Football Transfer Market Platform</p>
                    </div>
                </div>

                <!-- Login Form (Right Side) -->
                <div class="login-container">
                    <div class="login-content">
                        <h3 class="login-title">Welcome Back</h3>
                        <p class="login-subtitle">Sign in to your TransferHub account</p>
                        
                        <form action="${pageContext.request.contextPath}/login" method="post" id="login-form">
                            <% if (request.getAttribute("error") != null) { %>
                                <p class="error"><%= request.getAttribute("error") %></p>
                            <% } %>
                            <div class="form-group">
                                <label for="username" class="form-label">Username</label>
                                <input type="text" id="username" name="username" class="form-input" placeholder="Enter your username" required>
                            </div>
                            
                            <div class="form-group">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" id="password" name="password" class="form-input" placeholder="Enter your password" required>
                            </div>
                            
                            <div class="remember-forgot">
                                <div class="remember-me">
                                    <input type="checkbox" id="remember" name="remember" class="remember-checkbox">
                                    <label for="remember">Remember me</label>
                                </div>
                                <a href="#" class="forgot-password">Forgot Password?</a>
                            </div>
                            
                            <button type="submit" class="login-btn-form">Login</button>
                        </form>
                        
                        <div class="register-link">
                            <span class="register-text">Don't have an account?</span>
                            <a href="${pageContext.request.contextPath}/register" class="register-btn">Register Now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>