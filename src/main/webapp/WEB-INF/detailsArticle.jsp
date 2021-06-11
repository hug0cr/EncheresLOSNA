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
<main>
	<%@ include file="./component/header.jspf" %>
	<c:if test="${!empty classe}">
		<p class="${classe}">${message}</p>
	</c:if>
	<img alt="al" src="img\MSI_HUGO - Jet Ski Tenerife.jpg">
	<h1>${article.nomArticle}</h1>
	<h2>Description</h2>
	<p>${article.description}</p>
	<h2>Catégorie</h2>
	<p>${categorie.libelle}</p>
	<h2>Meilleure offre</h2>
	<p>${article.prixVente}</p>
	<h2>Mise à prix</h2>
	<p>${article.miseAPrix}</p>
	<h2>Fin des enchères</h2>
	<p><fmt:formatDate value="${article.dateFinEncheres }"/></p>
	<h2>Retrait</h2>
	<p>Adresse de retrait</p>
	<h2>Vendeur</h2>
	<p><a href="./ProfilServlet?user=${article.vendeur.noUtilisateur}">${article.vendeur.pseudo}</a></p>
	<h2>Ma proposition</h2>
	<form action="./EncherirServlet?article=${article.noArticle}" method="post">
		<input type="number" name="enchere" id="inputEnchere">
		<button type="submit">Encherir</button>
	</form>
	
	<%@ include file="./component/footer.jspf" %>
	</main>
</body>
</html>