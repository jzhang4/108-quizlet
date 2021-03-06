/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.32
 * Generated at: 2016-03-12 03:58:18 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import quiz.Quiz;
import quiz.Question;
import quiz.MAQuestion;
import java.util.*;
import java.util.Date;
import user.*;
import java.util.*;
import java.sql.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import quiz.JSONCreator;
import quiz.ScoreBoard;
import quiz.ScoreBoard.Score;
import org.json.simple.parser.ParseException;

public final class QuizSummaryPage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("user");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("quiz.JSONCreator");
    _jspx_imports_classes.add("quiz.Question");
    _jspx_imports_classes.add("org.json.simple.JSONObject");
    _jspx_imports_classes.add("org.json.simple.parser.ParseException");
    _jspx_imports_classes.add("quiz.Quiz");
    _jspx_imports_classes.add("org.json.simple.parser.JSONParser");
    _jspx_imports_classes.add("java.util.Date");
    _jspx_imports_classes.add("quiz.ScoreBoard");
    _jspx_imports_classes.add("quiz.ScoreBoard.Score");
    _jspx_imports_classes.add("quiz.MAQuestion");
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
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


String quizstr = "";
String username = "";
ScoreBoard board = null; 
String name = request.getParameter("quizname");
String currentuser = "";
User cu = null;


