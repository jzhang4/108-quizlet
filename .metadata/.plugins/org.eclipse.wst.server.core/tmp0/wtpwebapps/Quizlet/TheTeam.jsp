<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ page import ="administration.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>The Team - Quizzler</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="CSS/common.css">
	<link rel="stylesheet" href="CSS/nav-bar.css">
	<link rel="stylesheet" href="CSS/TheTeam.css">
	
</head>
<body>
	<% 
		Administrator adminLink = (Administrator) (request.getServletContext()).getAttribute("currentStats");
		adminLink.incrementCount("TheTeam.jsp");
	%>
	<div id=header>
		<ul>
		  <li class="name"><a href="index.jsp">Quizzler</a></li>
		  <li><a href="TheTeam.html">Meet the Team</a></li>
		  <li><a href="HomepageLogin.html">Login</a></li>
		</ul>
		<div id="innerHeaderLarge">
			<div class="team_images">
				<img src="theTeamMembers/jaimiexiecs108.jpg" style="width:100px;height:100px;">
				<img src="theTeamMembers/jessicaZhang.png" style="width:100px;height:100px;">
				<img src="theTeamMembers/liamNeath.jpg" style="width:100px;height:100px;">
				<img src="theTeamMembers/serena.jpg" style="width:100px;height:100px;">
			</div>
			<h2 class="img_caption">Jaimie</h2>
			<h2 class="img_caption">Jess</h2>
			<h2 class="img_caption">Liam</h2>
			<h2 class="img_caption">Serena</h2>
		</div>
	</div>
</body>
</html>