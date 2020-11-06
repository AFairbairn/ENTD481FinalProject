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
<a class="inputButton" href="UserManagement?action=ADD">Add User</a>
	<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>User Name</th>
			<th>Email</th>
			<th>Edit/Delete</th>
		</tr>
		<c:forEach items = "${list}" var = "user">
			<tr>
				<td>${user.fname}</td>
				<td>${user.lname}</td>
				<td>${user.username}</td>
				<td><a href="UserManagement?action=EMAIL&email=${user.email}">${user.email}</a></td>
				<td><a href="UserManagement?action=EDIT&id=${user.id}">Edit</a> |
				<a href="UserManagement?action=DELETE&id=${user.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<span>${status}</span>
</div>
</div>
</body>
</html>
