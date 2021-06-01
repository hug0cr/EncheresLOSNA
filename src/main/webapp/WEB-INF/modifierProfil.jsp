<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier le profil</title>
</head>
<body>
	<%@ include file="./component/header.jspf" %>
	
	<h1>Mon profil</h1>
	
	<!-- Ajouter des value dans les champs pour afficher les infos lorsqu'on les a -->
	
	<form action="./ProfilServlet?type=modif" method="post">
		<label for="prenom">Prénom : </label>
		<input type="text" name="prenom" placeholder="Votre prénom" required>
		<label for="nom">Nom : </label>
		<input type="text" name="nom" placeholder="Votre nom" required>
		<label for="pseudo">Pseudo : </label>
		<input type="text" name="pseudo" placeholder="Votre pseudo" required>
		<label for="mail">Email : </label>
		<input type="email" name="mail" placeholder="Votre adresse mail" required>
		<label for="phone">Téléphone : </label>
		<input type="tel" name="phone" placeholder="Votre n° de téléphone" required>
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
	<p><a href="./listeEncheres">Annuler</a></p>
	
	<%@ include file="./component/footer.jspf" %>
</body>
</html>