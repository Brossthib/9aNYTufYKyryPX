<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,cosport.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Co sport</title>
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
										<li><a href="serv1?op=afficherAnnonce&annonce=<%=a.getIdString()%>"><%=a.getNom() %></a></li>
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
						<h2>Remplir le formulaire pour déposer une annonce
						</h2>
						<form method="post" action="serv1">
							<input type="hidden" name="op" value="Deposer annonce" />
							Sport : <input type= "text" name="sport" /> <br/>
							Lieu : <input type ="text" name = "lieu" /> <br/>
							Nombre   maximum   de   participants : <input type ="text" name = "nb" /> <br/>
							<%if ((Integer)request.getAttribute("status") == 3) {%>
								<span style="color : red">Le terrain selectionné n'est pas dans la bonne ville !</span><br/>	
							<%} %>	
							Terrain : 
							<select name="terrain">
							<%for (Terrain t : (ArrayList<Terrain>) request.getAttribute("Lterrain")) { %>
								<option value="<%=t.getId()%>"><%=t.getNom()%> (à <%=t.getLieu().getNom() %>) </option>
								<%} %>
							</select><br/>
							Date : 
							<select name="jour">
							<%for (int i=1;i<=31; i++) { %>
								<option value="<%=i%>"><%=i%> </option>
								<%} %>
							</select>
							<select name="mois">
							<%for (int i=1;i<=12; i++) { %>
								<option value="<%=i%>"><%=i%> </option>
								<%} %>
							</select>
							<select name="annee">
							<%for (int i=2014;i<=2016; i++) { %>
								<option value="<%=i%>"><%=i%> </option>
								<%} %>
							</select><br/>
							Heure :<input type ="text" name = "heure" />h  <input type ="text" name = "minutes" />min<br/>
							<%if ((Integer)request.getAttribute("status") == 0) {%>
								<span style="color : red">Erreur, réessayez</span><br/>	
							<%} %>	
							<input type="submit" value="Deposer annonce" />
							</form>
							<p><a href="serv1?op=lister" class="button">Consulter les annonces</a></p>								
					</header>
					<span class="image featured"><img src="images/champion3.jpg" alt="" /></span>
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