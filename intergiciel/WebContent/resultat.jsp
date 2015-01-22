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
				<h1><a href="serv1?op=index">Co Sport</a> by us</h1>
				<nav id="nav">
				<%Personne user = (Personne) request.getAttribute("User"); %>
					<ul>
						<li><a href="serv1?op=index">Home</a></li>
						<%if (user != null) { %>
						<%Collection<Annonce> annParticipe = user.getParticipeIci(); %>
						<li><a href="" class="icon fa-angle-down"><%=user.getPseudo() %></a>
							<ul>
								<li><a href="serv1?op=profil">Profil</a></li>
								<li>
									<a href="serv1?op=mesAnnonces">Mes annonces</a>
									<ul>
										<%for (Annonce a : annParticipe) {%>
										<li><a href="serv1?op=afficherAnnonce&annonce=<%=a.getIdString()%>"><%=a.getNom() %></a></li>
										<%} %>
									</ul>									
								<li><a href="serv1?op=deconnecter">Me deconnecter</a></li>
							</ul>
						</li>
						<%} else {%>
						<li><a href="serv1?op=connectionjsp" class="button">Se connecter</a></li>
						<%} %>
					</ul>
				</nav>
			</header>

		<!-- Banner -->
			<section id="banner">
				<h2>Co Sport</h2>
					<p>Le sport, ça se pratique à plusieurs ! <br/></p>	
			</section>

		<!-- Main -->
			<section id="main" class="container">
		
				<section class="box special">
					<header class="major">
						<h2>Détails de l'annonce :</h2>
						
						<%Annonce ann = (Annonce) request.getAttribute("annonce"); %>
						<%Collection<Personne> participants = ann.getParticipants(); %>
						
						<p>
						<%if ((Integer)request.getAttribute("status") == 0) {%>	
						<span style="color : red">Désolé, vous ne pouvez pas participer.</span><br/>	
						<%} %>
						<b><%=ann.getNom() %></b>
						<ul>
						<li> Déposée par : <%=ann.getDeposeur().getPseudo() %></li>
						<li> Date : <%=ann.getDate() %></li>
						<li> Lieu : <%=ann.getLieu().getNom() %></li>
						<li> Sport : <%=ann.getSport().getNom() %></li>
						<li> Terrain : <%=ann.getTerrain().getNom() %></li>
						<li> Participants (<%=participants.size() %>/<%=ann.getNbMaxParticipant() %>)
							<ul>
							<%for(Personne p : participants){%>
								<li>
								<b><%=p.getPseudo()%></b><br/>
								</li>
							<%}%>
							</ul>
						</li>
						
						</ul></p>
					</header>
					<%if (user == null) {%>
					<p><a href="serv1?op=connectionjsp" class="button">Participer</a><br/>
					</p>
					<%} else { 
					if (ann.getTerrain().getIsPrivate()) {%>
					<p><a href="serv1?op=payer&annonce=<%=ann.getId() %>" class="button">Participer</a><br/>
					<%} else { %>
					<p><a href="serv1?op=participer&annonce=<%=ann.getId() %>" class="button">Participer</a><br/>
					</p>
					<%} }%>
					<span class="image featured"><img src="images/ban2.jpg" alt="" /></span>
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
					<li>&copy; Cosport. All rights reserved.</li>
				</ul>
			</footer>

	</body>
</html>