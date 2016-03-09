<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="user.User, java.util.*, user.Request, user.AccountManager, user.Message, user.User" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Messages</title>
</head>
<body>

<h1>From: <% out.println(((Message)request.getAttribute("Message")).getSender()); %></h1>

<h1>To: <% out.println(((Message)request.getAttribute("Message")).getRecipient()); %></h1>

<h2>Subject: <% out.println(((Message)request.getAttribute("Message")).getSubject()); %></h2>

<h3>Message: <% out.println(((Message)request.getAttribute("Message")).getMessage()); %></h3>

<%
	Message m = (Message)request.getAttribute("Message");

	if (m.getType().equals("Request")) {
		out.println("<p>Visit ");
		out.println("<a href =\"/Quizlet/SearchUserServlet?user=" + m.getSender() + "\">");
		out.println(m.getSender() + "'s");
		out.println("</a> Page");
		
		out.println("<form action=\"RequestResponseServlet\" method=\"post\">");
		if (!m.getSender().equals(((User)request.getAttribute("currUser")).getUserName())) {
			out.println("<input type=\"submit\" name=\"AcceptRequest\" value=\"Accept\"/>");
			out.println("<input type=\"submit\" name=\"DeleteRequest\" value=\"Delete\"/>");
			out.println("<input name=\"currUser\" type=\"hidden\" value=\"" + ((User)request.getAttribute("currUser")).getUserName() + "\"/>");
			out.println("<input name=\"sender\" type=\"hidden\" value=\"" + m.getSender() + "\"/>");
			out.println("</p>");
		}
	}
%>

</body>
</html>