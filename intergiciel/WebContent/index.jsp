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
										<li><a href="#"><%=a.getNom() %></a></li>
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
				<p>Another fine responsive way to be awesome !!</p>
				<ul class="actions">
					<li><a href="inscription.html" class="button special">S'inscrire</a></li>
					<%if (user == null) { %><li><a href="serv1?op=connectionjsp" class="button">Se connecter</a></li><%} %><br/>
					<li><a href="serv1?op=deposer" class="button">Deposer une annonce</a></li>
					<li><a href="serv1?op=lister" class="button">Consulter les annonces</a></li><br/>	
					<li><a href="serv1?op=admin" class="button">Connection rapide (admin->a et user->b)</a></li>	<br/>
					<li><a href="serv1?op=annonce1" class="button">Deposer Annonce préfabriquée</a></li>									
				</ul>
			</section>



		<!-- Main -->
			<section id="main" class="container">
		
				<section class="box special">
					<header class="major">
						<h2>Introducing the ultimate co sport website
						<br />
						for practicing with strangers </h2>
						<p>
							Pratiquer votre sport favori n'a jamais été aussi simple !<br/>
							Inscrivez vous, postez votre annonce si elle n'existe pas déjà, et allez vous dégraisser !<br/>
						</p>
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
					<li>&copy; Co sport. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
				</ul>
			</footer>

	</body>
</html>