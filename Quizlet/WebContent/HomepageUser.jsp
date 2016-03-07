<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="user.User, java.util.*, user.Request, user.AccountManager" %>
<%@ page import = "administration.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Successful Login</title>
<link rel="stylesheet" href="UserHomePage.css">
</head>
<body>
	<div class = "header"> 
		<h1>Welcome  <% out.println(((User)request.getAttribute("user")).getUserName());%></h1>
	</div>



<a href="/Quizlet/HomepageLogin.html">Logout</a>
<p>
<% 
	if(request.getAttribute("error") != null)
		out.println(request.getAttribute("error") + " does not exist");
%>
</p>
<h1> Recent Announcements</h1>

<%
	Administrator values = (Administrator) session.getAttribute("currentStats");
	ArrayList<Announcement> announcements = new ArrayList<Announcement>();
	announcements = values.getAnnounce();
	out.write("<table style = \"width:100%\">");
	out.write("<tr>");
	out.write("<th>Announcement</th>");
	out.write("<th>Date</th>");
	out.write("<tr>");
	for (int i = 0; i < announcements.size(); i++){
		Announcement temp = announcements.get(i);
		out.write("<tr>");
		out.write("<td>" + temp.getText() + "</td>");
		out.write("<td>" + temp.getDate() + "</td>");
		out.write("</tr>");
	}
	out.write("</table>");
%>



<h1> Social Connections </h1>
<form action="SearchUserServlet" method="post">
<input type="text" name="user"/>
<input type="submit" value="Search for User"/>
<input name="currUser" type="hidden" value="<% out.println(((User)request.getAttribute("user")).getUserName());%>"/>
</form>

<h2>Sent requests</h2>

<ul>
<%
	for (Request r : ((User)request.getAttribute("user")).getSentRequests()) {
		int ID = r.getRecipientID();
		User u = ((AccountManager)request.getAttribute("am")).getAccount(ID);
		out.println("<li>");
		out.println(u.getUserName());
		out.println("</li>");
	}
%>
</ul>

<h2>Received requests</h2>

<ul>
<%
	for (Request r : ((User)request.getAttribute("user")).getReceivedRequests()) {
		int ID = r.getSenderID();
		User u = ((AccountManager)request.getAttribute("am")).getAccount(ID);
		out.println("<li>");
		out.println(u.getUserName());
		out.println("</li>");
	}
%>
</ul>
<h1>Achievements</h1>
<p> Click <a href = "./AchievementViewer.jsp">here</a> to view your achievements </p>
</body>
</html>