<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>Achievements</title>
			<link rel="stylesheet" href ="HomePage.css">
			<link rel="stylesheet" href="AchievementStyling.css">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="CSS/common.css">
	<link rel="stylesheet" href="CSS/login-formatting.css">
	<link rel="stylesheet" href="CSS/tableFormatting.css">
	
</head>
<body>
	<div id=header>

		<ul>
			<li class="name"><a>Quizzler</a></li>
			<li><a href="HomepageLogin.html">Logout</a></li>
			<li><a href="ListQuizzes.jsp">Quizzes</a></li>
			<li><a href="/Quizlet/HomepageUser.jsp">Profile</a></li>
			<li><a href="HistorySummaryPage.jsp">History</a></li>
		</ul>
		<div id="extra-large-inner-header">
				<h1>Achievements</h1> 
			<form action = "./achieveGetServlet" method = post>
				<input type = "text" name = "userNameToLookUp" placeHolder = "Name of user to look up" size = "40">
				<input type = "submit" class="btn btn-primary" value = "Submit">				
			</form>
			<%  
				if(request.getAttribute("userAchievements") != null){	// check for the state of achievements when we first generate the page 
						
					ArrayList<Integer> values = (ArrayList<Integer>) request.getAttribute("userAchievements");
					if (values.size() > 0){
						out.write("<h1> Displaying " + (String)request.getAttribute("nameOfUser") + "'s Achievements</h1>" );
						for (int i = 0; i < values.size(); i++){
							String location = "./AchievementImages/Achieve";
							if (values.get(i) == 1){
								out.write("<div class=\"floated_img\">");
								out.write("<img src=\"" + location + Integer.toString(i+1) + ".png\">");
								out.write("</div>");
							}
						}
					} else {
						out.write("<h1> Could Not Find The Requested User</h1>");
					}
				}
			%>
			</div>
		</div>
</body>
		
	</html>