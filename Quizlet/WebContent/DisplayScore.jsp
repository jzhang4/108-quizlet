<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Score</title>
</head>
<body>
<h1>Score: <%= session.getAttribute("score")%></h1>
<%
long newtime = System.currentTimeMillis(); 
long time = newtime - (long)session.getAttribute("time");
time /= 1000; 
%>
<h1>Time: <%= time%> seconds</h1>
<form action="DisplayQuizServlet" method="post">
  <input type="submit" value = "Get Answers"/>
</form>
</body>
</html>