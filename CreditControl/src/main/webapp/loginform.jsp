<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>CreditControl</title>
</head>
<body>
	<img src="image/tree.png" width="100%" style="opacity: 0.2;">
	<div class="loginbox">
		<img src="image/profil.png"
			style="position: absolute; bottom: 93%; right: 40%; width: 20%">
		<form action="UserLoginServlet" method="post">
			<p>Benutzername</p>
			<input type="text" name="username"
				placeholder="Benutzernamen eingeben"
				style="border-radius: 3px; border: none;width: 150px;height: 25px;" required>
			<p>Passwort</p>
			<input type="password" name="password" placeholder="••••••••••"
				style="border-radius: 3px; border: none;width: 150px;height: 25px;" required> 
				<input type="submit" value="Anmelden" style="border-radius: 3px; border: none; position: absolute; bottom: 28%; right: 58%; width: 30%">
		</form>
		<input type="submit" value="Registrierung"
			style="border-radius: 3px; border: none; position: absolute; bottom: 20%; right: 58%; width: 30%">
	</div>
</body>