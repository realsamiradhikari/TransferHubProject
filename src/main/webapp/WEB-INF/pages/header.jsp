<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />
</head>
<body>
<!-- Main Navigation -->
    <nav class="main-nav">
        <img src="${pageContext.request.contextPath}/resources/Homeimages/logo.png" alt="TransferHub Logo" class="logo">
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/contactus">Contact us</a></li>
            <li><a href="${pageContext.request.contextPath}/aboutus">About us</a></li>
        </ul>
        <div class="header-actions">
    		<a href="${pageContext.request.contextPath}/login" class="barcelona-login-btn">Login</a>
    		<div class="search-icon">🔍</div>
  		</div>
    </nav>
</body>
</html>