<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Club Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ClubDashboard.css">
</head>
<body>
    <!-- Header with Logo -->
    <div class="header">
        <img src="${pageContext.request.contextPath}/resources/Homeimages/logo.png" alt="TransferHub Logo">
        <h1>Club Dashboard</h1>
        <div class="club-info">
            <div class="avatar">C</div>
            <span>Club</span>
        </div>
    </div>

    <!-- Main Layout -->
    <div class="container">
        <!-- Navigation Bar -->
        <div class="navbar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/clubDashboard" class="active">🏠 Home</a></li>
                <li><a href="${pageContext.request.contextPath}/dashboardplayers">⚽ Players</a></li>
                <li><a href="${pageContext.request.contextPath}/profile">👤 Profile</a></li>
            </ul>
            <form class="logout-form" action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <h2>Welcome to the Transfer Hub Club Dashboard</h2>
            <p>Login successful. You are now logged in as a club.</p>
            
            <!-- Quick Stats Section -->
            <div class="stats-section">
                <div class="stat-card">
                    <h3>20</h3>
                    <p>Squad Players</p>
                </div>
                <div class="stat-card">
                    <h3>3</h3>
                    <p>Pending Offers</p>
                </div>
                <div class="stat-card">
                    <h3>5</h3>
                    <p>Available Transfers</p>
                </div>
            </div>

            <!-- Featured Players Section -->
            <div class="featured-section">
                <h3>Featured Players</h3>
                <div class="player-grid">
                    <div class="player-card">
                        <div class="player-image placeholder"></div>
                        <h4>Player Name</h4>
                        <p>Position: Forward</p>
                        <p>Age: 24</p>
                        <button class="view-btn">View Profile</button>
                    </div>
                    <div class="player-card">
                        <div class="player-image placeholder"></div>
                        <h4>Player Name</h4>
                        <p>Position: Midfielder</p>
                        <p>Age: 27</p>
                        <button class="view-btn">View Profile</button>
                    </div>
                    <div class="player-card">
                        <div class="player-image placeholder"></div>
                        <h4>Player Name</h4>
                        <p>Position: Defender</p>
                        <p>Age: 22</p>
                        <button class="view-btn">View Profile</button>
                    </div>
                </div>
            </div>

            <!-- Recent Activity Section -->
            <div class="activity-section">
                <h3>Recent Activity</h3>
                <ul class="activity-list">
                    <li><span class="activity-icon">📝</span> Transfer request sent for Player X - 2 hours ago</li>
                    <li><span class="activity-icon">✅</span> Offer accepted for Player Y - Yesterday</li>
                    <li><span class="activity-icon">📩</span> New message from Agent Z - 2 days ago</li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>