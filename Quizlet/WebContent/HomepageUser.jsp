<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="user.User, java.util.*, user.Request, user.AccountManager, user.Message" %>
<%@ page import = "administration.*" %>
<%@ page import = "userPhotos.*" %>
<%@ page import = "user.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "quiz.*" %>
<%@ page import = "quiz.ScoreBoard.Score" %>
<%@ page import= "java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome <% out.println(session.getAttribute("user"));%> - Quizzler</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
   	<link rel="stylesheet" href="CSS/UserHomePage.css">
	<link rel="stylesheet" href="CSS/common.css">
    <link rel="stylesheet" href="CSS/login-formatting.css">    

</head>
<body>
	<div id=header>
	
	<ul>
		<li class="name"><a>Quizzler</a></li>
		<li><a href="/Quizlet/LogoutServlet">Logout</a></li>
		<li><a href="ListQuizzes.jsp">Quizzes</a></li>
		<li><a href="HomepageUser.jsp">Profile</a></li>
	</ul>
	<div id="extra-large-inner-header">
		<div> 
			<h1>Welcome  <% out.println(session.getAttribute("user")); %></h1>
		</div>
		
		
		<div class="leftSide">
		
			<div id = "userPicture">
				<%
					UserPhoto photoLoader = (UserPhoto)(request.getServletContext()).getAttribute("photoAssign");
					String userCurrently = ((String)session.getAttribute("user"));
					int photoValue = photoLoader.getPhotoName(userCurrently);
					if (photoValue != 0){
						String htmlCode = "<img src = \"" + "./defaultPhotos/photo" + Integer.toString(photoValue) + ".jpg" + "\""   + "/>";
						out.write(htmlCode);
					} else {	// we need to pull the image file from an area outside the project space
						String path22 = request.getContextPath();
						out.write("<img src =\"" + path22 + "/image/" + userCurrently +".jpg" +"\"" + "/>");
					}
				%>
				<form action="UploadServlet" method="post" enctype="multipart/form-data">
				    <input type="file" name="file" />
				    <%
				    	String htmlCodeForForm = "<input type = \"hidden\" name = \"imageName\" + value = \"" + userCurrently + "\"" + "/>" ;
				    	out.write(htmlCodeForForm);
				    %>
				    <input type="submit" value ="Change Profile Photo"/>
				</form>		
					
			</div>
			<div class="divclass">
				<h1> Recent Announcements</h1>
				
	  			<%	
	  				Administrator values = (Administrator)(request.getServletContext()).getAttribute("currentStats");
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
			</div>
			
			<h1>Achievements</h1>
			<p><a href="AchievementViewer.jsp">Click here</a> to view all your achievements</p>
			<%  

				Achievements achieveContainer = (Achievements)(request.getServletContext()).getAttribute("achieveLookUp");
				if (achieveContainer != null){
					ArrayList<Integer> achHolder = new ArrayList<Integer>();
					String userName = (String)session.getAttribute("user");
					achHolder = achieveContainer.fetchAchievemnt(userName);
					if(achHolder != null){
						int numTotalAchieve = 0;
						if (achHolder.size() > 0){
							for (int i = 0; i < achHolder.size(); i++){
								String location = "./AchievementImages/Achieve";
								if (achHolder.get(i) == 1 && numTotalAchieve < 2){
									numTotalAchieve += 1;
									out.write("<div class=\"floated_img\">");
									out.write("<img src=\"" + location + Integer.toString(i+1) + ".png\">");
									out.write("</div>");
								}
							}
						} else {
							out.write("<p>No achievements yet! Go take some quizzes and earn some!</p>");
						}
					}
				}
			%>  
		</div>
		
		<div class="rightSide">
			<h1> Social Connections </h1>
			<form action="SearchUserServlet" method="post">
			<input type="text" name="user"/>
			<input type="submit" class="btn btn-primary" value="Search for User"/>
			<input name="currUser" type="hidden" value="<% out.println(session.getAttribute("user"));;%>"/>
			<p>
				<% 
					if(request.getAttribute("error") != null)
						out.println("User" + request.getAttribute("error") + " does not exist");
				%>
			</p>
			</form>
			<div class="friendstables">
				<h2>Friends</h2>
					<%
						out.write("<table style = \"width:100%\">");
						out.write("<tr>");
						out.write("<th>Friends</th>");
						out.write("<tr>");
						User cu = ((AccountManager)session.getAttribute("am")).getAccount((String)session.getAttribute("user"));
						if(cu.getFriends().size() == 0){
							out.write("<tr>");
							out.write("<td> ");
							out.write("</td>");
							out.write("</tr>");
						}
						for (Integer ID : cu.getFriends()) {
							User u = ((AccountManager)session.getAttribute("am")).getAccount(ID);
							out.write("<tr>");
							out.write("<td>");
							out.println("<a href =\"/Quizlet/SearchUserServlet?user=" + u.getUserName() + "&currUser=" + cu.getUserName() + "\">");
							out.println(u.getUserName());
							out.println("</a>");
							out.write("</td>");
							out.write("</tr>");
						}
						out.write("</table>");

					%>
				
				<h2>Received requests from:</h2>
				
				<ul>
				<%
					List<Request> receivedRequests = cu.getReceivedRequests();
					for (int i = receivedRequests.size() - 1; i >= 0; i--) {
						Request r = receivedRequests.get(i);
						int ID = r.getSenderID();
						User u = ((AccountManager)session.getAttribute("am")).getAccount(ID);
						if (u.getUserName().equals(cu.getUserName().trim())) {
							break;
						}
						out.println("<li>");
						out.println("<a href =\"/Quizlet/SearchUserServlet?user=" + u.getUserName() + "&currUser=" + cu.getUserName() + "\">");
						out.println(u.getUserName());
						out.println("</a>");
						
						
						out.println("<form action=\"RequestResponseServlet\" method=\"post\">");
						out.println("<input type=\"submit\" name=\"AcceptRequest\" value=\"Accept\"/>");
						out.println("<input type=\"submit\" name=\"DeleteRequest\" value=\"Delete\"/>");
						out.println("<input name=\"currUser\" type=\"hidden\" value=\"" + cu.getUserName() + "\"/>");
						out.println("<input name=\"sender\" type=\"hidden\" value=\"" + u.getUserName() + "\"/>");
						out.println("</li>");
					}
				%>
				</ul>	
				
				<h2>Sent requests to:</h2>
				
				<ul>
				<%
					List<Request> sentRequests = cu.getSentRequests();
					for (int i = sentRequests.size() - 1; i >= 0; i--) {
						Request r = sentRequests.get(i);
						int ID = r.getRecipientID();
				
						User u = ((AccountManager)session.getAttribute("am")).getAccount(ID);
						out.println("<li>");
						out.println("<a href =\"/Quizlet/SearchUserServlet?user=" + u.getUserName() + "&currUser=" + cu.getUserName() + "\">");
						out.println(u.getUserName());
						out.println("</a>");
						out.println("</li>");
					}
	
				%>
				</ul>
			</div>
			
			
			<h2><a href="/Quizlet/SendMessage.jsp">Send a new message</a></h2>
			

			<h2>Received messages from:</h2>	
  			<%
  				List<Message> receivedMessages = cu.getReceivedMessages();
  				for (int i = receivedMessages.size() - 1; i >=0; i--) {
  					Message m = receivedMessages.get(i);
					String sender = m.getSender();
					User u = ((AccountManager)session.getAttribute("am")).getAccount(sender);
					out.println("<li>");
					if (!m.isRead()) {
						out.println("<b>");
					}
					out.println("<a href =\"/Quizlet/ViewMessageServlet?id=" + m.getID() + "&currUser=" + cu.getUserName() + "\">");
					out.println(u.getUserName());
					out.println("</a>");
					out.println(m.getSubject());
					if (!m.isRead()) {
						out.println("</b>");
					}
				}
			%>  		
			
			<h2>Sent messages to:</h2>	
  			<%
				List<Message> sentMessages = cu.getSentMessages();
				for (int i = sentMessages.size() - 1; i >=0; i--) {
					Message m = sentMessages.get(i);
					String recipient = m.getRecipient();
					User u = ((AccountManager)session.getAttribute("am")).getAccount(recipient);
					out.println("<li>");
					out.println("<a href =\"/Quizlet/ViewMessageServlet?id=" + m.getID() + "&currUser=" + cu.getUserName() + "\">");
					out.println(u.getUserName());
					out.println("</a>");
				}
			%> 
			<h2>Popular Quizzes:</h2>	
  			<%
  			ServletContext context = getServletContext(); 
			DBConnection connect = (DBConnection)(context.getAttribute("Connection"));
			
			Statement stmt = connect.getStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes");
			
			ArrayList<Long> takenlist = new ArrayList<Long>();
			
			while (rs.next()) {
				long taken = rs.getLong(3);
				takenlist.add(taken);
			}
			Collections.sort(takenlist); 
			Collections.reverse(takenlist);


			long cutoff;
			if (takenlist.size() < 3) {
				cutoff = 0;
			} else cutoff = takenlist.get(2);
			rs.beforeFirst();
			while (rs.next()) {
				long taken = rs.getLong(3);
				if (taken >= cutoff) {
					String name = rs.getString(2);
					out.println("<p><a href=\"QuizSummaryPage.jsp?quizname="+name+"\">"+name+"</a> Taken "+taken+" times</p>");
				}
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
			while (rs.next()) {
				long time = rs.getLong(4);
				if (time >= timecutoff) {
					String name = rs.getString(2);
					Date dt = new Date(time);
					String user = rs.getString(1);
					out.println("<p><a href=\"QuizSummaryPage.jsp?quizname="+name+"\">"+name+"</a> Created on "+dt.toString()+", by "+user+"</p>");
				}
			}
			%>
 			
 			<h2>Recently Taken Quizzes:</h2>	
  			<%
  			ScoreBoard board = null; 
			String username = (String)session.getAttribute("user");
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
			<h2>Your Recently Created Quizzes:</h2>	
  			<%
  			
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
