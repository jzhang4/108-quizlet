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

<form action="FriendRequestServlet" method="post">
<input name="user" type="hidden" value="<% out.println(((User)request.getAttribute("user")).getUserName()); %>"/>
<input type="submit" value="Send Friend Request"/></p>
</form>


</body>
</html>