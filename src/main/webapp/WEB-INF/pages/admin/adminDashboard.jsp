<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
</head>
<body>
    <!-- Header with Logo -->
    <div class="header">
        <img src="${pageContext.request.contextPath}/resources/Homeimages/logo.png" alt="TransferHub Logo">
        <h1>Admin Dashboard</h1>
        <div class="admin-info">
            <div class="avatar">A</div>
            <span>Admin</span>
        </div>
    </div>

    <!-- Main Layout -->
    <div class="container">
        <!-- Navigation Bar -->
        <div class="navbar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/adminDashboard" class="active">🏠 Home</a></li>
                <li><a href="${pageContext.request.contextPath}/admindashboardclubs">👥 Club Management</a></li>
                <li><a href="${pageContext.request.contextPath}/admindashboardplayers">⚽ Player Management</a></li>
                <li><a href="${pageContext.request.contextPath}/adminprofile">👤 Profile</a></li>
            </ul>
            <form class="logout-form" action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <h2>Home</h2>

            <!-- Dashboard Cards -->
            <div class="dashboard-cards">
                <div class="card">
                    <h3>Total Clubs</h3>
                    <p>${totalClubs}</p>
                </div>
                <div class="card">
                    <h3>Total Players</h3>
                    <p>${totalPlayers}</p>
                </div>
                <div class="card">
                    <h3>Active Transfers</h3>
                    <p>12</p>
                </div>
            </div>

            <!-- Placeholder Chart -->
            <div class="chart-placeholder">
                <h3>Player Performance Trends</h3>
                <div class="placeholder-box">
                    [Chart Placeholder - Player Performance Trends]
                </div>
            </div>

            <!-- Top Performing Players -->
            <div class="top-players">
                <h3>Top Performing Players</h3>
                <ul>
                    <li><span>Lionel Messi</span><span>Goals: 30</span></li>
                    <li><span>Robert Lewandowski</span><span>Goals: 28</span></li>
                    <li><span>Neymar Jr.</span><span>Goals: 25</span></li>
                    <li><span>Kylian Mbappé</span><span>Goals: 22</span></li>
                    <li><span>Pedri</span><span>Goals: 15</span></li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>