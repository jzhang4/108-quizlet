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
	<title>Display quiz</title>
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
			<h1>Display Quiz</h1>
			<%
			
			Quiz quiz = (Quiz)(session.getAttribute("quiz"));
			out.println("<h2>"+quiz.getName()+ "</h2>");
			out.println("<p>"+quiz.getDescription()+"<p>");
			Iterator<Question> it = quiz.iterator();
			int index = 1;
			while (it.hasNext()) {
				Question q = it.next();
				String ques = q.getQuestion();
				out.println("<p>Type: "+q.stringType(q.getType()) + "</p>");
			
				if (q.getType() == Question.PICTURE_RESPONSE) {
					out.println("<p>Question "+index + "</p>");
					out.println("<img src=\""+ques + "\" alt = \""+ques+"\" style = \"width:128px;height:128px;\">");
				} else out.println("<p>Question "+index + ": "+ques + "</p>");
				
				if (q.getType() == Question.MULTIPLE_ANSWER) {
					MAQuestion maq = (MAQuestion)q; 
					String ordered = Boolean.toString(maq.ordered());
					int numanswers = maq.getNumAnswers();
					out.println("<p>Ordered: "+ordered + "</p>");
					out.println("<p>Number of Required Answers: " + numanswers +"</p>");
				}
				
				ArrayList<String> answers = q.getAnswer().getAnswer();
				out.println("<p> Answer(s): ");
				for (String str : answers) {
					out.println(str+"\n");
				}
				out.println("</p>");
				if (q.getType() == Question.MULTIPLE_CHOICE) {
					ArrayList<String> choices = q.getChoices();
					out.println("<p> Choices(s): ");
					for (String str : choices) {
						out.println(str+"\n");
					}
					out.println("</p>");
				}
				index++;
				
			}
			
			%>
			
			
			<form action="RemoveQuestionServlet" method="post">
			  <p>Remove question number: <input type="text" name="num" />
			  <input type="submit" class="btn btn-primary" value = "Remove"/>
			</form>
			
			<form action="AddQuestionServlet" method="post">
			  <input type="submit" class="btn btn-primary" value = "Add Additional Questions"/>
			</form>
			
			<p>Done making the quiz?</p>
			<form action="CreateQuizServlet" method="post">
			  <input type="submit" class="btn btn-primary" value = "Save Quiz"/>
			</form>
			
			<form action="ListQuizzes.jsp" method="post">
			  <input type="submit" class="btn btn-primary" value = "Cancel"/>
			</form>
	 	</div>
	</div>

</body>
</html>