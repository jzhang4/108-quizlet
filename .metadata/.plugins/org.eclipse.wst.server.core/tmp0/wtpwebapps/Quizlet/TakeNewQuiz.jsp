 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "quiz.Quiz"%>
<%@ page import= "quiz.Question"%>
<%@ page import= "user.DBConnection"%>
<%@ page import= "java.util.*"%>    
<%@ page import= "java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Take New Quiz</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="CSS/common.css">
	<link rel="stylesheet" href="CSS/login-formatting.css">
	
</head>
<body>

	<div id=header>

		<ul>
			<li class="name"><a>Quizzler</a></li>
			<li><a href="HomepageLogin.html">Logout</a></li>
			<li><a href="TakeNewQuiz.jsp">Quizzes</a></li>
			<li><a>Profile</a></li>
		</ul>
		<div id="extra-large-inner-header">
	
	
			<h1>Choose a Quiz to Take! </h1>
			<form action="TakeDataQuizServlet" method="post">
			<%
			ServletContext context = getServletContext(); 
			DBConnection connect = (DBConnection)(context.getAttribute("Connection"));
			
			Statement stmt = connect.getStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes");
			
			while (rs.next()) {
				String name = rs.getString(2);
				out.println("<input type=\"radio\" name=\"quizname\" value=\""+name+"\"> "+name +"<br>");
			}
			
			%>
			<input type="submit" class="btn btn-primary" value = "Take quiz"/>
			</form>
		</div>
	</div>
</body>
</html>
