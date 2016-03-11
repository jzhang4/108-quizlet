<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="user.*, java.util.*, user.AccountManager, quiz.*, java.sql.*, userPhotos.*" %>
<%@ page import = "quiz.ScoreBoard.Score" %>
<%@ page import= "java.util.Date"%>



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
			
			<div id = "userPicture">
				<%
					UserPhoto photoLoader = (UserPhoto)(request.getServletContext()).getAttribute("photoAssign");
					String userCurrently = u.getUserName();
					int photoValue = photoLoader.getPhotoName(userCurrently);
					if (photoValue != 0){
						String htmlCode = "<img src = \"" + "./defaultPhotos/photo" + Integer.toString(photoValue) + ".jpg" + "\""   + "/>";
						out.write(htmlCode);
					} else {	// we need to pull the image file from an area outside the project space
						String path22 = request.getContextPath();
						out.write("<img src =\"" + path22 + "/image/" + userCurrently +".jpg" +"\"" + "/>");
					}
				%>	
					
			</div>
			
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
			</p>
			
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
			
			<h2>Recently Taken Quizzes:</h2>	
  			<%
  			
  			ServletContext context = getServletContext(); 
			DBConnection connect = (DBConnection)(context.getAttribute("Connection"));
			
			Statement stmt = connect.getStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes");
  			
  			ScoreBoard board = null; 
			String username = u.getUserName();
  			try {
  				rs.beforeFirst();
  				
  				while(rs.next()) {
  					Blob boardblob = rs.getBlob(7);
 					board = new ScoreBoard(boardblob);
 					ArrayList<Score> recentScores = board.getRecentTaken(username);
 					
 					String quizname = rs.getString(2);
 					for (Score sc : recentScores) {
 						Date dt = new Date(sc.timetaken);
 						out.println("<p>Quiz : "+quizname +", Taken at: "+ dt.toString()+", Score: "+sc.score+", Time: "+sc.timescore+"</p>");
 					}
  				}
  			} catch (SQLException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
			%>
			<h2>Recently Created Quizzes:</h2>	
  			<%
  			
  			ArrayList<Long> timelist = new ArrayList<Long>();
			rs.beforeFirst();
			while (rs.next()) {
				long time = rs.getLong(4);
				timelist.add(time);
			}
			Collections.sort(timelist); 
			Collections.reverse(timelist);
			long timecutoff;
			if (timelist.size() < 3) {
				timecutoff = 0;
			} else timecutoff = timelist.get(2);
			rs.beforeFirst();
  			
			ArrayList<Long> yourquiz = new ArrayList<Long>();
			rs.beforeFirst();
			while (rs.next()) {
				long time = rs.getLong(4);
				if (rs.getString(1).equals(username)) yourquiz.add(time);
			}
			Collections.sort(yourquiz); 
			Collections.reverse(yourquiz);
			
			if (yourquiz.size() < 3) {
				timecutoff = 0;
			} else timecutoff = yourquiz.get(2);
			rs.beforeFirst();
			while (rs.next()) {
				long time = rs.getLong(4);
				if (time >= timecutoff && rs.getString(1).equals(username)) {
					String name = rs.getString(2);
					Date dt = new Date(time);
					out.println("<p><a href=\"QuizSummaryPage.jsp?quizname="+name+"\">"+name+"</a> Created on "+dt.toString()+"</p>");
				}
			}
			%>
			
		</div>
	</div>		


</body>
</html>