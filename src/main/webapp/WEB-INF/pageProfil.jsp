<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<c:if test="${requestScope.type == 'monProfil' }">
	<title>Mon profil</title>
</c:if>

<c:if test="${requestScope.type != 'monProfil' }">
	<title>Profil de</title>
</c:if>
</head>
<body>
	<%@ include file="./component/header.jspf"%>

	<c:if test="${Param.type == monProfil }">
		<h1>Mon profil</h1>
		<p>Pseudo :</p>
		<p>Nom :</p>
		<p>Prenom :</p>
		<p>Email :</p>
		<p>Téléphone :</p>
		<p>Rue :</p>
		<p>Code postal :</p>
		<p>Ville :</p>

		<c:if test="${!empty sessionScope.user }">
			<p>
				<a href="./gererProfil">Modifier</a>
			</p>
		</c:if>
	</c:if>

	<c:if test="${Param.type != monProfil }">
		<h1>Profil de </h1>
		<p>Pseudo :</p>
		<p>Nom :</p>
		<p>Prenom :</p>
		<p>Email :</p>
		<p>Téléphone :</p>
		<p>Rue :</p>
		<p>Code postal :</p>
		<p>Ville :</p>
	</c:if>



	<%@ include file="./component/footer.jspf"%>
</body>
</html>