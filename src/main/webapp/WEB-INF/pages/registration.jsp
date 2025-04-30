<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TransferHub - Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>
    <div class="container">
        <div class="logo-container">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/resources/Homeimages/logo.png" alt="TransferHub Logo">
            </div>
            <h1 class="company-name">TransferHub</h1>
        </div>
        
        <div class="barcelona-stripe"></div>
        
        <c:if test="${not empty error}">
            <div class="general-error">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <c:choose>
                    <c:when test="${empty errors.clubid}">
                        <label for="clubid">Club Username</label>
                    </c:when>
                    <c:otherwise>
                        <label for="clubid" class="error">Club Username</label>
                    </c:otherwise>
                </c:choose>
                <div class="input-icon">
                    <i>🏢</i>
                    <input type="text" id="clubid" name="clubid" placeholder="Enter your club username" 
                           value="<c:out value='${param.clubid}' default='' />" required>
                </div>
                <c:if test="${not empty errors.clubid}">
                    <small class="error">${errors.clubid}</small>
                </c:if>
            </div>
            
            <div class="form-group">
                <c:choose>
                    <c:when test="${empty errors.email}">
                        <label for="email">Club Email</label>
                    </c:when>
                    <c:otherwise>
                        <label for="email" class="error">Club Email</label>
                    </c:otherwise>
                </c:choose>
                <div class="input-icon">
                    <i>✉️</i>
                    <input type="email" id="email" name="email" placeholder="Enter your club email" 
                           value="<c:out value='${param.email}' default='' />" required>
                </div>
                <c:if test="${not empty errors.email}">
                    <small class="error">${errors.email}</small>
                </c:if>
            </div>
            
            <div class="form-group">
                <c:choose>
                    <c:when test="${empty errors.password}">
                        <label for="password">Password</label>
                    </c:when>
                    <c:otherwise>
                        <label for="password" class="error">Password</label>
                    </c:otherwise>
                </c:choose>
                <div class="input-icon">
                    <i>🔒</i>
                    <input type="password" id="password" name="password" placeholder="Create a password" required>
                </div>
                <c:if test="${not empty errors.password}">
                    <small class="error">${errors.password}</small>
                </c:if>
            </div>
            
            <button type="submit">Register</button>
        </form>
        
        <div class="barcelona-stripe"></div>
        
        <div class="footer">
            Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a>
        </div>
    </div>
</body>
</html>