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
<meta name="description" content="Découvrez les enchères « Les objets sont nos amis » ! Une plateforme web pour permettre la cession d’objets de seconde main sans transaction financière.">
<link href="css/style_Home.css" type="text/css" rel="stylesheet">
	<%@ include file="./component/header.jspf"%>
	<main>
		<h1>Liste des enchères</h1>
		<%@ include file="./component/search.jspf"%>
		<c:if test="${empty sessionScope.user}">
			<!-- Utilisateur non connecté -->
			<section class="liste-encheres">
				<c:forEach items="${lesArticles }" begin="0" end="10" var="article">
					<div class="glass">
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
			<section class="liste-encheres">
				<c:forEach items="${lesArticles }" begin="0" end="10" var="article">
					<div class="glass">
						<img alt="une image" src="">
						<h4>
							<a href="./ArticleServlet?article=${article.noArticle}">${article.nomArticle}</a>
						</h4>
						<p>Prix : ${article.prixVente} points</p>
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
	</main>
	<%@ include file="./component/footer.jspf"%>
</body>
</html>