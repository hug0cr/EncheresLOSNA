<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style_XL.css" rel="stylesheet">
<c:if test="${param.type == 'new' }">
	<title>Nouvelle annonce</title>
</c:if>
<c:if test="${param.type != 'new'}">
	<title>Modifier une annonce</title>
</c:if>
</head>
<body>
	<%@ include file="./component/header.jspf"%>

	<c:if test="${param.type == 'new'}">
		<!-- Nouvelle annonce -->
		<h1>Nouvelle annonce</h1>
		<form action="./ArticleServlet?type=crea" method="post">
			<label for="nom">Article : </label>
			<input type="text" name="nom" placeholder="Le nom de l'article" required autofocus>
			<label for="description">Description : </label>
			<textarea rows="10" cols="30" name="description" placeholder="La description de l'article" required></textarea>
			<label for="categorie">Catégorie : </label>
			<select name="categorie" required>
				<option value="0" disabled selected>-- Choisissez une catégorie --</option>
			<c:forEach items="${categories}" var="c">
				<option value="${c.noCategorie}">${c.libelle}</option>
			</c:forEach>
			</select>
			<label for="prix">Mise à prix : </label>
			<input type="number" name="prix" required>
			<label for="dateDebut">Début des enchères</label>
			<input type="date" name="dateDebut" required>
			<label for="dateFin">Fin des enchères</label>
			<input type="date" name="dateFin" required>
			<fieldset>
				<legend>Retrait</legend>
				<label for="rue">Rue : </label>
				<input type="text" name="rue" placeholder="N° et nom de rue" required value="${user.rue}">
				<label for="codePostal">Code postal : </label>
				<input type="text" name="codePostal" placeholder="Votre code postal" required value="${user.codePostal}">
				<label for="ville">Ville : </label>
				<input type="text" name="ville" placeholder="Votre ville" required value="${user.ville}">
			</fieldset>
			<button type="submit">Enregistrer</button>
			<a href="./listeEncheres">Annuler</a>
		</form>
	</c:if>
	<c:if test="${param.type != 'new'}">
		<!-- Modifier annonce -->
		<h1>Modifier une annonce</h1>
		<form action="./ArticleServlet?type=modif" method="post">
			<label for="nom">Article : </label>
			<input type="text" name="nom" placeholder="Le nom de l'article" required autofocus>
			<label for="description">Description : </label>
			<textarea rows="10" cols="30" name="description" placeholder="La description de l'article" required></textarea>
			<label for="categorie">Catégorie : </label>
			<select name="categorie" required>
				<option value="0" disabled selected>-- Choisissez une catégorie --</option>
			<c:forEach items="${categories}" var="c">
				<option value="${c.noCategorie}">${c.libelle}</option>
			</c:forEach>
			</select>
			<label for="prix">Mise à prix : </label>
			<input type="number" name="prix" required>
			<label for="dateDebut">Début des enchères</label>
			<input type="date" name="dateDebut" required>
			<label for="dateFin">Fin des enchères</label>
			<input type="date" name="dateFin" required>
			<fieldset>
				<legend>Retrait</legend>
				<label for="rue">Rue : </label>
				<input type="text" name="rue" placeholder="N° et nom de rue" required value="${user.rue}">
				<label for="codePostal">Code postal : </label>
				<input type="text" name="codePostal" placeholder="Votre code postal" required value="${user.codePostal}">
				<label for="ville">Ville : </label>
				<input type="text" name="ville" placeholder="Votre ville" required value="${user.ville}">
			</fieldset>
			<button type="submit">Enregistrer</button>
			<a href="./listeEncheres">Annuler</a>
		</form>
	</c:if>

	<%@ include file="./component/footer.jspf"%>
</body>
</html>