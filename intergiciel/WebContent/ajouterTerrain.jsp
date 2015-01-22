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
					<%if  ((Integer) request.getAttribute("status") == 1) {%>
					<p>Le sport, ça se pratique à plusieurs ! <br/></p>
					<%} else { %>
					<p>Votre requête est invalide !<br/>
					Remplissez bien tous les champs</p>
					<%} %>
			</section>



		<!-- Main -->
			<section id="main" class="container">
		
				<section class="box special">
					<header class="major">
						<h2>Ajoutez un terrain</h2>
						<form method="post" action="serv1">
							<input type="hidden" name="op" value="ajTerrain" />	
								<%if ((Integer)request.getAttribute("status") == 2) {%>
								<span style="color : red">Ce terrain existe déjà</span><br/>	
								<%} %>	
								Nom du terrain : <input type= "text" name="nom" /> <br/>		
								Lieu : <input type= "text" name="lieu" /> <br/>
								Sport principal : <input type= "text" name="sport" /> <br/>	
								Est il privé : 
								<br/>
								<input class="css-checkbox" type="radio" name="prive" value="True">Oui<br>
								<input class="css-checkbox" type="radio" name="prive" value="False">Non	
								<br/>
								<%if ((Integer)request.getAttribute("status") == 0) {%>
								<span style="color : red">Erreur, réessayez</span><br/>	
								<%} %>	
								
							<input type="submit" value="Ajouter le terrain" />
						</form>
					</header>
					<span class="image featured"><img src="images/sports-motivational.jpg" alt="" /></span>
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
					<li>&copy; Co sport. All rights reserved.</li>
				</ul>
			</footer>

	</body>
</html>