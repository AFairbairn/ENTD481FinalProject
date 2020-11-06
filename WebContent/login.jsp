<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href= "<%=request.getContextPath()%>/style.css">
<title>ENTD481 Final Project</title>
</head>
<body>
<div class="container">
<div class="loginStyle">
<h1>Login</h1>
    <br/>
    <form action="login" method="post">
    	<label for="username">Username</label>
        <input type="text" name="username"/><br/><br/>
        <label for="password">Password</label>
        <input type="password" name="password"/><br/><br/>
    <input class="inputButton" type="submit" value="Login"/>
    <br>${message}
    <br>
</form>
</div>
</div>
</body>
</html>
