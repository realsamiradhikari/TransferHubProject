<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Player Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminDashboardPlayers.css">
    <style>
        .error {
            color: red;
            font-size: 0.9em;
            display: block;
            margin-top: 5px;
        }
        .warning {
            background-color: #fff3cd;
            color: #856404;
            border: 1px solid #ffeeba;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 4px;
            display: none;
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
                <li><a href="${pageContext.request.contextPath}/admindashboardclubs">🏟️ Club Management</a></li>
                <li><a href="${pageContext.request.contextPath}/admindashboardplayers" class="active">⚽ Player Management</a></li>
                <li><a href="${pageContext.request.contextPath}/adminprofile">👤 Profile</a></li>
            </ul>
            <form class="logout-form" action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <h2>Player Management</h2>
            <button class="add-new-btn" onclick="resetFormForAdd()">Add New Player</button>

            <!-- Warning Messages -->
            <c:if test="${not empty errors}">
                <div class="warning">
                    <c:forEach var="error" items="${errors}">
                        <p>${error}</p>
                    </c:forEach>
                </div>
            </c:if>

            <!-- Add/Edit Player Form -->
            <div class="player-form-container" id="playerForm">
                <form class="player-form" id="playerFormElement" action="${pageContext.request.contextPath}/admindashboardplayers" method="post">
                    <input type="hidden" id="formAction" name="action" value="${isAdding != null && isAdding ? 'insert' : 'update'}">
                    <div>
                        <label for="playerId">Player ID</label>
                        <input type="number" id="playerId" name="playerId" 
                               value="${formPlayer != null ? formPlayer.playerId : (playerToEdit != null ? playerToEdit.playerId : '')}" 
                               min="1" max="100" placeholder="e.g., 1 (1 to 100)" required>
                    </div>
                    <div>
                        <label for="playerName">Player Name</label>
                        <input type="text" id="playerName" name="playerName" 
                               value="${formPlayer != null ? formPlayer.playerName : (playerToEdit != null ? playerToEdit.playerName : '')}" 
                               pattern=".{2,}" placeholder="e.g., Lionel Messi (at least 2 characters)" required 
                               title="Player Name must be at least 2 characters long">
                    </div>
                    <div>
                        <label for="contractDuration">Contract Duration</label>
                        <input type="number" id="contractDuration" name="contractDuration" 
                               value="${formPlayer != null ? formPlayer.contractDuration : (playerToEdit != null ? playerToEdit.contractDuration : '')}" 
                               min="0" max="72" placeholder="e.g., 24 (Contract Duration in months, 0 to 72)" required 
                               title="Contract Duration must be between 0 and 72 months">
                    </div>
                    <div>
                        <label for="position">Position</label>
                        <select id="position" name="position" required>
                            <option value="" disabled ${formPlayer == null && playerToEdit == null ? 'selected' : ''}>Select Position</option>
                            <option value="ST" ${formPlayer != null && formPlayer.position == 'ST' ? 'selected' : (playerToEdit != null && playerToEdit.position == 'ST' ? 'selected' : '')}>ST</option>
                            <option value="SS" ${formPlayer != null && formPlayer.position == 'SS' ? 'selected' : (playerToEdit != null && playerToEdit.position == 'SS' ? 'selected' : '')}>SS</option>
                            <option value="AMF" ${formPlayer != null && formPlayer.position == 'AMF' ? 'selected' : (playerToEdit != null && playerToEdit.position == 'AMF' ? 'selected' : '')}>AMF</option>
                            <option value="RWF" ${formPlayer != null && formPlayer.position == 'RWF' ? 'selected' : (playerToEdit != null && playerToEdit.position == 'RWF' ? 'selected' : '')}>RWF</option>
                            <option value="LWF" ${formPlayer != null && formPlayer.position == 'LWF' ? 'selected' : (playerToEdit != null && playerToEdit.position == 'LWF' ? 'selected' : '')}>LWF</option>
                            <option value="CM" ${formPlayer != null && formPlayer.position == 'CM' ? 'selected' : (playerToEdit != null && playerToEdit.position == 'CM' ? 'selected' : '')}>CM</option>
                            <option value="CDM" ${formPlayer != null && formPlayer.position == 'CDM' ? 'selected' : (playerToEdit != null && playerToEdit.position == 'CDM' ? 'selected' : '')}>CDM</option>
                            <option value="LB" ${formPlayer != null && formPlayer.position == 'LB' ? 'selected' : (playerToEdit != null && playerToEdit.position == 'LB' ? 'selected' : '')}>LB</option>
                            <option value="RB" ${formPlayer != null && formPlayer.position == 'RB' ? 'selected' : (playerToEdit != null && playerToEdit.position == 'RB' ? 'selected' : '')}>RB</option>
                            <option value="CB" ${formPlayer != null && formPlayer.position == 'CB' ? 'selected' : (playerToEdit != null && playerToEdit.position == 'CB' ? 'selected' : '')}>CB</option>
                            <option value="GK" ${formPlayer != null && formPlayer.position == 'GK' ? 'selected' : (playerToEdit != null && playerToEdit.position == 'GK' ? 'selected' : '')}>GK</option>
                        </select>
                    </div>
                    <div>
                        <label for="age">Age</label>
                        <input type="number" id="age" name="age" 
                               value="${formPlayer != null ? formPlayer.age : (playerToEdit != null ? playerToEdit.age : '')}" 
                               min="15" max="45" placeholder="e.g., 25 (15 to 45 years)" required 
                               title="Age must be between 15 and 45">
                    </div>
                    <div>
                        <label for="marketValue">Market Value</label>
                        <input type="number" id="marketValue" name="marketValue" 
                               value="${formPlayer != null ? formPlayer.marketValue : (playerToEdit != null ? playerToEdit.marketValue : '')}" 
                               step="0.01" min="0" max="50000000" placeholder="e.g., 5000000 (0 to 50M)" required 
                               title="Market Value must be between 0 and 50,000,000">
                    </div>
                    <div>
                        <label for="nationality">Nationality</label>
                        <select id="nationality" name="nationality" required>
                            <option value="" disabled ${formPlayer == null && playerToEdit == null ? 'selected' : ''}>Select Country</option>
                            <option value="Argentina" ${formPlayer != null && formPlayer.nationality == 'Argentina' ? 'selected' : (playerToEdit != null && playerToEdit.nationality == 'Argentina' ? 'selected' : '')}>Argentina</option>
                            <option value="Brazil" ${formPlayer != null && formPlayer.nationality == 'Brazil' ? 'selected' : (playerToEdit != null && playerToEdit.nationality == 'Brazil' ? 'selected' : '')}>Brazil</option>
                            <option value="Portugal" ${formPlayer != null && formPlayer.nationality == 'Portugal' ? 'selected' : (playerToEdit != null && playerToEdit.nationality == 'Portugal' ? 'selected' : '')}>Portugal</option>
                            <option value="Spain" ${formPlayer != null && formPlayer.nationality == 'Spain' ? 'selected' : (playerToEdit != null && playerToEdit.nationality == 'Spain' ? 'selected' : '')}>Spain</option>
                            <option value="France" ${formPlayer != null && formPlayer.nationality == 'France' ? 'selected' : (playerToEdit != null && playerToEdit.nationality == 'France' ? 'selected' : '')}>France</option>
                            <option value="Germany" ${formPlayer != null && formPlayer.nationality == 'Germany' ? 'selected' : (playerToEdit != null && playerToEdit.nationality == 'Germany' ? 'selected' : '')}>Germany</option>
                            <option value="England" ${formPlayer != null && formPlayer.nationality == 'England' ? 'selected' : (playerToEdit != null && playerToEdit.nationality == 'England' ? 'selected' : '')}>England</option>
                            <option value="Italy" ${formPlayer != null && formPlayer.nationality == 'Italy' ? 'selected' : (playerToEdit != null && playerToEdit.nationality == 'Italy' ? 'selected' : '')}>Italy</option>
                            <option value="Netherlands" ${formPlayer != null && formPlayer.nationality == 'Netherlands' ? 'selected' : (playerToEdit != null && playerToEdit.nationality == 'Netherlands' ? 'selected' : '')}>Netherlands</option>
                            <option value="Belgium" ${formPlayer != null && formPlayer.nationality == 'Belgium' ? 'selected' : (playerToEdit != null && playerToEdit.nationality == 'Belgium' ? 'selected' : '')}>Belgium</option>
                        </select>
                    </div>
                    <div>
                        <label for="gAContributions">G/A Contributions</label>
                        <input type="number" id="gAContributions" name="gAContributions" 
                               value="${formPlayer != null ? formPlayer.GAContributions : (playerToEdit != null ? playerToEdit.GAContributions : '')}" 
                               min="0" max="1000" placeholder="e.g., 10 (0 to 1000)" required 
                               title="G/A Contributions must be between 0 and 1000">
                    </div>
                    <div>
                        <label for="clubId">Club ID</label>
                        <input type="number" id="clubId" name="clubId" 
                               value="${formPlayer != null ? formPlayer.clubId : (playerToEdit != null ? playerToEdit.clubId : '0')}" 
                               min="0" placeholder="e.g., 1 (0 if no club)" 
                               title="Club ID must be a positive number or zero">
                    </div>
                    <c:choose>
                        <c:when test="${playerToEdit != null}">
                            <button type="submit">Update Player</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit">Add Player</button>
                        </c:otherwise>
                    </c:choose>
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

        function resetFormForAdd() {
            const form = document.getElementById('playerFormElement');
            form.reset(); // Clear all form fields
            document.getElementById('formAction').value = 'insert'; // Set action to insert
            toggleForm(); // Show the form
        }

        // Safely handle form toggle based on server-side conditions
        window.onload = function() {
            const shouldToggle = ${playerToEdit != null || formPlayer != null || not empty errors ? 'true' : 'false'};
            if (shouldToggle) {
                toggleForm();
            }
            const warning = document.querySelector('.warning');
            if (warning && warning.children.length > 0) {
                warning.style.display = 'block';
            }
        };
    </script>
</body>
</html>