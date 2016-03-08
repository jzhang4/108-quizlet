<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sign Up - Quizzler</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="CSS/common.css">
	<link rel="stylesheet" href="CSS/nav-bar.css">
</head>
<body>

	<div id=header>
		<ul>
		  <li class="name"><a href="index.jsp">Quizzler</a></li>
		  <li><a href="TheTeam.html">Meet the Team</a></li>
		  <li><a href="#contact">Admin Login</a></li>
		  <li><a href="HomepageLogin.html">User Login</a></li>
		</ul>
		<form action="CreateAccountServlet" method="post">
		<div id="innerHeaderLarge">
			<h1>Create a New Account:</h1>
			<h3>The name <%= request.getAttribute("user") %> is already in use. Please try again.</h3>
			<div>
				<h2>Username: <input type="text" name="user" /></h2>
				<h2>Password: <input type="text" name="password" /></h2>
			</div>
			<input type="submit" class="btn btn-primary" value="Create Account"/>
			<h3>Already have an account? <a href="HomepageLogin.html" class="sign_up">Login Here!</a>
			</h3>
		</div>
		</form>
	</div>


</body>
</html>