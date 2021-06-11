<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/style_XL.css" type="text/css" rel="stylesheet">
<title>${article.nomArticle}</title>
</head>
<body>
<%@ include file="./component/header.jspf" %>
	<main>
	<c:if test="${!empty classe}">
		<p class="${classe}">${message}</p>
	</c:if>
	<img alt="al" src="img\MSI_HUGO - Jet Ski Tenerife.jpg">
	<h1>${article.nomArticle}</h1>
	<section id="section-description" class="glass">
		<h2 id="titre-description">Description</h2>
		<p id="description">${article.description}</p>
	</section>
	<section id="infos-article">
		<div class="glass">
			<h2 id="titre-categorie">Catégorie</h2>
			<p id="categorie">${categorie.libelle}</p>
		</div>
		<div class="glass">
			<h2 id="titre-meilleure-offre">Meilleure offre</h2>
			<p id="meilleure-offre">${article.prixVente}</p>
		</div>
		<div class="glass">
			<h2 id="titre-mise-a-prix">Mise à prix</h2>
			<p id="mise-a-prix">${article.miseAPrix}</p>
		</div>
		<div class="glass">
			<h2 id="titre-date-fin">Fin des enchères</h2>
			<p id="date-fin"><fmt:formatDate value="${article.dateFinEncheres }"/></p>
		</div>
		<div class="glass">
			<h2 id="titre-retrait">Retrait</h2>
			<p id="retrait">Adresse de retrait</p>
		</div>
		<div class="glass">
			<h2 id="titre-vendeur">Vendeur</h2>
			<p id="vendeur"><a href="./ProfilServlet?user=${article.vendeur.noUtilisateur}" class="underline">${article.vendeur.pseudo}</a></p>
		</div>
	</section>
	<section>
		<h2 id="titre-proposition">Ma proposition</h2>
		<form action="./EncherirServlet?article=${article.noArticle}" method="post" id="proposition">
			<input type="number" name="enchere" id="inputEnchere">
			<button type="submit" class="button">Encherir</button>
		</form>
	</section>
	</main>
	<%@ include file="./component/footer.jspf" %>
</body>
</html>
