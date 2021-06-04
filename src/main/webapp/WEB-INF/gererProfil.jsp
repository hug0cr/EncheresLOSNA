<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<c:if test="${empty sessionScope.user}">
	<title>Créer un profil</title>
</c:if>
<c:if test="${!empty sessionScope.user}">
	<title>Modifier mon profil</title>
</c:if>
</head>
<body>
	<%@ include file="./component/header.jspf"%>

	<c:if test="${empty sessionScope.user}">
		<h1>Créer un profil</h1>
		<form action="./ProfilServlet?type=crea" method="post">
			<label for="prenom">Prénom : </label>
			<input type="text" name="prenom" placeholder="Votre prénom" required>
			<label for="nom">Nom : </label>
			<input type="text" name="nom"placeholder="Votre nom" required> <label for="pseudo">Pseudo : </label>
			<input type="text" name="pseudo" placeholder="Votre pseudo"	required>
			<label for="mail">Email : </label>
			<input type="email" name="mail" placeholder="Votre adresse mail" required>
			<label for="phone">Téléphone : </label>
			<input type="tel" name="phone" placeholder="Votre n° de téléphone" pattern="[0-9]{10}" required>
			<label for="rue">Rue : </label>
			<input type="text" name="rue" placeholder="Votre rue" required>
			<label for="codePostal">Code postal : </label>
			<input type="text" name="codePostal" placeholder="Votre code postal" required>
			<label for="ville">Ville : </label>
			<input type="text" name="ville" placeholder="Votre ville" required>
			<label for="motDePasse">Mot de passe : </label>
			<input type="password" name="motDePasse" placeholder="Votre mot de passe" required>
			<label for="confirmation">Confirmation : </label>
			<input type="password" name="confirmation" placeholder="Confirmation" required>
			<button type="submit">Créer</button>
		</form>
		<p>
			<a href="./listeEncheres">Annuler</a>
		</p>
	</c:if>
	<c:if test="${!empty sessionScope.user}">
		<h1>Modifier mon profil</h1>
		<form action="./ProfilServlet?type=modif" method="post">
		<label for="prenom">Prénom : </label>
		<input type="text" name="prenom" value="${sessionScope.user.prenom }" placeholder="Votre prénom" required>
		<label for="nom">Nom : </label>
		<input type="text" name="nom" value="${sessionScope.user.nom }" placeholder="Votre nom" required>
		<label for="pseudo">Pseudo : </label>
		<input type="text" name="pseudo" value="${sessionScope.user.pseudo }" placeholder="Votre pseudo"	required>
		<label for="mail">Email : </label>
		<input type="email" name="mail" value="${sessionScope.user.email }" placeholder="Votre adresse mail" required>
		<label for="phone">Téléphone : </label>
		<input type="tel" name="phone" value="${sessionScope.user.telephone }" placeholder="Votre n° de téléphone" pattern="[0-9]{10}" required>
		<label for="rue">Rue : </label>
		<input type="text" name="rue" value="${sessionScope.user.rue }" placeholder="Votre rue" required>
		<label for="codePostal">Code postal : </label>
		<input type="text" name="codePostal" value="${sessionScope.user.codePostal }" placeholder="Votre code postal" required>
		<label for="ville">Ville : </label>
		<input type="text" name="ville" value="${sessionScope.user.ville }" placeholder="Votre ville" required>
		<label for="motDePasse">Nouveau mot de passe : </label>
		<input type="password" name="motDePasse" placeholder="Votre nouveau mot de passe">
		<label for="confirmation">Confirmation : </label>
		<input type="password" name="confirmation" placeholder="Confirmation">
		<button type="submit">Entegistrer</button>
		<p>
			<a href="./ProfilServlet?type=suppr">Supprimer le compte</a>
		</p>
		</form>
	</c:if>
	<%@ include file="./component/footer.jspf"%>
</body>
</html>