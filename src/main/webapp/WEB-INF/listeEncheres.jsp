<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Les Objets Sont Nos Amis - Liste des enchères</title>
<meta name="description" content="Découvrez les enchères « Les objets sont nos amis » ! Une plateforme web pour permettre la cession d’objets de seconde main sans transaction financière.">
</head>
<body>
	<%@ include file="./component/header.jspf" %>
	<h1>Liste des enchères</h1>
	<h2>Filtres :</h2>
	<form action="/FiltrageServlet" method="post">
		<input type="text" name="nomArticle" placeholder="Le nom de l'article contient...">
		<label for="categorie">Catégorie</label>
		<select name="categorie">
			<option value="all" selected>Toutes</option>
			<!-- Ajouter dynamiquement les <option> en fonction de la table Categorie -->
		</select>
		<button type="submit">Rechercher</button>
	</form>
	<section id="liste-encheres">
		<div class="enchere">
			<img alt="une image" src="">
			<h4><a>Titre de l'annonce</a></h4>
			<p>Prix : 210 points</p>
			<p>Fin de l'enchère : 10/08/2021</p>
			<p>Vendeur : <a>jojo44</a></p>
		</div>
	</section>
	<%@ include file="./component/footer.jspf" %>
</body>
</html>