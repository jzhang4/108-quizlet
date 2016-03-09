 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "quiz.Quiz"%>
<%@ page import= "quiz.Question"%>
<%@ page import= "quiz.DBConnection"%>
<%@ page import= "java.util.*"%>    
<%@ page import= "java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Take New Quiz</title>
</head>
<body>
<h1>Choose new quiz to take: </h1>
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
<input type="submit" value = "Take quiz"/>
</form>

</body>
</html>