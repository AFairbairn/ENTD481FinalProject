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
<title>User Management</title>
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

<div>
<h1>${type}</h1>
    <br/>
    <form class="formStyle" action="AddUser" method="post">
    	<label for="fname">First Name</label>
        <input type="text" name="fname" value="${user.fname}"/><br/><br/>
        
        <label for="lname">Last Name</label>
        <input type="text" name="lname" value="${user.lname}"/><br/><br/>
        
        <label for="email">Email</label>
        <input type="email" name="email" value="${user.email}"/><br/><br/>
        
        <label for="username">Username</label>
        <input type="text" name="username" value="${user.username}"/><br/><br/>
        
        <label for="password">Password</label>
        <input type="password" name="password" value="${user.password}"/><br/><br/>
        
		
		<input type="hidden" name="id" value="${user.id}"/>
    	<input type="submit" value="${submitType}"/>
    	<br>${message}
        <br><br>
	</form>
</div>
	
</div>
</div>
</body>
</html>
