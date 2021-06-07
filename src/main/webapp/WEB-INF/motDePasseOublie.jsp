<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/style_XL.css" type="text/css" rel="stylesheet">
<title>Mot de passe oublié</title>
</head>
<body>
	<main>
		<form action="./ConnexionServlet" method="POST">
			<label for="mailOubli">Votre email</label> <input type="email"
				name="mailOubli" required autofocus>
			<button type="submit">Envoyer</button>
		</form>
	</main>
</body>
</html>