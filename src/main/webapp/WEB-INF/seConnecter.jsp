<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>
	<%@ include file="./component/header.jspf" %>
	
	<h1>Se connecter</h1>
	<form action="./ConnexionServlet" method="post">
	<label for="id">Votre e-mail</label>
	<input type="text" name="mail">
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