/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.32
 * Generated at: 2016-03-12 00:11:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import user.User;
import java.util.*;
import user.Request;
import user.AccountManager;
import user.Message;
import administration.*;
import userPhotos.*;
import user.*;
import java.sql.*;
import java.util.*;
import quiz.*;
import quiz.ScoreBoard.Score;
import java.util.Date;

public final class HomepageUser_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("quiz");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("userPhotos");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("administration");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("user");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("user.Request");
    _jspx_imports_classes.add("user.User");
    _jspx_imports_classes.add("java.util.Date");
    _jspx_imports_classes.add("quiz.ScoreBoard.Score");
    _jspx_imports_classes.add("user.AccountManager");
    _jspx_imports_classes.add("user.Message");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("    \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("\t<title>Welcome ");
 out.println(session.getAttribute("user"));
      out.write(" - Quizzler</title>\n");
      out.write("\t<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">\n");
      out.write("   \t<link rel=\"stylesheet\" href=\"CSS/UserHomePage.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"CSS/common.css\">\n");
      out.write("\n");
      out.write("    <link rel=\"stylesheet\" href=\"CSS/login-formatting.css\">    \n");
      out.write("\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<div id=header>\n");
      out.write("\t\n");
      out.write("\t<ul>\n");
      out.write("\t\t<li class=\"name\"><a>Quizzler</a></li>\n");
      out.write("\t\t<li><a href=\"/Quizlet/LogoutServlet\">Logout</a></li>\n");
      out.write("\t\t<li><a href=\"ListQuizzes.jsp\">Quizzes</a></li>\n");
      out.write("\t\t<li><a href=\"HomepageUser.jsp\">Profile</a></li>\n");
      out.write("\t\t<li><a href=\"HistorySummaryPage.jsp\">History</a></li>\n");
      out.write("\t</ul>\n");
      out.write("\t<div id=\"extra-large-inner-header\">\n");
      out.write("\t\t<div> \n");
      out.write("\t\t\t<h1>Welcome  ");
 out.println(session.getAttribute("user")); 
      out.write("</h1>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("\t\t<div class=\"leftSide\">\n");
      out.write("\t\t\n");
      out.write("\t\t\t<div id = \"userPicture\">\n");
      out.write("\t\t\t\t");

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
				
      out.write("\n");
      out.write("\t\t\t\t<form action=\"UploadServlet\" method=\"post\" enctype=\"multipart/form-data\">\n");
      out.write("\t\t\t\t    <input type=\"file\" name=\"file\" />\n");
      out.write("\t\t\t\t    ");

				    	String htmlCodeForForm = "<input type = \"hidden\" name = \"imageName\" + value = \"" + userCurrently + "\"" + "/>" ;
				    	out.write(htmlCodeForForm);
				    
      out.write("\n");
      out.write("\t\t\t\t    <input type=\"submit\" value =\"Change Profile Photo\"/>\n");
      out.write("\t\t\t\t</form>\t\t\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"divclass\">\n");
      out.write("\t\t\t\t<h1> Recent Announcements</h1>\n");
      out.write("\t\t\t\t\n");
      out.write("\t  \t\t\t");
	
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
				
      out.write("\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<h1>Achievements</h1>\n");
      out.write("\t\t\t<p><a href=\"AchievementViewer.jsp\">Click here</a> to view all your achievements</p>\n");
      out.write("\t\t\t");
  
				Achievements achieveContainer = (Achievements)(request.getServletContext()).getAttribute("achieveLookUp");
				if (achieveContainer != null){
					String userName = (String)session.getAttribute("user");
					achieveContainer.doUpdate("Amateur Author", userName);
					ArrayList<Integer> achHolder = new ArrayList<Integer>();
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
			
      out.write("  \n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t<div class=\"rightSide\">\n");
      out.write("\t\t\t<h1> Social Connections </h1>\n");
      out.write("\t\t\t<form action=\"SearchUserServlet\" method=\"post\">\n");
      out.write("\t\t\t<input type=\"text\" name=\"user\"/>\n");
      out.write("\t\t\t<input type=\"submit\" class=\"btn btn-primary\" value=\"Search for User\"/>\n");
      out.write("\t\t\t<input name=\"currUser\" type=\"hidden\" value=\"");
 out.println(session.getAttribute("user"));;
      out.write("\"/>\n");
      out.write("\t\t\t<p>\n");
      out.write("\t\t\t\t");
 
					if(request.getAttribute("error") != null)
						out.println("User" + request.getAttribute("error") + " does not exist");
				
      out.write("\n");
      out.write("\t\t\t</p>\n");
      out.write("\t\t\t</form>\n");
      out.write("\t\t\t<div class=\"friendstables\">\n");
      out.write("\t\t\t\t<h2>Friends</h2>\n");
      out.write("\t\t\t\t\t");

						out.write("<table style = \"width:100%\">");
						out.write("<tr>");
						out.write("<th>Friends</th>");
						out.write("<tr>");
						User cu = ((AccountManager)session.getAttribute("am")).getAccount((String)session.getAttribute("user"));
						if (cu.getFriends().size() >= 10){		// Number of friends achievmeent!
							String userName = (String)session.getAttribute("user");
							if (achieveContainer != null)achieveContainer.doUpdate("Friendly Quizzer", userName);		
						}
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
					
      out.write("\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<h2>Received requests from:</h2>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<ul>\n");
      out.write("\t\t\t\t");

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
				
      out.write("\n");
      out.write("\t\t\t\t</ul>\t\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<h2>Sent requests to:</h2>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<ul>\n");
      out.write("\t\t\t\t");

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
	
				
      out.write("\n");
      out.write("\t\t\t\t</ul>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<h2><a href=\"/Quizlet/SendMessage.jsp\">Send a new message</a></h2>\n");
      out.write("\t\t\t\n");
      out.write("\n");
      out.write("\t\t\t<h2>Received messages from:</h2>\t\n");
      out.write("  \t\t\t");

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
			
      out.write("  \t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<h2>Sent messages to:</h2>\t\n");
      out.write("  \t\t\t");

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
			
      out.write(" \n");
      out.write("\t\t\t<h2>Popular Quizzes:</h2>\t\n");
      out.write("  \t\t\t");

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
			
      out.write("\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<h2>Recently Created Quizzes:</h2>\t\n");
      out.write("  \t\t\t");

  			
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
			
      out.write("\n");
      out.write(" \t\t\t\n");
      out.write(" \t\t\t<h2>Recently Taken Quizzes:</h2>\t\n");
      out.write("  \t\t\t");

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
			
      out.write("\n");
      out.write("\t\t\t<h2>Your Recently Created Quizzes:</h2>\t\n");
      out.write("  \t\t\t");

  			
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
			
      out.write("\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<h2>Friends activity:</h2>\t\n");
      out.write("  \t\t\t");

  			ArrayList<Long> times = new ArrayList<Long>(); 
  			
  			for (Integer ID : cu.getFriends()) {
				User u = ((AccountManager)session.getAttribute("am")).getAccount(ID);
				rs.beforeFirst();
				while (rs.next()) {
					long time = rs.getLong(4);
					if (rs.getString(1).equals(u.getUserName())) times.add(time);
				}
				
			
			}
  			Collections.sort(times); 
			Collections.reverse(times);
			
			if (times.size() < 3) {
				timecutoff = 0;
			} else timecutoff = times.get(2);
			
			for (Integer ID : cu.getFriends()) {
				User u = ((AccountManager)session.getAttribute("am")).getAccount(ID);
				String usern = u.getUserName();
				rs.beforeFirst();
				while (rs.next()) {
					long time = rs.getLong(4);
					if (time >= timecutoff && rs.getString(1).equals(usern)) {
						String name = rs.getString(2);
						Date dt = new Date(time);
						out.println("<p>"+usern+" created <a href=\"QuizSummaryPage.jsp?quizname="+name+"\">"+name+"</a> on "+dt.toString()+"</p>");
						
					}
					Blob boardblob = rs.getBlob(7);
 					board = new ScoreBoard(boardblob);
 					ArrayList<Score> recentScores = board.getRecentTaken(usern);
 					String quizname = rs.getString(2);
 					for (Score sc : recentScores) {
 						Date dt = new Date(sc.timetaken);
 						out.println("<p>"+usern+" took "+quizname +" at: "+ dt.toString()+", Score: "+sc.score+", Time: "+sc.timescore+"</p>");
 					}
				}					
			}
			
      out.write("\n");
      out.write("\t\t\t\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
