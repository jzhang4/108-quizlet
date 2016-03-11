<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Send New Message</title>
<link rel="stylesheet" href="CSS/common.css">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="CSS/login-formatting.css">
	
</head>
<body>
	<div id=header>

		<ul>
			<li class="name"><a>Quizzler</a></li>
			<li><a href="HomepageLogin.html">Logout</a></li>
			<li><a href="ListQuizzes.jsp">Quizzes</a></li>
			<li><a href="/Quizlet/HomepageUser.jsp">Profile</a></li>
		</ul>
		<div id="innerHeaderLarge">

			<h1>Send your Message</h1>
			
			<form action="SendNewMessageServlet" method="post" id="newmessage">
			
			<%
				if (request.getAttribute("subject") != null) {
					out.println("To: <input type=\"text\" name=\"recipient\" value=\"" + request.getAttribute("to") + "\"><br>");
					out.println("Subject: <input type=\"text\" name=\"subject\" value=\"" + request.getAttribute("subject") + "\"><br>");
				} else {
					out.println("To: <input type=\"text\" name=\"recipient\"><br>");
					out.println("Subject: <input type=\"text\" name=\"subject\"><br>");
				}	
			%>
			
			
			<!--   To: <input type="text" name="recipient"><br>
			  Subject: <input type="text" name="subject"><br> -->
			  Message:<br>
			  <textarea name="message" form="newmessage"></textarea>
			  <input type="submit" value="Send">
			</form>
		</div>
	</div>
</body>
</html>