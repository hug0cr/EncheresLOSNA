<%@page import="fr.eni.encheresLOSNA.bll.ArticleVenduManager"%>
<%@page import="fr.eni.encheresLOSNA.bll.UtilisateurManager"%>
<%@page import="fr.eni.encheresLOSNA.bo.ArticleVendu"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Les Objets Sont Nos Amis - Liste des enchères</title>
<meta name="description"
	content="Découvrez les enchères « Les objets sont nos amis » ! Une plateforme web pour permettre la cession d’objets de seconde main sans transaction financière.">
</head>
<body>
	<%@ include file="./component/header.jspf"%>
	<h1>Liste des enchères</h1>




	<c:if test="${empty sessionScope.user}">
		<!-- Utilisateur non connecté -->
		<h2>Filtres :</h2>
		<form action="./FiltrageServlet" method="post">
			<input type="text" name="nomArticle" placeholder="Le nom de l'article contient...">
			<label for="categorie">Catégorie</label>
			<select name="categorie">
				<option value="all" selected>Toutes</option>
				<!-- Ajouter dynamiquement les <option> en fonction de la table Categorie -->
			</select>
			<button type="submit">Rechercher</button>
		</form>
		<section id="liste-encheres">

			<%
				ArticleVenduManager articleMgr = ArticleVenduManager.getInstance();
				List<ArticleVendu> lesArticles = articleMgr.getArticlesVendusTop50(); // A modifier avec getLast50Articles()
			%>

			<c:forEach items="${lesArticles }" begin="0" end="10" var="article">
				<div class="enchere">
					<img alt="une image" src="">
					<h4>${article.nomArticle}</h4>
					<p>Mise à prix : ${article.miseAPrix}</p>
					<p>Fin des enchères : ${article.dateFinEncheres}</p>
					<p>Vendeur : ${article.vendeur.pseudo}</p>
				</div>
			</c:forEach>

		</section>


	</c:if>




	<c:if test="${!empty sessionScope.user}">
		<!-- Utilisateur connecté -->
	</c:if>






	<%@ include file="./component/footer.jspf"%>
</body>
</html>