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
						<%Collection<Annonce> annParticipe = user.getParticipeIci(); %>
						<%Collection<Annonce> annCree = user.getAnnonces(); %>
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
						<h2>Bonjour, <%=user.getPseudo() %></h2>
																			
						<p>Nom d'utilisateur : <%=user.getPseudo() %><br/>
						Nom : <%=user.getNom() %><br/>
						Prenom : <%=user.getPrenom() %><br/>
						<a href="serv1?op=modifier" class="button"/>Modifier le profil</a></p><br/>
					</header>
				</section>						

			</section>
			
			
			<section class="box special features">
					<div class="features-row">
						<section>
							<span class="icon major fa-bolt accent2"></span>
							<h3>Prochaines annonces :</h3>
							<p><%for(Annonce p : annParticipe){%>
							<li>
							<b><%=p.getNom().toString()%></b><br/>
							</li>
						<%}%></p>
						</section>
						
						<section>
							<span class="icon major fa-area-chart accent3"></span>
							<h3>Annonces déposées par moi :</h3>
							<p><%for(Annonce p : annCree){%>
							<li>
							<b><%=p.getNom().toString()%></b><br/>
							</li>
						<%}%></p>
						</section>
					</div>
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