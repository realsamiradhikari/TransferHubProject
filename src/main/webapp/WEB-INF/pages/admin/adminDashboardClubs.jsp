<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Club Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminDashboardClubs.css">    
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
                <li><a href="${pageContext.request.contextPath}/adminprofile">👤 Profile</a></li>
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
                        <th>CLUB ID</th>
                        <th>CLUB NAME</th>
                        <th>CLUB EMAIL</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="club" items="${clubList}">
                        <tr>
                            <td>${club.clubId}</td>
                            <td>${club.clubUsername}</td>
                            <td>${club.clubEmailId}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>