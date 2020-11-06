<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
int timeout = session.getMaxInactiveInterval();
response.setHeader("Refresh", timeout + "; URL = Logout");
%>
<jsp:useBean id="user" scope="page" class="entd481.app.database.User"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<script src="time.js"></script>
<title>It's a Bird! It's a Plane! It's a Bean!</title>
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
	<h1>Profile</h1>
	    <br/>
	<jsp:setProperty name="user" property="fname" value="This"/>
	<jsp:setProperty name="user" property="lname" value="is just"/>
	<jsp:setProperty name="user" property="email" value="a bean test."/>
	<h3>First Name: </h3><jsp:getProperty name="user" property="fname"/>
	<h3>Last Name: </h3><jsp:getProperty name="user" property="lname"/>
	<h3>Email: </h3><jsp:getProperty name="user" property="email"/>
</div>
</div>
</body>
</html>