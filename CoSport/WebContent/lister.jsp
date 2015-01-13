<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,cosport.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lister</title>
</head>
<body>
hojo
<%Collection<Annonce> ann = (Collection<Annonce>) request.getAttribute("annonces");%>

<% if (ann.isEmpty()){%>
	<p>coucou</p>
	<%} else { %>
	<p>hey</p>
	 
 <%}%>

<%for(Annonce p : ann){%>
	<p><%=p.getSport().toString()%></p>
	<p><%=p.getLieu().getNom()%></p>
	
<%}%>
	<p><a href="index.html"/>retour à l'index</p>
</body>
</html>