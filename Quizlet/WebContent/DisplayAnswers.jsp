<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "quiz.Quiz"%>
<%@ page import= "quiz.Question"%>
<%@ page import= "quiz.MAQuestion"%>
<%@ page import= "java.util.*, user.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Display quiz</title>
<!--  	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="CSS/common.css">
	<link rel="stylesheet" href="CSS/login-formatting.css">-->
	
	
	<link rel="stylesheet" href="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
	<%
		Quiz quiz = (Quiz)(session.getAttribute("quiz"));
	%>
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
			
			<%
			out.println("<h1>Answers for <strong>"+ quiz.getName() + "</strong></h1>");
			out.println("<p><strong>Description: </strong>"+quiz.getDescription()+"</p>");
			out.println("<h2>Questions and Answers:</h2>");
			Iterator<Question> it = quiz.iterator();
			int index = 1;
			while (it.hasNext()) {
				Question q = it.next();
				String ques = q.getQuestion();
				out.println("<p>Type: "+q.stringType(q.getType()) + "</p>");
			
				if (q.getType() == Question.PICTURE_RESPONSE) {
					out.println("<p>Question "+index + "</p>");
					out.println("<img src=\""+ques + "\" alt = \""+ques+"\" style = \"width:128px;height:128px;\">");
				} else out.println("<p>Question "+index + ": "+ques + "</p>");
				
				if (q.getType() == Question.MULTIPLE_ANSWER) {
					MAQuestion maq = (MAQuestion)q; 
					String ordered = Boolean.toString(maq.ordered());
					int numanswers = maq.getNumAnswers();
					out.println("<p>Ordered: "+ordered + "</p>");
					out.println("<p>Number of Required Answers: " + numanswers +"</p>");
				}
				
				ArrayList<String> answers = q.getAnswer().getAnswer();
				out.println("<p> Answer(s): ");
				for (String str : answers) {
					out.println(str+"\n");
				}
				out.println("</p>");
				if (q.getType() == Question.MULTIPLE_CHOICE) {
					ArrayList<String> choices = q.getChoices();
					out.println("<p> Choices(s): ");
					for (String str : choices) {
						out.println(str+"\n");
					}
					out.println("</p>");
				}
				index++;
				
			}
			
			%>
			
			<form action="SendChallengeServlet" method="post">
			  Send Challenge to a Friend: <!--  <input type="text" name="recipient"/> -->
			  <select name="recipient">
			  <%
			  	User cu = ((AccountManager)session.getAttribute("am")).getAccount((String)session.getAttribute("user"));
			  	for (Integer id : cu.getFriends()) {
			  		User friend = ((AccountManager)session.getAttribute("am")).getAccount(id);
			  		out.println("<option value=\"" + friend.getUserName() + "\">" + friend.getUserName() + "</option>");
			  	}
			  
			  	out.println("<input name=\"quizname\" type=\"hidden\" value=\"" + quiz.getName() + "\"/>");
				out.println("<input name=\"score\" type=\"hidden\" value=\"" + session.getAttribute("score") + "\"/>");
			  %>
			  </select>
			  <input type="submit" name="Send" class="btn btn-primary" value = "Send"/>
			</form>
			<div class="ct-chart ct-perfect-fourth" style="height: 400px; width: 600px;">
				<%
					ArrayList<Integer> rawValues = new ArrayList<Integer>();
					for (int i = 0; i < 100; i ++){				// this code is not tested! USE WITH CAUTION
						rawValues.add(i);
					}
					int maxValue = Collections.max(rawValues);
					int bucketSize = maxValue/4;
					int numFirstBucket = 0;
					int numSecondBucket = 0; 
					int numThirdBucket = 0;
					int numFourthBucket = 0;
					for (int i = 0; i < rawValues.size(); i++){
						if (rawValues.get(i) >= 0 && rawValues.get(i) <= bucketSize){
							numFirstBucket++;
						} else if (rawValues.get(i) > bucketSize && rawValues.get(i) <= 2 * bucketSize){
							numSecondBucket++;
						} else if (rawValues.get(i) > 2 * bucketSize && rawValues.get(i) <= 3 * bucketSize){
							numThirdBucket++;
						} else {
							numFourthBucket++;
						}
					}
					String firstBucketLabel = "0 - " + Integer.toString(bucketSize);
					String secondBucketLabel = Integer.toString(bucketSize) + " - " + Integer.toString(2*bucketSize);
					String thirdBucketLabel = Integer.toString(2*bucketSize) + " - " + Integer.toString(3*bucketSize);
					String fourthBucketLabel = Integer.toString(3*bucketSize) + " - " + Integer.toString(4*bucketSize);
					
					// time to make the chart
					out.write ("<script>");
					out.write("new Chartist.Bar('.ct-chart', {");
					out.write("labels: [\'" + firstBucketLabel + "\',\'" +secondBucketLabel + "\',\'" + thirdBucketLabel + "\',\'" + fourthBucketLabel + "\'],");
					out.write("series:[20,20,20,20]");
					//out.write("series: [" + Integer.toString(numFirstBucket) + "," + Integer.toString(numSecondBucket) +"," +Integer.toString(numThirdBucket) + "," + Integer.toString(numFourthBucket) + "]");
					out.write("}, {");
					out.write("fullWidth: true,");
					out.write("chartPadding: {");
					out.write("right: 40");
					out.write("}");
					out.write("});");
					
					out.write("</script>");
				
				
				%>
				</div>
						
			<form action="ListQuizzes.jsp">
			  <input type="submit" class="btn btn-primary" value = "Back to Quizzes"/>
			</form>
			
	 	</div>
	</div>

</body>
</html>