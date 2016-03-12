<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "quiz.Quiz"%>
<%@ page import= "quiz.Question"%>
<%@ page import= "quiz.MAQuestion"%>
<%@ page import= "java.util.*"%>
<%@ page import= "java.util.Date"%>
<%@ page import= "user.*"%>

<%@ page import= "java.util.*"%>    
<%@ page import= "java.sql.*"%>
<%@ page import= "org.json.simple.parser.JSONParser"%>
<%@ page import= "org.json.simple.JSONObject"%>
<%@ page import= "quiz.JSONCreator" %>
<%@ page import= "quiz.ScoreBoard" %>
<%@ page import= "quiz.ScoreBoard.Score" %>
<%@ page import= "org.json.simple.parser.ParseException" %>

<%
ServletContext context = getServletContext(); 
DBConnection connect = (DBConnection)(context.getAttribute("Connection"));

String currentuser = (String)session.getAttribute("user");

Statement stmt = connect.getStatement();

ScoreBoard board = null; 



%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><%=currentuser %>'s Quiz History </title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="CSS/common.css">
	<link rel="stylesheet" href="CSS/login-formatting.css">
	<link rel="stylesheet" href="CSS/tableFormatting.css">
	
</head>
<body>
	<div id=header>

		<ul>
			<li class="name"><a>Quizzler</a></li>
			<li><a href="HomepageLogin.html">Logout</a></li>
			<li><a href="ListQuizzes.jsp">Quizzes</a></li>
			<li><a href="/Quizlet/HomepageUser.jsp">Profile</a></li>
			<li><a href="HistorySummaryPage.jsp">History</a></li>
		</ul>
		<div id="extra-large-inner-header">

			<h1><%=currentuser %>'s Quiz History </h1>
			<div>
			<%
			

 			if (request.getAttribute("error") != null) {
 				out.println(request.getAttribute("error"));
 			}
 			
			%>
			
			<%
			out.println("<p><strong>Your Past Performance:</strong> </p>");
			out.write("<table>");
			out.write("<tr>");
			out.write("<th>Quiz Name</th><th>Date Taken</th><th>Score</th><th>Time</th>");
			out.write("<tr>");
			
			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes");
				
				while(rs.next()) {
					String quizname = rs.getString(2);

					Blob boardblob = rs.getBlob(7);
					
					board = new ScoreBoard(boardblob);
					for (Score sc : board.getUsers()) {
						if (sc.user.equals(currentuser)){
							Date dt = new Date(sc.timetaken);
							out.println("<tr>");
							out.println("<td>"+ quizname+"</td><td>"+ dt.toString()+"</td><td>"+sc.score+"</td><td>"+sc.timescore+"</td>");
							out.println("</tr>");
						}
					}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			out.write("</table>");
			out.write("</div>");
	
			%> 
			
			<div>
				
				<form action="HomepageUser.jsp" method="post">
				  <input type="submit" class="btn btn-primary" value = "Back"/>
				</form>
				
			</div>
			

	 	</div>
	</div>
</div>
</body>
</html>