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

String username = "";

String name = request.getParameter("quizname");

Statement stmt = connect.getStatement();
String quizstr = "";
ScoreBoard board = null; 

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
	
</head>
<body>
	<div id=header>

		<ul>
			<li class="name"><a>Quizzler</a></li>
			<li><a href="HomepageLogin.html">Logout</a></li>
			<li><a href="TakeNewQuiz.jsp">Quizzes</a></li>
			<li><a href="/Quizlet/HomepageUser.jsp">Profile</a></li>
		</ul>
		<div id="extra-large-inner-header">
			<h1>Summary: <%=quiz.getName() %></h1>
			<%
			
			out.println("<p><strong>Quiz Name:</strong> "+quiz.getName()+ "</p>");
			out.println("<p><strong>Quiz Description:</strong> "+quiz.getDescription()+"</p>");
			
			out.println("<p><strong>Creator:</strong> "+"<a href =\"/Quizlet/SearchUserServlet?user=" + username + "\">" + username +"</a></p>");
			
			out.println("<p><strong>Your Past Performance:</strong> </p>");
			
			for (Score sc : board.getUsers()) {
				if (sc.user.equals(username)){
					Date dt = new Date(sc.timetaken);
					out.println("<p>Taken at: "+ dt.toString()+", Score: "+sc.score+", Time: "+sc.timescore+"</p>");
				}
			}
			out.println("<p><strong>Top Performers of all time:</strong> </p>");
			ArrayList<Score> top = board.getTopPerformers();
			for (Score sc : top) {
				Date dt = new Date(sc.timetaken);
				out.println("<p>User: "+sc.user +", Taken at: "+ dt.toString()+", Score: "+sc.score+", Time: "+sc.timescore+"</p>");
				
			}
			
			out.println("<p><strong>Top Performers in last 15 minutes:</strong> </p>");
			ArrayList<Score> recent = board.getTopRecentPerformers();
			for (Score sc : recent) {
				Date dt = new Date(sc.timetaken);
				out.println("<p>User: "+sc.user +", Taken at: "+ dt.toString()+", Score: "+sc.score+", Time: "+sc.timescore+"</p>");
				
			}
			out.println("<p><strong>All recent test takers(last 15 minutes):</strong> </p>");
			ArrayList<Score> recentall = board.getRecentPerformers();
			for (Score sc : recentall) {
				Date dt = new Date(sc.timetaken);
				out.println("<p>User: "+sc.user +", Taken at: "+ dt.toString()+", Score: "+sc.score+", Time: "+sc.timescore+"</p>");
				
			}
			out.println("<p><strong>All test takers:</strong> </p>");
			for (Score sc : board.getUsers()) {
				Date dt = new Date(sc.timetaken);
				out.println("<p>User: "+sc.user +", Taken at: "+ dt.toString()+", Score: "+sc.score+", Time: "+sc.timescore+"</p>");			
			}
			
			if (username.equals(currentuser)) {
				out.println("<form action=\"DisplayQuiz.jsp\" method=\"post\">"); 
				out.println("<input type=\"submit\" class=\"btn btn-primary\" value = \"Edit Quiz\"/>");
				out.println("</form>"); 
			}
			%>
			
			<div>
				<form action="TakeQuizServlet" method="post">
				  <input type="submit" class="btn btn-primary" value = "Take Quiz"/>
				</form>
				
				<form action="ListQuizzes.jsp" method="post">
				  <input type="submit" class="btn btn-primary" value = "Back"/>
				</form>
				
			</div>
			

	 	</div>
	</div>

</body>
</html>