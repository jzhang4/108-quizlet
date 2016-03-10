<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="user.User, java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><% out.println(((User)request.getAttribute("user")).getUserName()); %></title>
</head>
<body>
<h1><% out.println(((User)request.getAttribute("user")).getUserName());%>'s Page</h1>
<h2>ID <% out.println(((User)request.getAttribute("user")).getID());%></h2>

<p>
<% 
	if(request.getAttribute("requestStatus") != null)
		out.println("Request sent to " + request.getAttribute("requestStatus"));
	else {
		out.println("<form action=\"FriendRequestServlet\" method=\"post\">");
		out.println("<input name=\"user\" type=\"hidden\" value=\""+ ((User)request.getAttribute("user")).getUserName() + "\"/>");
		out.println("<input type=\"submit\" value=\"Send Friend Request\"/>");
		out.println("<input type=\"hidden\" name=\"currUser\" value=\"" + request.getAttribute("currUser") + "\"/>");
		out.println("</form>");
	}
%>
</p>

<a href="/Quizlet/HomepageLogin.html">Logout</a>


</body>
</html>