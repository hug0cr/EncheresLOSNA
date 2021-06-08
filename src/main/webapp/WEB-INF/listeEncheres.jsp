<%@page import="fr.eni.encheresLOSNA.ihm.FiltrageServlet"%>
<%@page import="fr.eni.encheresLOSNA.bll.ArticleVenduManager"%>
<%@page import="fr.eni.encheresLOSNA.bll.UtilisateurManager"%>
<%@page import="fr.eni.encheresLOSNA.bo.ArticleVendu"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Les Objets Sont Nos Amis - Liste des enchères</title>
<meta name="description"
	content="Découvrez les enchères « Les objets sont nos amis » ! Une plateforme web pour permettre la cession d’objets de seconde main sans transaction financière.">
<link href="css/style_XL.css" type="text/css" rel="stylesheet">
</head>
<body>
	<main>
		<%@ include file="./component/header.jspf"%>
		<h1>Liste des enchères</h1>
		<h2>Filtres :</h2>
		<form action="./FiltrageServlet" method="post">
			<input type="text" name="motCle"
				placeholder="Le nom de l'article contient..."> <label
				for="categorie">Catégorie : </label> <select name="categorie"
				id="categorie" required>
				<option value="0" selected>Toutes</option>
				<c:forEach items="${categories}" var="c">
					<option value="${c.noCategorie}">${c.libelle}</option>
				</c:forEach>
			</select>
			<c:if test="${!empty sessionScope.user}">
				<c:if test="${mode == 'achat'}">
					<div id="modeAchat">
						<input type="checkbox" name="achat" id="<%= FiltrageServlet.ENCHERES_OUVERTE %>" value="<%= FiltrageServlet.ENCHERES_OUVERTE %>">
						<label for="<%= FiltrageServlet.ENCHERES_OUVERTE %>">Enchères ouvertes</label>
						<input type="checkbox" name="achat" id="<%= FiltrageServlet.ENCHERES_EN_COURS %>" value="<%= FiltrageServlet.ENCHERES_EN_COURS %>">
						<label for="<%= FiltrageServlet.ENCHERES_EN_COURS %>">Enchères en cours</label>
						<input type="checkbox" name="achat" id="<%= FiltrageServlet.ENCHERES_REMPORTEES %>" value="<%= FiltrageServlet.ENCHERES_REMPORTEES %>">
						<label for="<%= FiltrageServlet.ENCHERES_REMPORTEES %>">Enchères remportées</label>
					</div>
				</c:if>
				<c:if test="${mode == 'vente'}">
					<div id="modeVente">
						<input type="checkbox" name="vente" id="<%= FiltrageServlet.VENTES_EN_COURS %>" value="<%= FiltrageServlet.VENTES_EN_COURS %>">
						<label for="<%= FiltrageServlet.VENTES_EN_COURS %>">Mes ventes en cours</label>
						<input type="checkbox" name="vente" id="<%= FiltrageServlet.VENTES_NON_DEBUTEES %>" value="<%= FiltrageServlet.VENTES_NON_DEBUTEES %>">
						<label for="<%= FiltrageServlet.VENTES_NON_DEBUTEES %>">Ventes non débutées</label>
						<input type="checkbox" name="vente" id="<%= FiltrageServlet.VENTES_TERMINEES %>" value="<%= FiltrageServlet.VENTES_TERMINEES %>">
						<label for="<%= FiltrageServlet.VENTES_TERMINEES %>">Ventes terminées</label>
					</div>
				</c:if>
				<p><a href="./Controler?mode=achat">Mode achat</a></p>
				<p><a href="./Controler?mode=vente">Mode vente</a></p>
			</c:if>
			<button type="submit">Rechercher</button>
		</form>
		<c:if test="${empty sessionScope.user}">
			<!-- Utilisateur non connecté -->
			<section id="liste-encheres">
				<c:forEach items="${lesArticles }" begin="0" end="10" var="article">
					<div class="enchere">
						<img alt="une image" src="">
						<h4>${article.nomArticle}</h4>
						<p>Prix : ${article.prixVente} points</p>
						<p>
							Fin des enchères :
							<fmt:formatDate value="${article.dateFinEncheres }" />
						</p>
						<p>Vendeur : ${article.vendeur.pseudo}</p>
					</div>
				</c:forEach>
			</section>
		</c:if>
		<c:if test="${!empty sessionScope.user}">
			<!-- Utilisateur connecté -->
			<section id="liste-encheres">
				<c:forEach items="${lesArticles }" begin="0" end="10" var="article">
					<div class="enchere">
						<img alt="une image" src="">
						<h4>
							<a href="./ArticleServlet?article=${article.noArticle}">${article.nomArticle}</a>
						</h4>
						<p>Mise à prix : ${article.prixVente} points</p>
						<p>
							Fin des enchères :
							<fmt:formatDate value="${article.dateFinEncheres}" />
						</p>
						<p>
							Vendeur : <a
								href="./ProfilServlet?user=${article.vendeur.noUtilisateur}">${article.vendeur.pseudo}</a>
						</p>
					</div>
				</c:forEach>
			</section>
		</c:if>






		<%@ include file="./component/footer.jspf"%>
	</main>
</body>
</html>