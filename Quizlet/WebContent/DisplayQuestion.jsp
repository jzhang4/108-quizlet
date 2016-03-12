<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "quiz.*"%>
<%@ page import= "java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<%
	Quiz quiz = (Quiz)(session.getAttribute("quiz"));
	out.println("<title>"+quiz.getName()+ "</title>");
	%>
	
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
			<li><a href="/Quizlet/HomepageUser.jsp">Profile</a></li>
			<li><a href="HistorySummaryPage.jsp">History</a></li>
		</ul>
		<div id="extra-large-inner-header">
			<%
			out.println("<h1>"+quiz.getName()+ "</h1>");
			Iterator<Question> it = (Iterator<Question>)(session.getAttribute("iterator"));
			
			Question q = it.next();
			String ques = q.getQuestion();
			int index = (int)(session.getAttribute("index"));
			
			if (q.getType() == Question.PICTURE_RESPONSE) {
				out.println("<p><strong>Question "+index + "</strong></p>");
				out.println("<img src=\""+ques + "\" alt = \""+ques+"\" style = \"width:128px;height:128px;\">");
			} else out.println("<p><strong>Question "+index + ":</strong> "+ques + "</p>");
			
			session.setAttribute("index", index+1);
			out.println("<form action=\"NextQuestionServlet\" method=\"post\">");
			
			if (q.getType() == Question.MULTIPLE_CHOICE) {
				for (String choice: q.getChoices()) {
					out.println("<input type=\"radio\" name=\"answer\" value=\""+choice+"\">  "+ choice +"<br>");
				}
			} else {
				int numanswers = q.getNumAnswers();
				for (int i = 0; i < numanswers; i++) {
					out.println("<input type=\"text\" name=\"answer\" />");
				}
			}
			
			session.setAttribute("currentq", q);
			out.println("<input type=\"submit\" class=\"btn btn-primary\" value = \"Next Question\"/>");
			out.println("</form>");
			%>
		</div>
	</div>

</body>
</html>