<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Club Dashboard - Players</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ClubDashboardPlayers.css">
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
                <li><a href="${pageContext.request.contextPath}/dashboard">🏠 Home</a></li>
                <li><a href="${pageContext.request.contextPath}/dashboardplayers" class="active">⚽ Players</a></li>
                <li><a href="${pageContext.request.contextPath}/profile">👤 Profile</a></li>
            </ul>
            <form class="logout-form" action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <h2>Players</h2>
            <div class="search-bar">
                <form action="${pageContext.request.contextPath}/dashboardplayers" method="get">
                    <input type="text" name="searchName" value="${searchName}" placeholder="Search by player name...">
                    <button type="submit">Search</button>
                </form>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>Player ID</th>
                        <th>Player Name</th>
                        <th>Position</th>
                        <th>Nationality</th>
                        <th>Age</th>
                        <th>Market Value</th>
                        <th>Contract Duration</th>
                        <th>G/A Contributions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="player" items="${playerList}">
                        <tr>
                            <td>${player.playerId}</td>
                            <td>${player.playerName}</td>
                            <td>${player.position}</td>
                            <td>${player.nationality}</td>
                            <td>${player.age}</td>
                            <td>${player.marketValue}</td>
                            <td>${player.contractDuration}</td>
                            <td>${player.GAContributions}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>