<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mot de passe oublié</title>
</head>
<body>
    <form action="./ConnexionServlet" method="POST">
        <label for="mail">Votre email</label>
        <input type="email" name="mail" required autofocus>
        <button type="submit">Envoyer</button>
    </form>
</body>
</html>