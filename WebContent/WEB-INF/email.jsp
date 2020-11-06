<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
int timeout = session.getMaxInactiveInterval();
response.setHeader("Refresh", timeout + "; URL = Logout");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<script src="time.js"></script>
<title>Send Email</title>
</head>
<body>

<header>
<div>
<nav>
	<div class="topBar">
	<input type="hidden" name="sessionTime" value="<%=timeout%>" />
	<span class="title">Welcome ${sessionScope.auth.fname}</span>
	<div class="countDown"><span>Session expires in: </span><span id="session"></span></div>
	</div>
	<div class="nav">
		<ul>
			<li><a href="UserManagement">User Management</a></li>
			<li><a href="UserManagement?action=EMAIL">Send Email</a></li>
			<li><a href="Profile">Profile</a></li>
			<li><a href="Logout">Logout</a></li>
		</ul>
	</div>
</nav>
</div>
</header>
<div>
<div class="container">
<h1>Email</h1>
    <br/>
	<form class="formStyle" method="POST" action="Email">
    <!--Inputs for customer data -->
    <label for="recipient">Recipient: </label>
    <input type="text" name="recipient" id="recipient" value="${email}"><br><br>

    <label for="subject">Subject: </label>
    <input type="text" name="subject" id="subject"><br><br>
	
	<div class="no-inline">
	    <label for="message">Message: </label>
    	<textarea class="boxsizingBorder" name="message" rows="25" cols="100" id="message"></textarea>

    	<input type="submit" value="Send">
    	<input type="hidden" name="sessionEmail" value="${sessionScope.auth.email}"/>
	</div>
	<br>${message}
    
</form>
</div>
</div>
</body>
</html>
