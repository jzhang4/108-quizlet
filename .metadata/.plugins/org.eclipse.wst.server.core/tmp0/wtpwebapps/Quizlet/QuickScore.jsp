<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "quiz.Quiz"%>
<%@ page import= "quiz.Question"%>
<%@ page import= "quiz.MAQuestion"%>
<%@ page import= "java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Points</title>
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
		<li><a href="HomepageUser.jsp">Profile</a></li>
		<li><a href="HistorySummaryPage.jsp">History</a></li>
	</ul>
	<div id="innerHeaderLarge">
		<h1>Points: <%= request.getAttribute("points")%></h1>
		<%
			Iterator<Question> it = (Iterator<Question>)(session.getAttribute("iterator"));	
			if (it.hasNext()) {
				out.println("<form action=\"DisplayQuestion.jsp\" method=\"post\">"); 
				out.println("<input type=\"submit\" value = \"Next Question\"/>");
				out.println("</form>");
			} else {
				out.println("<form action=\"DisplayScore.jsp\" method=\"post\">"); 
				out.println("<input type=\"submit\" value = \"See Score\"/>");
				out.println("</form>");
			}
		%>
	</div>
</div>
</body>
</html>