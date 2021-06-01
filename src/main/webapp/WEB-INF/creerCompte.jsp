<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un compte</title>
</head>
<body>
	<%@ include file="./component/header.jspf" %>
	
	<h1>Mon profil</h1>
	<form action="./ServletConnexion" method="post">
		<input type="text" name="prenom" placeholder="Votre prénom" required>
		<input type="text" name="nom" placeholder="Votre nom" required>
		<input type="text" name="pseudo" placeholder="Votre pseudo" required>
		<input type="email" name="mail" placeholder="Votre adresse mail" required>
		<input type="tel" name="prenom" placeholder="Votre prénom" required>
		<input type="text" name="prenom" placeholder="Votre prénom" required>
		<input type="text" name="prenom" placeholder="Votre prénom" required>
		<input type="text" name="prenom" placeholder="Votre prénom" required>
		<input type="text" name="prenom" placeholder="Votre prénom" required>
		<input type="text" name="prenom" placeholder="Votre prénom" required>
	</form>
	
	<%@ include file="./component/footer.jspf" %>
</body>
</html>