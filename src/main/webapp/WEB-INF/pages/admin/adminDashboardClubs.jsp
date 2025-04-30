<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Club Management</title>
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

        /* Table Styling */
        .club-table {
            width: 100%;
            border-collapse: collapse;
            background-color: #2A3439;
            border-radius: 8px;
            overflow: hidden;
        }

        .club-table th, .club-table td {
            padding: 12px 15px;
            text-align: left;
        }

        .club-table th {
            background-color: #004D98;
            color: #FFFFFF;
            font-weight: bold;
        }

        .club-table td {
            color: #D3D3D3;
        }

        .club-table tr:nth-child(even) {
            background-color: #1F292A;
        }

        .club-table .status {
            padding: 5px 10px;
            border-radius: 15px;
            font-size: 12px;
            color: #FFFFFF;
            background-color: #28A745;
        }

        .club-table .actions a {
            margin-right: 10px;
            text-decoration: none;
            color: #A50044;
        }

        .club-table .actions a:hover {
            color: #FFFFFF;
        }

        /* Add New Button */
        .add-new-btn {
            background-color: #004D98;
            color: #FFFFFF;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            float: right;
            margin-bottom: 20px;
        }

        .add-new-btn:hover {
            background-color: #003A6F;
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
                <li><a href="${pageContext.request.contextPath}/admindashboard">🏠 Home</a></li>
                <li><a href="#" class="active">👥 Club Management</a></li>
                <li><a href="${pageContext.request.contextPath}/admindashboardplayers">⚽ Player Management</a></li>
                <li><a href="#">👤 Profile</a></li>
            </ul>
            <form class="logout-form" action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <h2>Club Management</h2>
            <button class="add-new-btn">Add New Club</button>
            <table class="club-table">
                <thead>
                    <tr>
                        <th>CLUB NAME</th>
                        <th>CLUB EMAIL</th>
                        <th>STATUS</th>
                        <th>ACTIONS</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>FC Barcelona</td>
                        <td>fcbarcelona@example.com</td>
                        <td><span class="status">Active</span></td>
                        <td class="actions">
                            <a href="#">✏️</a>
                            <a href="#">🗑️</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Real Madrid</td>
                        <td>realmadrid@example.com</td>
                        <td><span class="status">Active</span></td>
                        <td class="actions">
                            <a href="#">✏️</a>
                            <a href="#">🗑️</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Manchester United</td>
                        <td>manutd@example.com</td>
                        <td><span class="status">Active</span></td>
                        <td class="actions">
                            <a href="#">✏️</a>
                            <a href="#">🗑️</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>