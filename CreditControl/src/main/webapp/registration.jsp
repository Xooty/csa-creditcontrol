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
	<div class="loginbox" style="position: absolute; width: 500px; height:500px;">
		<img src="image/profil.png"
			style="position: absolute; bottom: 93%; right: 40%; width: 20%">
			<p style="position: absolute; bottom: 77%; right: 45%; width: 20%; font-size: 30px">Registrierung</p>
			<p style="position: absolute; bottom: 74%; right: 18%; width: 50%; font-size: 17px">Bitte legen Sie ein Konto an:</p>
		<form action="UserRegisterServlet" method="post">
			<p style="position: absolute; bottom: 64%; right: 64%;">Nachname</p>
			<input type="text" name="nachname"				
				style="border-radius: 3px; border: none;width: 150px;height: 25px; position: absolute; bottom:61%;right: 54%;" required>
			<p style="position: absolute; bottom: 64%; right: 24%;">Vorname</p>
			<input type="text" name="vorname"				
				style="border-radius: 3px; border: none;width: 150px;height: 25px; position: absolute; bottom:61%;right: 14%;" required>
			<p style="position: absolute; bottom: 49%; right: 39%;">E-Mail-Adresse</p>
			<input type="email" name="email"				
				style="border-radius: 3px; border: none;width: 250px;height: 25px; position: absolute; bottom:45%;right: 25%;" required>	
			<p style="position: absolute; bottom: 31%; right: 60%;">Benutzername</p>
			<input type="text" name="username"
				placeholder="Benutzername eingeben"
				style="border-radius: 3px; border: none;width: 150px;height: 25px; position: absolute; bottom:28%;right: 54%;" required>	
			<p style="position: absolute; bottom:31%;right: 29%;">Passwort</p>
			<input type="password" name="password" placeholder="••••••••••"
				style="border-radius: 3px; border: none;width: 150px;height: 25px; position: absolute; bottom:28%;right: 14%;" required> 
			<input type="submit" value="registrieren"
			style="border-radius: 3px; border: none; position: absolute; bottom: 10%; right: 35%; width: 30%;height: 25px;">
		</form>
	</div>
</body>