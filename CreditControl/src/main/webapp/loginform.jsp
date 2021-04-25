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
		<img src="image/profil.png" style="position: absolute;bottom: 93%;right:40%;width: 20%">
		<form>
			<p>Username</p>
			<input type="text" name="username" placeholder="Enter Username" style="border-radius: 3px; border: none;"required>
			<p>Password</p>
			<input type="password" name="password" placeholder="••••••••••" style="border-radius: 3px; border: none;"required> 
			<input type="submit" value="Sign In" style="border-radius: 3px; border: none;width: 25%;height: 15%" onclick="window.location.href='http://localhost:8080/CreditControl/website.jsp';">
			<input type="submit" value="Registration" style="border-radius: 3px; border: none;position: absolute;bottom: 20%;right:58%;width: 30%">
			</form>
		</div>
		
		
		 
	
</body>