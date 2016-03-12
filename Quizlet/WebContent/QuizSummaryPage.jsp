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

String quizstr = "";
String username = "";
ScoreBoard board = null; 
String name = request.getParameter("quizname");
String currentuser = "";


if (request.getAttribute("temp") == null) {
	ServletContext context = getServletContext(); 
	DBConnection connect = (DBConnection)(context.getAttribute("Connection"));

	currentuser = (String)session.getAttribute("user");
	Statement stmt = connect.getStatement();


	try {
		ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes");
		
		while(rs.next()) {
			String quizname = rs.getString(2);

			if (quizname.equals(name)) {
				long taken = rs.getLong(3); 
				username = rs.getString(1);
				Blob quizblob = rs.getBlob(5);
				Blob boardblob = rs.getBlob(7);
				
				board = new ScoreBoard(boardblob);
				
				byte[] bdata = quizblob.getBytes(1, (int)quizblob.length());
				quizstr = new String(bdata);			
				break; 
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}




	JSONParser parser = new JSONParser(); 

	Quiz quiz =null; 

	try {
	Object obj = parser.parse(quizstr);
	JSONObject jobj = (JSONObject)obj;
	quiz = JSONCreator.getQuiz(jobj);
	
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	session.setAttribute("quiz", quiz);


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Summary: <%=quiz.getName() %></title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="CSS/common.css">
	<link rel="stylesheet" href="CSS/login-formatting.css">
	<link rel="stylesheet" href="CSS/tableFormatting.css">
	
</head>
<body>
	<div id=header>
		
		<ul>
			<li class="name"><a>Quizzler</a></li>
		
			<%
			if (request.getParameter("temp") != null) {	
				out.println("<li><a href=\"CreateNewAccount.html\">Create Account</a></li>");
				request.setAttribute("temp", "true");


			} else {
				out.println("<li><a href=\"HomepageLogin.html\">Logout</a></li>");
				out.println("<li><a href=\"ListQuizzes.jsp\">Quizzes</a></li>");
				out.println("<li><a href=\"HomepageUser.jsp\">Profile</a></li>");
				out.println("<li><a href=\"HistorySummaryPage.jsp\">History</a></li>");
			}
			%>
		</ul>
		<div id="extra-large-inner-header">

			<h1>Summary: <%=quiz.getName() %></h1>
			<div>
			<%
			

 			if (request.getAttribute("error") != null) {
 				out.println(request.getAttribute("error"));
 			}
 			
			out.println("<p>Quiz Name: "+quiz.getName()+ "</p>");
			out.println("<p>Quiz Description: "+quiz.getDescription()+"</p>");
			
			out.println("<p><strong>Creator:</strong> "+"<a href =\"/Quizlet/SearchUserServlet?user=" + username + "\">" + username +"</a></p>");
			%>
			
			<% 
			
			if (request.getParameter("temp") == null) {					
				out.println("<form action=\"TakeQuizServlet\" method=\"post\">");
				out.println("<input type=\"submit\" class=\"btn btn-primary\" value = \"Take Quiz\"/>");
				out.println("</form>");
			}

			%> 	
			
			<%
			
			if (request.getParameter("temp") == null) {					
				out.println("<p><strong>Your Past Performance:</strong> </p>");
				out.write("<table>");
				out.write("<tr>");
				out.write("<th>Date Taken</th><th>Score</th><th>Time</th>");
				out.write("<tr>");
				int count = 0;
				for (Score sc : board.getUsers()) {
					if (sc.user.equals(currentuser)){
						Date dt = new Date(sc.timetaken);
						out.println("<tr>");
						out.println("<td>"+ dt.toString()+"</td><td>"+sc.score+"</td><td>"+sc.timescore+"</td>");
						out.println("</tr>");
						count++;
					}
				}
				out.write("</table>");
				if(count==0)out.write("<p>None</p>");
				out.write("</div>");
			}
			
	
			out.write("<div>");
			out.println("<h2>Top Performers of all time:</strong> </h2>");

			ArrayList<Score> top = board.getTopPerformers();
			out.write("<table>");
			out.write("<tr>");
			out.write("<th>User</th><th>Date Taken</th><th>Score</th><th>Time</th>");
			out.write("<tr>");
			for (Score sc : top) {
				String userInTable;
				User performer = ((AccountManager)session.getAttribute("am")).getAccount(sc.user);
				if (performer.privacyOn()) {
					userInTable = "Anonymous";
				} else {
					userInTable = sc.user;
				}
				out.write("<tr>");
				Date dt = new Date(sc.timetaken);
				out.println("<td>"+userInTable +"</td><td>"+ dt.toString()+"</td><td>"+sc.score+"</td><td>"+sc.timescore+"</td>");
				out.write("</tr>");
			}
			out.write("</table>");
			if(top.size()==0)out.write("<p>None</p>");
			out.write("</div>");
			
			out.write("<div>");
			out.println("<h2>Top Performers in last 15 minutes:</strong> </h2>");
			ArrayList<Score> recent = board.getTopRecentPerformers();
			out.write("<table>");
			out.write("<tr>");
			out.write("<th>User</th><th>Date Taken</th><th>Score</th><th>Time</th>");
			out.write("<tr>");
			for (Score sc : recent) {
				String userInTable;
				User performer = ((AccountManager)session.getAttribute("am")).getAccount(sc.user);
				if (performer.privacyOn()) {
					userInTable = "Anonymous";
				} else {
					userInTable = sc.user;
				}
				out.write("<tr>");
				Date dt = new Date(sc.timetaken);
				out.println("<td>"+userInTable +"</td><td>"+ dt.toString()+"</td><td>"+sc.score+"</td><td>"+sc.timescore+"</td>");
				out.write("</tr>");
			}
			out.write("</table>");
			if(recent.size()==0)out.write("<p>None</p>");
			out.write("</div>");

			
			out.write("<div>");
			out.println("<h2>All recent test takers(last 15 minutes):</strong> </h2>");
			out.write("<table>");
			out.write("<tr>");
			out.write("<th>User</th><th>Date Taken</th><th>Score</th><th>Time</th>");
			out.write("<tr>");
			ArrayList<Score> recentall = board.getRecentPerformers();
			for (Score sc : recentall) {
				String userInTable;
				User performer = ((AccountManager)session.getAttribute("am")).getAccount(sc.user);
				if (performer.privacyOn()) {
					userInTable = "Anonymous";
				} else {
					userInTable = sc.user;
				}
				out.write("<tr>");
				Date dt = new Date(sc.timetaken);
				out.println("<td>"+userInTable +"</td><td>"+ dt.toString()+"</td><td>"+sc.score+"</td><td>"+sc.timescore+"</td>");
				out.write("</tr>");
			}
			out.write("</table>");
			if(recentall.size()==0)out.write("<p>None</p>");
			out.write("</div>");
			
			out.write("<div>");
			out.write("<table>");
			out.write("<tr>");
			out.write("<th>User</th><th>Date Taken</th><th>Score</th><th>Time</th>");
			out.write("<tr>");
			out.println("<h2>All test takers:</strong> </h2>");
			for (Score sc : board.getUsers()) {
				String userInTable;
				User performer = ((AccountManager)session.getAttribute("am")).getAccount(sc.user);
				if (performer.privacyOn()) {
					userInTable = "Anonymous";
				} else {
					userInTable = sc.user;
				}
				out.write("<tr>");
				Date dt = new Date(sc.timetaken);
				out.println("<td>"+userInTable +"</td><td>"+ dt.toString()+"</td><td>"+sc.score+"</td><td>"+sc.timescore+"</td>");
				out.write("</tr>");
			}
			out.write("</table>");
			if(board.getUsers().size()==0)out.write("<p>None</p>");
			out.write("</div>");
			
			
			double totalScore = 0;
			double totalTime = 0;
			for (Score sc : board.getUsers()) {
				totalTime += sc.timescore;
				totalScore += sc.score;	
			}
			double averageScore = totalScore/board.getUsers().size();
			double averageTime = totalTime/board.getUsers().size();
			out.write("<h2>Average Score: " + ((double)((int)(averageScore*100)))/100 + " Average Time: " + ((double)((int)(averageTime*100)))/100 + " s</h2>");

			
			if (request.getParameter("temp") == null && username.equals(currentuser)) {
				out.println("<form action=\"DisplayQuiz.jsp\" method=\"post\">"); 
				out.println("<input type=\"submit\" class=\"btn btn-primary\" value = \"Edit Quiz\"/>");
				out.println("</form>"); 
			} 
			
			%>
			
			<div>
			
			<% 
			
			if (request.getParameter("temp") == null) {					
				out.println("<form action=\"TakeQuizServlet\" method=\"post\">");
				out.println("<input type=\"submit\" class=\"btn btn-primary\" value = \"Take Quiz\"/>");
				out.println("</form>");
			}

			%> 				
				<form action="ListQuizzes.jsp" method="post">
				<%
				if (request.getParameter("temp") != null || request.getAttribute("temp") != null) {					
					out.println("<input type=\"hidden\" value=\"true\" name=\"temp\"/>");
					request.setAttribute("temp", true);
				}
				%>
				  <input type="submit" class="btn btn-primary" value = "Back"/>
				</form>
			</div>
			

	 	</div>
	</div>
</div>
</body>
</html>