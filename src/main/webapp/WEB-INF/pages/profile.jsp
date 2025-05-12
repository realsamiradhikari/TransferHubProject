<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Club Dashboard - Profile</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ClubProfile.css">
</head>
<body>
    <div class="header">
        <img src="${pageContext.request.contextPath}/resources/Homeimages/logo.png" alt="TransferHub Logo">
        <h1>Club Dashboard</h1>
        <div class="club-info">
            <div class="avatar">C</div>
            <span>Club</span>
        </div>
    </div>

    <div class="container">
        <div class="navbar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/dashboard">🏠 Home</a></li>
                <li><a href="${pageContext.request.contextPath}/dashboardplayers">⚽ Players</a></li>
                <li><a href="${pageContext.request.contextPath}/profile" class="active">👤 Profile</a></li>
            </ul>
            <form class="logout-form" action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>

        <div class="main-content">
            <h2>Profile</h2>
            <table>
                <tr>
                    <th>Club ID</th>
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
            <div class="update-form">
                <form action="${pageContext.request.contextPath}/profile" method="post">
                    <input type="text" name="newUsername" placeholder="Enter new username" required>
                    <button type="submit">Update Username</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>