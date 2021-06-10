<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<%@ include file="./component/header.jspf"%>
	<main id="connexion-panel" class="glass">
		<h1>Connexion</h1>
		<form action="./ConnexionServlet" method="post">
			<label for="inputMail">Votre e-mail</label>
			<c:if test="${!empty login}">
				<input type="email" name="mail" id="inputMail" value="${login}">
			</c:if>
			<c:if test="${empty login}">
				<input type="email" name="mail">
			</c:if>
			<label for="mdp">Votre mot de passe</label>
			<input type="password" name="mdp"> <input type="checkbox" name="seSouvenirDeMoi">
			<label for="seSouvenirDeMoi">Se souvenir de moi</label>
			<button type="submit" class="button">Se connecter</button>
		</form>
		<p><a id="mdpOublie" href="./motDePasseOublie" class="button">Mot de passe oublié</a></p>
		<p><a id="new-account" href="./gererProfil" class="button">Créer un compte</a></p>
	</main>
	<%@ include file="./component/footer.jspf"%>
</body>
</html>