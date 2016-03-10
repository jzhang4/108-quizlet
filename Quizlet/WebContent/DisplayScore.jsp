<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Score</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="CSS/common.css">
	<link rel="stylesheet" href="CSS/login-formatting.css">
</head>
<body>
	<div id=header>

		<ul>
			<li class="name"><a>Quizzler</a></li>
			<li><a href="/Quizlet/LogoutServlet">Logout</a></li>
			<li><a href="TakeNewQuiz.jsp">Quizzes</a></li>
			<li><a>Profile</a></li>
		</ul>
		<div id="extra-large-inner-header">
			<h1>Score: <%= session.getAttribute("score")%></h1>
			<%
			long newtime = System.currentTimeMillis(); 
			long time = newtime - (long)session.getAttribute("time");
			time /= 1000; 
			%>
			
			<h1>Time: <%= time%> seconds</h1>
			<form action="DisplayAnswersServlet" method="post">
				<input type="submit" class="btn btn-primary" value = "Get Answers"/>
			</form>
		</div>
	</div>
</body>
</html>