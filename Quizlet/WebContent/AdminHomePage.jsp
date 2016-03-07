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
		<!--  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">-->
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link rel="stylesheet" href="./CSS/common.css">
		<link rel="stylesheet" href="./CSS/03.css">
		<link rel="stylesheet" href ="./CSS/HomePage.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
		<div id = "header">		<!-- Page header, CSS styles it with a solid colour background -->
			<h1><b><font size = "20"> Administration</font></b></h1> 
		</div>
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
	        				<form action ="${pageContext.request.contextPath}/quickActionServlet" method = post>
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
		        	<form action ="${pageContext.request.contextPath}/quickActionServlet" method = post>
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
	        	<form action ="${pageContext.request.contextPath}/quickActionServlet" method = post>
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
			<h1> Remove Quiz</h1>
		</div>
		<p> Table with dynamic search then a remove function for the quiz </p>
		
	</div>	
		<div id="wrapper">
			<h1> Site Statistics</h1>
			<% 
				Administrator values = (Administrator) session.getAttribute("currentStats");
				if (values != null) values.getVisitFreq();
				if (values == null) System.out.println("VALUES IS NULL LIAM, STUFF WENT WRONG");
			%>
			<p> Current Number of Users: <span class = "purple">  <b> <%out.write(Integer.toString(values.getNumUsers())); %></b> </span> </p>
			<p> Current Number Of Quizzes: <span class = "purple"><b> <% out.write(Integer.toString(values.getNumQuizzes())); %> </b> </span> </p>
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
								if (values != null){
									ArrayList<String> namesOfMonth = values.getNameOfMonths();
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
						if (values != null){
							ArrayList<PageInfo> pagesToDisplay = values.getPages();
							for (int i = 0; i < pagesToDisplay.size(); i++){
								PageInfo temp = pagesToDisplay.get(i);
								out.write("<tr>");
								out.write("<th scope = \"row\">" + temp.pageName + "</th>");
								int numMonths = 5;
								for (int j = 0; j < numMonths; j++){
									out.write("<td>" + temp.fetchFreq(j) + "</td>");
								}
								out.write("</tr>");
							}
						}
						%>					
					</tbody>
				</table>
			</div>
			<div class = "barGraph" id = "bar-graph">
			</div>
		</div>
		<div class ="barga" id = "asdasd">
			<h2>Current Quiz</h2>
		</div>
			
		<script src="animatedGraph.js"></script>
		
	</body>
</html>