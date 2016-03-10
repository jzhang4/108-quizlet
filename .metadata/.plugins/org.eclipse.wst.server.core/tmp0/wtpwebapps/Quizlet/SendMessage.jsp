<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Send New Message</title>
</head>
<body>

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

</body>
</html>