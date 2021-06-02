<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>
	<%@ include file="./component/header.jspf" %>
	
	<%
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies){
			if (c.getName().equals("loginLOSNA")){
				String login = c.getValue();
			}
		}
	%>
	
	<h1>Se connecter</h1>
	<h2>${login}</h2>
	<form action="./ConnexionServlet" method="post">
	<label for="id">Votre e-mail</label>
	
	<c:if test="${!empty login}">
		<input type="email" name="mail" value="${login}">
	</c:if>
	
	<c:if test="${empty login}">
		<input type="email" name="mail">
	</c:if>
	
	
	<label for="mdp">Mot de passe</label>
	<input type="password" name="mdp">
	<input type="checkbox" name="seSouvenirDeMoi">
	<label for="seSouvenirDeMoi">Se souvenir de moi</label>
	<button type="submit">Connexion</button>
	<a href="./motDePasseOublie">Mot de passe oublié</a>
	</form>
	
	<p><a href="./creerProfil">Créer un compte</a></p>
	
	<%@ include file="./component/footer.jspf" %>
</body>
</html>