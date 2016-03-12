<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import = "administration.*"%>
<%@ page language="java" import ="java.util.ArrayList" %>

<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=1024">
		<title> Administration Home</title>
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link rel="stylesheet" href="CSS/common.css">
		<link rel="stylesheet" href="CSS/03.css">
		<link rel="stylesheet" href="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
		<link rel="stylesheet" href="CSS/admin-formatting.css">
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    	<script src="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
	</head>
	<script>
		$(document).ready(function(){
			$("#showTable").click(function(){
		        $("#data-table").show();
		        $("#bar-graph").hide();
		    });
		    $("#showBarGraph").click(function(){
		        $("#data-table").hide();
		        $("#bar-graph").show();
		    });
		    $("#showBoth").click(function(){
		    	$("#data-table").show();
		    	$("#bar-graph").show();
		    });
		});
	</script>
	<body>
		<div id=header>	
			<ul>				<!-- Blue bar at the top of the admin page, height is set in the CSS styling -->
				<li class="name"><a href="AdminHomePage.jsp">Quizzler</a></li>
				<li><a href="/Quizlet/LogoutServlet">Logout</a></li>
			</ul>
			<div id = "extra-large-inner-header">		<!-- Page header, CSS styles it with a solid colour background -->
				<h1>Administration</h1> 
					<div class = "leftSide"> <!-- Group short quick actions into an accordion bar  -->
						<h1>Quick Actions</h1>
						<div class="container">
					  		<div class="panel-group" id="accordion">	<!-- Based on an example from w3schools -->
					    		<div class="panel panel-default">
					      			<div class="panel-heading">
					        			<h4 class="panel-title">
					          				<a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Make A New Administrator</a>
					        			</h4>
					     			</div>
		      					<div id="collapse1" class="panel-collapse collapse in">
		        					<div class="panel-body">
		        						<form action ="./quickActionServlet" method = post>
		        				 			<input type = "text" name = "username" placeholder = "Enter the name of the user" size = "40">
		        				 			<input type = "submit" value = "Make Admin">
					        				 	<%
					        				 		if (request.getAttribute("successAdmin") != null){
					        				 			if ((int) request.getAttribute("successAdmin") == 1){
					        				 				out.write("<p>"+(String)request.getParameter("username") + "was succesfully made an admin");
					        				 			} else if ((int) request.getAttribute("successAdmin") == 0){
					        				 				out.write("<p>Failed to make "+(String)request.getParameter("username") + "an admin");
					        				 			}
					        				 		}
					        				 	%>
		        				 			<input name="quickActionSelected" type ="hidden" value ="admin">
		        						</form>
		        					</div>
		      					</div>
		    				</div>
						    <div class="panel panel-default">
						      	<div class="panel-heading">
						        	<h4 class="panel-title">
						          		<a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Create A New Announcement</a>
						       		</h4>
						     	 </div>
						      	<div id="collapse2" class="panel-collapse collapse">
						        	<div class="panel-body">
							        	<form action ="./quickActionServlet" method = post>
							        		Enter the text of the announcement and press 'Make Announcement':
							        		<textarea name = "newAnnouncement" cols = "50" rows = "6" >
							        		</textarea>
							        		<input type = "submit" value = "Make Annoucement">
							        		<input name="quickActionSelected" type ="hidden" value ="announce">
							        		<%
										 		if (request.getAttribute("successAnnounce") != null){
										 			if ((int) request.getAttribute("successAnnounce") == 1){
										 				out.write("<p>Announcement was succesfully made to the community");
										 			} else if ((int) request.getAttribute("successAnnounce") == 0){
										 				out.write("<p>Failed to make the announcement");
										 			}
										 		}
									 		%>
							        	</form>
						        	</div>
						      	</div>
						    </div>
					    	<div class="panel panel-default">
					      		<div class="panel-heading">
					        		<h4 class="panel-title">
					          			<a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Remove a User's Account</a>
					        		</h4>
					      		</div>
					      	<div id="collapse3" class="panel-collapse collapse">
					        	<div class="panel-body">
						        	<form action ="./quickActionServlet" method = post>
						        		<input type = "text" name = "userNameToRemove" placeholder = "Enter the name of the user" size = "40">
						        		<input type = "submit" value = "Remove User">
						        		<input name="quickActionSelected" type ="hidden" value ="remove">
						        		<%
										 	if (request.getAttribute("successRemove") != null){
										 		if ((int) request.getAttribute("successRemove") == 1){
										 			out.write("<p>User was succesfully removed from the community");
										 		} else if ((int) request.getAttribute("successRemove") == 0){
										 			out.write("<p>Failed to remove the user");
										 		}
										 	}
									 	%>
						        	</form>
					        	</div>
					      	</div>
					    </div>
		  			</div> 
		  			</br><!-- Just a break to separate our quiz remove section from everything else -->
		  		</div>
				<div class = "quizRemove">	
					<h1>Remove Quiz</h1>
					<form action = "./quickActionServlet" method = post>
						<input type = "text" name = "quizToRemove" placeholder = "Enter the name of the quiz to delete" size = "40">
						<input type = "submit" value = "Remove Quiz">
						<input name="quickActionSelected" type ="hidden" value ="removeQuiz">
					</form>
					<%
					   if (request.getAttribute("successRemoveQuiz") != null){
					        if ((int) request.getAttribute("successRemoveQuiz") == 1){
					        		out.write("<p>"+(String)request.getParameter("quizToRemove") + " was succesfully removed" + "</p>");
					        } else if ((int) request.getAttribute("successRemoveQuiz") == 0){
					        		out.write("<p>"+(String)request.getParameter("quizToRemove") + " could not be found or removed" + "</p>");
					        }
					   }
					%>
					<h1> Remove Quiz History</h1>
					<form action = "./quickActionServlet" method = post>
						<input type = "text" name = "quizToClear" placeholder = "Enter the name of the quiz to clear" size = "40">
						<input type = "submit" value = "Remove Quiz History">
						<input name = "quickActionSelected" type = "hidden" value = "removeQuizHistory">
					</form>
					<%
						if (request.getAttribute("successClearHistory") != null){
							if ((int) request.getAttribute("successClearHistory") == 1){
								out.write("<p>" + (String)request.getParameter("quizToClear") + " scoreboard was succesfully cleared </p>");
							} else if ((int) request.getAttribute("successClearHistory") == 0){
								out.write("<p>" + (String)request.getParameter("quizToClear") + " could not be found or failed to remove board </p>");	
							}
						}
					
					%>
				</div>
			</div>	
			<div id="wrapper">
				<h1> Site Statistics</h1>
				<% 
					Administrator adminLink = (Administrator) (request.getServletContext()).getAttribute("currentStats");
					if (adminLink != null) adminLink.getVisitFreq();
					if (adminLink == null) System.out.println("VALUES IS NULL LIAM, STUFF WENT WRONG");
				%>
				<p> Current Number of Users: <span class = "purple">  <b> <%out.write(Integer.toString(adminLink.getNumUsers())); %></b> </span> </p>
				<p> Current Number Of Quizzes: <span class = "purple"><b> <% out.write(Integer.toString(adminLink.getNumQuizzes())); %> </b> </span> </p>
				<button id="showTable">Show Table View</button>			<!--  Buttons that control how the data -->
				<button id="showBarGraph">Show BarGraph View</button>	<!--  is presented to the administrator -->
				<button id="showBoth"> Show Both Views</button>
				<div class="chart" id = "chart-access">
					<h2>Monthly Visits to Web Pages</h2>
					<table id="data-table" border="1" summary="This table shows the monthly visits to certain key web pages on the site">
						<caption>Numbers In Thousands</caption>
						<thead>
							<tr>
								<td>&nbsp;</td>
								<% 
									if (adminLink != null){
										ArrayList<String> namesOfMonth = adminLink.getNameOfMonths();
										for (int i = 0; i < namesOfMonth.size(); i++){
											String monthName = namesOfMonth.get(i);
											out.write("<th scope = \"col\">" +monthName +"</th>");
										}
									}
								%>
							</tr>				
						</thead>
						<tbody>
							<%
							String firstSite ="";
							String secondSite ="";
							String thirdSite ="";
							if (adminLink != null){
								ArrayList<PageInfo> pagesToDisplay = adminLink.getPages();
								for (int i = 0; i < pagesToDisplay.size(); i++){
									PageInfo temp = pagesToDisplay.get(i);
									out.write("<tr>");
									out.write("<th scope = \"row\">" + temp.pageName + "</th>");
									int numMonths = 7;
									String tempSite ="";
									for (int j = 0; j < numMonths; j++){
										out.write("<td>" + temp.fetchFreq(j) + "</td>");
										tempSite = tempSite + temp.fetchFreq(j);
										if (j != numMonths - 1){
											tempSite +=", ";
										} 
									}
									if (i == 0){
										firstSite = tempSite;
									} else if (i == 1){
										secondSite = tempSite;
									} else {
										thirdSite = tempSite;
									}
									out.write("</tr>");
								}
							}
							%>					
						</tbody>
					</table>
				</div>
				<div class="ct-chart ct-perfect-fourth" style="height: 400px; width: 600px;">
				<script>
				<%
					ArrayList<Integer> values43 = new ArrayList<Integer>();
					out.write("new Chartist.Bar('.ct-chart', {");
					out.write("labels: ['Sunday','Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday','Satuday'],");
					
					out.write("series: [");
					out.write("[" + firstSite + "],");
					out.write("[" + secondSite + "],");
					out.write("[" + thirdSite + "]");
					
					out.write("]");
					out.write("}, {");
					out.write("fullWidth: true,");
					out.write("chartPadding: {");
					out.write("right: 40");
					out.write("}");
					out.write("});");
				%>
				</script>
			</div>
			</div>
			</div>
		</div>
	</body>
</html>