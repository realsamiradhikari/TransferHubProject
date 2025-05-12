<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Profile</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminProfile.css">
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
                <li><a href="${pageContext.request.contextPath}/admindashboardclubs">👥 Club Management</a></li>
                <li><a href="${pageContext.request.contextPath}/admindashboardplayers">⚽ Player Management</a></li>
                <li><a href="${pageContext.request.contextPath}/adminprofile" class="active">👤 Profile</a></li>
            </ul>
            <form class="logout-form" action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <h2>Profile</h2>
            <table>
                <tr>
                    <th>ID</th>
                    <td>${clubId != -1 ? clubId : 'N/A'}</td>
                </tr>
                <tr>
                    <th>Username</th>
                    <td>${clubUsername != null ? clubUsername : 'N/A'}</td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>${clubEmail != null ? clubEmail : 'N/A'}</td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>