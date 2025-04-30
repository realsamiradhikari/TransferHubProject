<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Club Dashboard</title>
</head>
<body>
    <h1>Welcome to the Transfer Hub Club Dashboard</h1>
    <p>Login successful. You are now logged in as a club.</p>

    <p>This is a placeholder dashboard. From here, clubs will be able to:</p>
    <ul>
        <li>View available players</li>
        <li>Request transfers</li>
        <li>Manage their squad</li>
        <li>View pending offers</li>
    </ul>
   <form action="${pageContext.request.contextPath}/logout" method="post">
   			<button type="submit">Logout</button>
	</form>
</body>
</html>
