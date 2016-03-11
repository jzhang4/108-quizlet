<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="user.User, java.util.*, user.AccountManager" %>

<% User u = (User)request.getAttribute("user"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><% out.println(u.getUserName()); %></title>
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
			<h1><% out.println(u.getUserName());%></h1>
			
			<a href="/Quizlet/SendMessage.jsp?recipient=<% out.println(u.getUserName()); %>">Send a message</a>
			
			<p>
			<% 
				if(request.getAttribute("requestStatus") != null) {
					out.println("Request sent to " + request.getAttribute("requestStatus"));
				} else if (request.getAttribute("friends") == null) {
					out.println("<form action=\"FriendRequestServlet\" method=\"post\">");
					out.println("<input name=\"user\" type=\"hidden\" value=\""+ u.getUserName() + "\"/>");
					out.println("<input type=\"submit\" class=\"btn btn-primary\" value=\"Send Friend Request\"/>");
					out.println("<input type=\"hidden\" class=\"btn btn-primary\" name=\"currUser\" value=\"" + request.getAttribute("currUser") + "\"/>");
					out.println("</form>");
				}
			%>
			
			
			<%
				out.write("<table style = \"width:100%\">");
				out.write("<tr>");
				out.write("<th>Friends</th>");
				out.write("<tr>");
				if(u.getFriends().size() == 0){
					out.write("<tr>");
					out.write("<td> ");
					out.write("</td>");
					out.write("</tr>");
				}
				for (Integer ID : u.getFriends()) {
					User friend = ((AccountManager)session.getAttribute("am")).getAccount(ID);
					out.write("<tr>");
					out.write("<td>");
					User cu = ((AccountManager)session.getAttribute("am")).getAccount((String)session.getAttribute("user"));
					
					out.println("<a href =\"/Quizlet/SearchUserServlet?user=" + friend.getUserName() + "&currUser=" + cu.getUserName() + "\">");
					out.println(friend.getUserName());
					out.println("</a>");
					out.write("</td>");
					out.write("</tr>");
				}
				out.write("</table>");

			%>
			
			</p>
		</div>
	</div>		


</body>
</html>