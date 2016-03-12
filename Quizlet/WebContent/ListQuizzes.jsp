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
	<title>Quizzes </title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="CSS/common.css">
	<link rel="stylesheet" href="CSS/login-formatting.css">
	
</head>
<body>

	<div id=header>

		<ul>
			<li class="name"><a>Quizzler</a></li>
			
			<%
				if (request.getParameter("temp") != null || request.getAttribute("temp") != null) {
					out.println("<li><a href=\"CreateNewAccount.html\">Create Account</a></li>");
				} else {
					out.println("<li><a href=\"HomepageLogin.html\">Logout</a></li>");
					out.println("<li><a href=\"ListQuizzes.jsp\">Quizzes</a></li>");
					out.println("<li><a href=\"HomepageUser.jsp\">Profile</a></li>");
					out.println("<li><a href=\"HistorySummaryPage.jsp\">History</a></li>");
				}
			%>
		</ul>
		<div id="extra-large-inner-header">
	
			<h1>Choose a Quiz to View! </h1>
			<form action="QuizSummaryPage.jsp" method="post">
				<%
				ServletContext context = getServletContext(); 
				DBConnection connect = (DBConnection)(context.getAttribute("Connection"));
				
				Statement stmt = connect.getStatement(); 
				ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes");
				int count = 0;
				while (rs.next()) {
					String name = rs.getString(2);
					if(count == 0){
						out.println("<input type=\"radio\" name=\"quizname\" checked=\"checked\" value=\""+name+"\"> "+name +"<br>");
					}else{
						out.println("<input type=\"radio\" name=\"quizname\" value=\""+name+"\"> "+name +"<br>");
					}
					count++;
				}
				
				request.setAttribute("temp", request.getParameter("temp"));
				%>
			<input type="submit" class="btn btn-primary" value = "View Quiz Summary"/>
			</form>
			
			<%
				if (request.getParameter("temp") == null) {
					out.println("<form action=\"NewQuizForm.html\">");
					out.println("<input type=\"submit\" class=\"btn btn-primary\" value = \"Create New Quiz\"/>");
					out.println("</form>");
				}
			%>
		</div>
	</div>
</body>
</html>