if (request.getAttribute("temp") == null) {
	ServletContext context = getServletContext(); 
	DBConnection connect = (DBConnection)(context.getAttribute("Connection"));

	currentuser = (String)session.getAttribute("user");
	cu = ((AccountManager)session.getAttribute("am")).getAccount(currentuser);
	Statement stmt = connect.getStatement();


	try {
		ResultSet rs = stmt.executeQuery("SELECT * FROM quizzes");
		
		while(rs.next()) {
			String quizname = rs.getString(2);

			if (quizname.equals(name)) {
				long taken = rs.getLong(3); 
				username = rs.getString(1);
				Blob quizblob = rs.getBlob(5);
				Blob boardblob = rs.getBlob(7);
				
				board = new ScoreBoard(boardblob);
				
				byte[] bdata = quizblob.getBytes(1, (int)quizblob.length());
				quizstr = new String(bdata);			
				break; 
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}




	JSONParser parser = new JSONParser(); 

	Quiz quiz =null; 

	try {
	Object obj = parser.parse(quizstr);
	JSONObject jobj = (JSONObject)obj;
	quiz = JSONCreator.getQuiz(jobj);
	
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	session.setAttribute("quiz", quiz);



      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("\t<title>Summary: ");
      out.print(quiz.getName() );
      out.write("</title>\n");
      out.write("\t<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"CSS/common.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"CSS/login-formatting.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"CSS/tableFormatting.css\">\n");
      out.write("\t\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<div id=header>\n");
      out.write("\t\t\n");
      out.write("\t\t<ul>\n");
      out.write("\t\t\t<li class=\"name\"><a>Quizzler</a></li>\n");
      out.write("\t\t\n");
      out.write("\t\t\t");

			if (request.getParameter("temp") != null) {	
				out.println("<li><a href=\"CreateNewAccount.html\">Create Account</a></li>");
				request.setAttribute("temp", "true");


			} else {
				out.println("<li><a href=\"HomepageLogin.html\">Logout</a></li>");
				out.println("<li><a href=\"ListQuizzes.jsp\">Quizzes</a></li>");
				out.println("<li><a href=\"HomepageUser.jsp\">Profile</a></li>");
				out.println("<li><a href=\"HistorySummaryPage.jsp\">History</a></li>");
			}
			
      out.write("\n");
      out.write("\t\t</ul>\n");
      out.write("\t\t<div id=\"extra-large-inner-header\">\n");
      out.write("\n");
      out.write("\t\t\t<h1>Summary: ");
      out.print(quiz.getName() );
      out.write("</h1>\n");
      out.write("\t\t\t<div>\n");
      out.write("\t\t\t");

			

 			if (request.getAttribute("error") != null) {
 				out.println(request.getAttribute("error"));
 			}
 			
			out.println("<p>Quiz Name: "+quiz.getName()+ "</p>");
			out.println("<p>Quiz Description: "+quiz.getDescription()+"</p>");
			
			out.println("<p><strong>Creator:</strong> "+"<a href =\"/Quizlet/SearchUserServlet?user=" + username + "\">" + username +"</a></p>");
			
      out.write("\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t");
 
			
			if (request.getParameter("temp") == null) {					
				out.println("<form action=\"TakeQuizServlet\" method=\"post\">");
				out.println("<input type=\"submit\" class=\"btn btn-primary\" value = \"Take Quiz\"/>");
				out.println("</form>");
			}

			
      out.write(" \t\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t");

			
			if (request.getParameter("temp") == null) {					
				out.println("<p><strong>Your Past Performance:</strong> </p>");
				out.write("<table>");
				out.write("<tr>");
				out.write("<th>Date Taken</th><th>Score</th><th>Time</th>");
				out.write("<tr>");
				int count = 0;
				for (Score sc : board.getUsers()) {
					if (sc.user.equals(currentuser)){
						Date dt = new Date(sc.timetaken);
						out.println("<tr>");
						out.println("<td>"+ dt.toString()+"</td><td>"+sc.score+"</td><td>"+sc.timescore+"</td>");
						out.println("</tr>");
						count++;
					}
				}
				out.write("</table>");
				if(count==0)out.write("<p>None</p>");
				out.write("</div>");
			}
			
	
			out.write("<div>");
			out.println("<h2>Top Performers of all time:</strong> </h2>");

			ArrayList<Score> top = board.getTopPerformers();
			out.write("<table>");
			out.write("<tr>");
			out.write("<th>User</th><th>Date Taken</th><th>Score</th><th>Time</th>");
			out.write("<tr>");
			for (Score sc : top) {
				String userInTable;
				User performer = ((AccountManager)session.getAttribute("am")).getAccount(sc.user);
				if (performer.privacyOn() && cu != null && !cu.isFriends(performer.getID())) {
					userInTable = "Anonymous";
				} else {
					userInTable = sc.user;
				}
				out.write("<tr>");
				Date dt = new Date(sc.timetaken);
				out.println("<td>"+userInTable +"</td><td>"+ dt.toString()+"</td><td>"+sc.score+"</td><td>"+sc.timescore+"</td>");
				out.write("</tr>");
			}
			out.write("</table>");
			if(top.size()==0)out.write("<p>None</p>");
			out.write("</div>");
			
			out.write("<div>");
			out.println("<h2>Top Performers in last 15 minutes:</strong> </h2>");
			ArrayList<Score> recent = board.getTopRecentPerformers();
			out.write("<table>");
			out.write("<tr>");
			out.write("<th>User</th><th>Date Taken</th><th>Score</th><th>Time</th>");
			out.write("<tr>");
			for (Score sc : recent) {
				String userInTable;
				User performer = ((AccountManager)session.getAttribute("am")).getAccount(sc.user);
				if (performer.privacyOn() && cu != null && !cu.isFriends(performer.getID())) {
					userInTable = "Anonymous";
				} else {
					userInTable = sc.user;
				}
				out.write("<tr>");
				Date dt = new Date(sc.timetaken);
				out.println("<td>"+userInTable +"</td><td>"+ dt.toString()+"</td><td>"+sc.score+"</td><td>"+sc.timescore+"</td>");
				out.write("</tr>");
			}
			out.write("</table>");
			if(recent.size()==0)out.write("<p>None</p>");
			out.write("</div>");

			
			out.write("<div>");
			out.println("<h2>All recent test takers(last 15 minutes):</strong> </h2>");
			out.write("<table>");
			out.write("<tr>");
			out.write("<th>User</th><th>Date Taken</th><th>Score</th><th>Time</th>");
			out.write("<tr>");
			ArrayList<Score> recentall = board.getRecentPerformers();
			for (Score sc : recentall) {
				String userInTable;
				User performer = ((AccountManager)session.getAttribute("am")).getAccount(sc.user);
				if (performer.privacyOn() && cu != null && !cu.isFriends(performer.getID())) {
					userInTable = "Anonymous";
				} else {
					userInTable = sc.user;
				}
				out.write("<tr>");
				Date dt = new Date(sc.timetaken);
				out.println("<td>"+userInTable +"</td><td>"+ dt.toString()+"</td><td>"+sc.score+"</td><td>"+sc.timescore+"</td>");
				out.write("</tr>");
			}
			out.write("</table>");
			if(recentall.size()==0)out.write("<p>None</p>");
			out.write("</div>");
			
			out.write("<div>");
			out.write("<table>");
			out.write("<tr>");
			out.write("<th>User</th><th>Date Taken</th><th>Score</th><th>Time</th>");
			out.write("<tr>");
			out.println("<h2>All test takers:</strong> </h2>");
			for (Score sc : board.getUsers()) {
				String userInTable;
				User performer = ((AccountManager)session.getAttribute("am")).getAccount(sc.user);
				if (performer.privacyOn() && cu != null && !cu.isFriends(performer.getID())) {
					userInTable = "Anonymous";
				} else {
					userInTable = sc.user;
				}
				out.write("<tr>");
				Date dt = new Date(sc.timetaken);
				out.println("<td>"+userInTable +"</td><td>"+ dt.toString()+"</td><td>"+sc.score+"</td><td>"+sc.timescore+"</td>");
				out.write("</tr>");
			}
			out.write("</table>");
			if(board.getUsers().size()==0)out.write("<p>None</p>");
			out.write("</div>");
			
			
			double totalScore = 0;
			double totalTime = 0;
			for (Score sc : board.getUsers()) {
				totalTime += sc.timescore;
				totalScore += sc.score;	
			}
			double averageScore = totalScore/board.getUsers().size();
			double averageTime = totalTime/board.getUsers().size();
			out.write("<h2>Average Score: " + ((double)((int)(averageScore*100)))/100 + " Average Time: " + ((double)((int)(averageTime*100)))/100 + " s</h2>");

			
			if (request.getParameter("temp") == null && username.equals(currentuser)) {
				out.println("<form action=\"DisplayQuiz.jsp\" method=\"post\">"); 
				out.println("<input type=\"submit\" class=\"btn btn-primary\" value = \"Edit Quiz\"/>");
				out.println("</form>"); 
			} 
			
			
      out.write("\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t");
 
			
			if (request.getParameter("temp") == null) {					
				out.println("<form action=\"TakeQuizServlet\" method=\"post\">");
				out.println("<input type=\"submit\" class=\"btn btn-primary\" value = \"Take Quiz\"/>");
				out.println("</form>");
			}

			
      out.write(" \t\t\t\t\n");
      out.write("\t\t\t\t<form action=\"ListQuizzes.jsp\" method=\"post\">\n");
      out.write("\t\t\t\t");

				if (request.getParameter("temp") != null || request.getAttribute("temp") != null) {					
					out.println("<input type=\"hidden\" value=\"true\" name=\"temp\"/>");
					request.setAttribute("temp", true);
				}
				
      out.write("\n");
      out.write("\t\t\t\t  <input type=\"submit\" class=\"btn btn-primary\" value = \"Back\"/>\n");
      out.write("\t\t\t\t</form>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\n");
      out.write("\t \t</div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
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
