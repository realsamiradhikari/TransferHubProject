<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Player Management</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminDashboardPlayers.css">
</head>
<body>
    <!-- Header with Logo -->
    <div class="header">
        <img src="logo-placeholder.png" alt="TransferHub Logo">
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
                <li><a href="${pageContext.request.contextPath}/admindashboardclubs">🏟️ Club Management</a></li>
                <li><a href="${pageContext.request.contextPath}/admindashboardplayers" class="active">⚽ Player Management</a></li>
                <li><a href="#">👤 Profile</a></li>
            </ul>
            <form class="logout-form" action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <h2>Player Management</h2>
            <button class="add-new-btn" onclick="toggleForm()">Add New Player</button>

            <!-- Add/Edit Player Form -->
            <div class="player-form-container" id="playerForm">
                <form class="player-form" action="${pageContext.request.contextPath}/admindashboardplayers" method="post">
                    <input type="hidden" name="action" value="${playerToEdit != null ? 'update' : 'insert'}">
                    <div>
                        <label for="playerId">Player ID</label>
                        <input type="number" id="playerId" name="playerId" value="${playerToEdit != null ? playerToEdit.playerId : ''}" 
                               ${playerToEdit != null ? 'readonly' : ''} required>
                    </div>
                    <div>
                        <label for="playerName">Player Name</label>
                        <input type="text" id="playerName" name="playerName" value="${playerToEdit != null ? playerToEdit.playerName : ''}" required>
                    </div>
                    <div>
                        <label for="contractDuration">Contract Duration</label>
                        <input type="number" id="contractDuration" name="contractDuration" 
                               value="${playerToEdit != null ? playerToEdit.contractDuration : ''}" required>
                    </div>
                    <div>
                        <label for="position">Position</label>
                        <select id="position" name="position" required>
                            <option value="" disabled ${playerToEdit == null ? 'selected' : ''}>Position</option>
                            <option value="ST" ${playerToEdit != null && playerToEdit.position == 'ST' ? 'selected' : ''}>ST</option>
                            <option value="SS" ${playerToEdit != null && playerToEdit.position == 'SS' ? 'selected' : ''}>SS</option>
                            <option value="AMF" ${playerToEdit != null && playerToEdit.position == 'AMF' ? 'selected' : ''}>AMF</option>
                            <option value="RWF" ${playerToEdit != null && playerToEdit.position == 'RWF' ? 'selected' : ''}>RWF</option>
                            <option value="LWF" ${playerToEdit != null && playerToEdit.position == 'LWF' ? 'selected' : ''}>LWF</option>
                            <option value="CM" ${playerToEdit != null && playerToEdit.position == 'CM' ? 'selected' : ''}>CM</option>
                            <option value="CDM" ${playerToEdit != null && playerToEdit.position == 'CDM' ? 'selected' : ''}>CDM</option>
                            <option value="LB" ${playerToEdit != null && playerToEdit.position == 'LB' ? 'selected' : ''}>LB</option>
                            <option value="RB" ${playerToEdit != null && playerToEdit.position == 'RB' ? 'selected' : ''}>RB</option>
                            <option value="CB" ${playerToEdit != null && playerToEdit.position == 'CB' ? 'selected' : ''}>CB</option>
                            <option value="GK" ${playerToEdit != null && playerToEdit.position == 'GK' ? 'selected' : ''}>GK</option>
                        </select>
                    </div>
                    <div>
                        <label for="age">Age</label>
                        <input type="number" id="age" name="age" value="${playerToEdit != null ? playerToEdit.age : ''}" required>
                    </div>
                    <div>
                        <label for="marketValue">Market Value</label>
                        <input type="number" id="marketValue" name="marketValue" step="0.01" 
                               value="${playerToEdit != null ? playerToEdit.marketValue : ''}" required>
                    </div>
                    <div>
                        <label for="nationality">Nationality</label>
                        <select id="nationality" name="nationality" required>
                            <option value="" disabled ${playerToEdit == null ? 'selected' : ''}>Country</option>
                            <option value="Argentina" ${playerToEdit != null && playerToEdit.nationality == 'Argentina' ? 'selected' : ''}>Argentina</option>
                            <option value="Brazil" ${playerToEdit != null && playerToEdit.nationality == 'Brazil' ? 'selected' : ''}>Brazil</option>
                            <option value="Portugal" ${playerToEdit != null && playerToEdit.nationality == 'Portugal' ? 'selected' : ''}>Portugal</option>
                            <option value="Spain" ${playerToEdit != null && playerToEdit.nationality == 'Spain' ? 'selected' : ''}>Spain</option>
                            <option value="France" ${playerToEdit != null && playerToEdit.nationality == 'France' ? 'selected' : ''}>France</option>
                            <option value="Germany" ${playerToEdit != null && playerToEdit.nationality == 'Germany' ? 'selected' : ''}>Germany</option>
                            <option value="England" ${playerToEdit != null && playerToEdit.nationality == 'England' ? 'selected' : ''}>England</option>
                            <option value="Italy" ${playerToEdit != null && playerToEdit.nationality == 'Italy' ? 'selected' : ''}>Italy</option>
                            <option value="Netherlands" ${playerToEdit != null && playerToEdit.nationality == 'Netherlands' ? 'selected' : ''}>Netherlands</option>
                            <option value="Belgium" ${playerToEdit != null && playerToEdit.nationality == 'Belgium' ? 'selected' : ''}>Belgium</option>
                        </select>
                    </div>
                    <div>
                        <label for="gAContributions">G/A Contributions</label>
                        <input type="number" id="gAContributions" name="gAContributions" 
                               value="${playerToEdit != null ? playerToEdit.GAContributions : ''}" required>
                    </div>
                    <div>
                        <label for="clubId">Club ID</label>
                        <input type="number" id="clubId" name="clubId" value="0">
                    </div>
                    <button type="submit">${playerToEdit != null ? 'Update Player' : 'Add Player'}</button>
                </form>
            </div>

            <!-- Player Table -->
            <table class="player-table">
                <thead>
                    <tr>
                        <th>PLAYER ID</th>
                        <th>PLAYER NAME</th>
                        <th>POSITION</th>
                        <th>NATIONALITY</th>
                        <th>AGE</th>
                        <th>MARKET VALUE</th>
                        <th>CONTRACT DURATION</th>
                        <th>G/A CONTRIBUTIONS</th>
                        <th>EDIT</th>
                        <th>DELETE</th>
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
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/admindashboardplayers?action=edit&playerId=${player.playerId}">✏️</a>
                            </td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/admindashboardplayers?action=delete&playerId=${player.playerId}" 
                                   onclick="return confirm('Are you sure you want to delete this player?')">🗑️</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script>
        function toggleForm() {
            const form = document.getElementById('playerForm');
            form.classList.toggle('active');
        }

        // Automatically show the form if editing
        window.onload = function() {
            <c:if test="${playerToEdit != null}">
                toggleForm();
            </c:if>
        };
    </script>
</body>
</html>