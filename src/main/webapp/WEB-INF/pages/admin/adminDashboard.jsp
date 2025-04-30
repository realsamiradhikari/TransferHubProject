<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Home</title>
    <style>
        @charset "UTF-8";

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            background-color: #1A2526;
            color: #FFFFFF;
        }

        /* Header with Logo and Title */
        .header {
            background: linear-gradient(90deg, #004D98, #A50044);
            padding: 15px 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .header img {
            height: 50px;
        }

        .header h1 {
            font-size: 24px;
        }

        .header .admin-info {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .header .admin-info .avatar {
            background-color: #A50044;
            color: #FFFFFF;
            width: 35px;
            height: 35px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: bold;
        }

        /* Layout */
        .container {
            display: flex;
            height: calc(100vh - 80px);
        }

        /* Navigation Bar */
        .navbar {
            width: 300px;
            background-color: #0F1A1C;
            padding: 20px;
            border-right: 2px solid #A50044;
        }

        .navbar ul {
            list-style: none;
        }

        .navbar ul li {
            margin-bottom: 20px;
        }

        .navbar ul li a {
            color: #FFFFFF;
            text-decoration: none;
            font-size: 16px;
            display: flex;
            align-items: center;
            gap: 10px;
            padding: 10px;
            border-radius: 5px;
        }

        .navbar ul li a:hover {
            background-color: #A50044;
        }

        .navbar ul li a.active {
            background-color: #004D98;
        }

        /* Logout Button */
        .navbar .logout-form {
            margin-top: 20px;
        }

        .navbar .logout-form button {
            width: 100%;
            padding: 10px;
            background-color: #A50044;
            color: #FFFFFF;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .navbar .logout-form button:hover {
            background-color: #8B0037;
        }

        /* Main Content */
        .main-content {
            flex: 1;
            padding: 20px;
            overflow-y: auto;
        }

        .main-content h2 {
            margin-bottom: 20px;
            color: #FFFFFF;
        }

        /* Dashboard Cards */
        .dashboard-cards {
            display: flex;
            gap: 20px;
            margin-bottom: 30px;
        }

        .card {
            background-color: #2A3439;
            padding: 20px;
            border-radius: 8px;
            flex: 1;
            text-align: center;
        }

        .card h3 {
            margin-bottom: 10px;
            color: #A50044;
        }

        .card p {
            font-size: 24px;
            color: #FFFFFF;
        }

        /* Placeholder Chart */
        .chart-placeholder {
            background-color: #2A3439;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }

        .chart-placeholder h3 {
            margin-bottom: 15px;
            color: #A50044;
        }

        .chart-placeholder .placeholder-box {
            height: 200px;
            background: linear-gradient(90deg, #004D98, #A50044);
            border-radius: 5px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #FFFFFF;
            font-size: 16px;
        }

        /* Top Players List */
        .top-players {
            background-color: #2A3439;
            padding: 20px;
            border-radius: 8px;
        }

        .top-players h3 {
            margin-bottom: 15px;
            color: #A50044;
        }

        .top-players ul {
            list-style: none;
        }

        .top-players ul li {
            padding: 10px 0;
            border-bottom: 1px solid #1F292A;
            display: flex;
            justify-content: space-between;
        }

        .top-players ul li:last-child {
            border-bottom: none;
        }
    </style>
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
                <li><a href="#">👤 Profile</a></li>
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
                    <p>25</p>
                </div>
                <div class="card">
                    <h3>Total Players</h3>
                    <p>450</p>
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