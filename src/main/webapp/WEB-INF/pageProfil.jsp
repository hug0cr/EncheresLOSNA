<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style_XL.css" rel="stylesheet">
<c:if test="${param.type == 'monProfil' }">
	<title>Mon profil</title>
</c:if>

<c:if test="${param.type != 'monProfil' }">
	<title>Profil de ${user.pseudo }</title>
</c:if>
</head>
<body>
	<%@ include file="./component/header.jspf"%>

	<c:if test="${param.type == 'monProfil' }">
		<h1>Mon profil</h1>
		<p>Pseudo : ${user.pseudo }</p>
		<p>Nom : ${user.nom }</p>
		<p>Prenom : ${user.prenom }</p>
		<p>Email : ${user.email }</p>
		<p>Téléphone : ${user.telephone }</p>
		<p>Rue : ${user.rue }</p>
		<p>Code postal : ${user.codePostal }</p>
		<p>Ville : ${user.ville }</p>

		<c:if test="${!empty sessionScope.user }">
			<p>
				<a href="./gererProfil">Modifier</a>
			</p>
		</c:if>
	</c:if>

	<c:if test="${param.type != 'monProfil' }">
		<h1>Profil de ${user.pseudo}</h1>
		<p>Nom : ${user.nom }</p>
		<p>Prenom : ${user.prenom }</p>
		<p>Email : ${user.email }</p>
		<p>Téléphone : ${user.telephone }</p>
		<p>Rue : ${user.rue }</p>
		<p>Code postal : ${user.codePostal }</p>
		<p>Ville : ${user.ville }</p>
	</c:if>



	<%@ include file="./component/footer.jspf"%>
</body>
</html>