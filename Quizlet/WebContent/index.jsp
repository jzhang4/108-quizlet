<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<title>Home - Quizzler</title>

	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="CSS/common.css">
	<link rel="stylesheet" href="CSS/nav-bar.css">
	<link rel="stylesheet" href="CSS/index.css">
</head>

<body>
	<div id=header>
		<ul>
		  <li class="name"><a href="index.jsp">Quizzler</a></li>
		  <li><a href="TheTeam.html">Meet the Team</a></li>
		  <li><a href="HomepageLogin.html">User Login</a></li>
		</ul>
		
		<div id="innerHeader">
			<h1>Ready To Get Those Neurons Firing?</h1>
			<h2><a href="CreateNewAccount.html" class="sign_up">Sign Up</a> and see what it's all about!</h2>
		</div>
	</div>
	<div id = "information">
		<h1> Why Quizzle? </h1>
		<div class = "item"> 
			<img src = "./indexImages/BrainLoves.png"/>
			<span class = "caption">Give your brain a workout!</span>
		</div>
		<div class = "item"> 
			<img src = "./indexImages/mobileAbility.png"/>
			<span class = "caption">You can take it anywhere!</span>
		</div>
		<div class = "item"> 
			<img src = "./indexImages/friends-icon.png" height = "300px"/>
			<span class = "caption">Go head to head with friends</span>
		</div>
	</div>
	<div id = "recentComments">
		<h1> What Others Have Been Saying ...</h1>
		<blockquote>
			Quizzlet really made the administration of tests SO much easier!
			<cite>Patrick Young</cite>
		</blockquote>
		
		<blockquote id = "centralQuote">
			One of the best applications we have seen in a decade. Quizzlet is
			really revolutionising the way we train our brain.
			<cite>The New York Times</cite>
		</blockquote>
		<div class = "twitterbox">
			<a class="twitter-timeline" href="https://twitter.com/hashtag/Quizzler" data-widget-id="707101829281124353">#Quizzler Tweets</a> <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
		</div>
	</div>
	<div id = "footer">
		<p>Quizzler&reg; and its Associates are owned fully by CookieDevelopers</p>
		<p> Copyright 1995 - 2016 </p>
	</div>
</body>
</html>