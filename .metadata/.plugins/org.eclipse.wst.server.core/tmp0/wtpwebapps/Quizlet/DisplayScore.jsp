<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "quiz.Quiz"%>
<%@ page import= "quiz.Question"%>
<%@ page import= "quiz.ScoreBoard"%>
<%@ page import= "user.DBConnection"%>
<%@ page import= "java.sql.*"%>

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
			<li><a href="ListQuizzes.jsp">Quizzes</a></li>
			<li><a href="/Quizlet/HomepageUser.jsp">Profile</a></li>
			<li><a href="HistorySummaryPage.jsp">History</a></li>
		</ul>
		<div id="extra-large-inner-header">
			<h1>Score: <%= session.getAttribute("score")%></h1>
			<%
			long newtime = System.currentTimeMillis(); 
			long time = newtime - (long)session.getAttribute("time");
			time /= 1000; 

	        Quiz quiz = (Quiz)(session.getAttribute("quiz"));
	        	        
	        
	        ServletContext context = getServletContext(); 
			DBConnection connect = (DBConnection)(context.getAttribute("Connection"));

	        
	        String name = quiz.getName();
	        String username = (String)session.getAttribute("user");
	        int score = (int)session.getAttribute("score");
	        
	        Statement stmt = connect.getStatement();
	        PreparedStatement pstmt = connect.getPreparedStatement2();
	        
	        try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes");
				
				while(rs.next()) {
					String quizname = rs.getString(2);
					if (quizname.equals(name)) {
						Blob boardblob = rs.getBlob(7);
						ScoreBoard sb = new ScoreBoard(boardblob);
						
						sb.addScore(username, score, time, newtime);
						byte[] sbytes = sb.boardToBytes();
						
						pstmt.setBytes(1, sbytes);
						pstmt.setString(2, name);
						pstmt.execute();
						
						long bestscore = rs.getLong(6); 
						if (score > bestscore) stmt.executeUpdate("UPDATE quizzes SET highscore = "+score+" WHERE name = \""+name+"\"");
						break; 
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			%>
			
			<h1>Time: <%= time%> seconds</h1>
			<form action="DisplayAnswersServlet" method="post">
				<input type="submit" class="btn btn-primary" value = "Get Answers"/>
			</form>
		</div>
	</div>
</body>
</html>