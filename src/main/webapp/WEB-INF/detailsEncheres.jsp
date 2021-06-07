<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style_XL.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<header>
		<%@ include file="./component/header.jspf" %>
	</header>
	
	<main>
		<h1>Etat enchère</h1>
		<aside>La photo</aside>
		<div>
			<p>Nom article</p>
			<div>
				<p>Description : </p>
				<p>Article.description</p>
			</div>
			<div>
				<p>Meilleur offre : </p>
				<p>Article.prixVente</p>
			</div>
			<div>
				<p>Mise à prix : </p>
				<p>Article.MiseAPrix</p>
			</div>
			<div>
				<p>Retrait : </p>
				<p>Retrait.rue + retrait.codePostal + retrait.ville</p>
			</div>
			<div>
				<p>Vendeur : </p>
				<p>Article.description</p>
			</div>
			<div>
				<p>Tel : </p>
				<p>Article.description</p>
			</div>
		</div>
		
	</main>
	
	<footer>
		<%@ include file="./component/footer.jspf" %>
	</footer>
	
</body>
</html>