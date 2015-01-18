<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,cosport.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>Co sport</title>
		<link rel="shortcut icon" href="images/rsz_cool.png" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery.dropotron.min.js"></script>
		<script src="js/jquery.scrollgress.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-wide.css" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
	</head>
	<body class="landing">

		<!-- Header -->
			<header id="header" class="alt">
				<h1><a href="index.html">Co Sport</a> by us</h1>
				<nav id="nav">
					<ul>
						<li><a href="index.html">Home</a></li>
						<li><a href="#" class="button">Sign Up</a></li>
					</ul>
				</nav>
			</header>

		<!-- Banner -->
			<section id="banner">
				<h2>Co Sport</h2>
				<p>Another fine responsive way to be awesome !!</p>
			</section>

		<!-- Main -->
			<section id="main" class="container">
		
				<section class="box special">
					<header class="major">
						<h2>Détails de l'annonce :</h2>
						
						<%Annonce ann = (Annonce) request.getAttribute("annonce"); %>
						<!-- <%Collection<Personne> participants = ann.getParticipants(); %>-->
						
						<p>
						<b><%=ann.getNom() %></b>
						<ul>
						<li> Déposée par : <%=ann.getDeposeur().getPseudo() %></li>
						<li> Date : 27/11/2016</li>
						<li> Lieu : <%=ann.getLieu().getNom() %></li>
						<li> Sport : <%=ann.getSport() %></li>
						<!-- 
						<li> Participants (<%=participants.size() %>/<%=ann.getNbMaxParticipant() %>)
							<ul>
							<%for(Personne p : participants){%>
								<li>
								<b><%=p.getPseudo()%></b><br/>
								</li>
							<%}%>
							</ul>
						</li>
						-->
						</ul></p>
					</header>
					<p><a href="pagePayement.html" class="button">Participer privé</a>
					<a href="serv1?op=participer?annonce=<%=ann.getId() %>" class="button">Participer public</a></p>
					<span class="image featured"><img src="images/pic01.jpg" alt="" /></span>
				</section>
									
			</section>
			

			
		<!-- Footer -->
			<footer id="footer">
				<ul class="icons">
					<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
					<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
					<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
					<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
					<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
					<li><a href="#" class="icon fa-google-plus"><span class="label">Google+</span></a></li>
				</ul>
				<ul class="copyright">
					<li>&copy; Cosport. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
				</ul>
			</footer>

	</body>
</html>