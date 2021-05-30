<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="de.hwrberlin.creditcontrol.login.UserBean" %>
<jsp:useBean id="UserBean" class="de.hwrberlin.creditcontrol.login.UserBean" scope="session"/>
    <jsp:setProperty name="UserBean" property="*"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>CreditControl</title>

<script>
	function changeFuncPriv() {

		var selectBoxPriv = document.getElementById("box_priv");
		var selectedValuePriv = selectBoxPriv.options[selectBoxPriv.selectedIndex].value;
		
		var textareaPriv = document.getElementById("text_area_priv");
		var textareaBau = document.getElementById("text_area_bau");		
		var textareaProfil = document.getElementById("text_profil");

		if (selectedValuePriv == "1") {
			textareaPriv.style.display = "block";
			textareaBau.style.display = "none";
			textareaProfil.style.display= "none";
			
		} else {
			textareaPriv.style.display = "none";
		}

		if (selectedValuePriv == "2") {
			window.location = "credit.jsp";
		}

	}

	function changeFuncBau() {

		var selectBoxBau = document.getElementById("box_bau");

		var selectedValueBau = selectBoxBau.options[selectBoxBau.selectedIndex].value;

		var textareaBau = document.getElementById("text_area_bau");
		var textareaPriv = document.getElementById("text_area_priv");		
		var textareaProfil = document.getElementById("text_profil");

		if (selectedValueBau == "1") {
			textareaBau.style.display = "block";
			textareaPriv.style.display = "none";
			textareaProfil.style.display= "none";
		} else {
			textareaBau.style.display = "none";
		}

		if (selectedValueBau == "2") {
			window.location = "credit_mortage.jsp";
		}
				
	}
	
	function profil(){
		var textareaProfil = document.getElementById("text_profil");
		
		textareaProfil.style.display = "block";
	
		var textareaPriv = document.getElementById("text_area_priv");
		var textareaBau = document.getElementById("text_area_bau");
		
		textareaBau.style.display = "none";
		textareaPriv.style.display = "none";
	}

</script>

</head>

<body>
	<div
		style="position: absolute; left: 1705px; top: 90px; font-family: Advent Pro;">
		<p>
			Username:
			<%= UserBean.getUsername() %></p>
	</div>
	<div class="logo_container">
		<div class="lo">
			<img src="image/punkte.PNG">
		</div>
		<div class="lo">
			<img src="image/logo_ppt.JPG" width="65%">
		</div>
		<div class="lo">
			<button class="button_profil" onclick="profil();">
				<img src="image/profil.png" align="left">
				<p>Profil</p>
			</button>
			<div
				style="position: absolute; bottom: -45px; left: 110px; font-family: Advent Pro;">
				<a href="website.jsp"
					onClick="<%%>">Logout</a>
			</div>
		</div>
	</div>
	<div class="container_mid">
		<div class="con">
			<div class="con_style">Privatkredit</div>
			<div style="height: 15px;"></div>
			<select class="select_wrapper" id="box_priv"
				onchange="changeFuncPriv();">
				<option class="select_option" selected>Bitte wählen:</option>
				<option class="select_option" value="1" id="select_priv1">Voraussetzung</option>
				<option class="select_option" value="2" id="select_priv2">Kredit
					beantragen</option>
				<option class="select_option" value="3" id="select_priv3">Support</option>
			</select>
		</div>
		<div class="con">
			<div class="con_style">Baufinanzierung</div>
			<div style="height: 15px;"></div>
			<select class="select_wrapper" id="box_bau"
				onchange="changeFuncBau();">
				<option class="select_option" selected>Bitte wählen:</option>
				<option class="select_option" value="1" id="select_bau1">Voraussetzung</option>
				<option class="select_option" value="2" id="select_bau2">Kredit
					beantragen</option>
				<option class="select_option" value="3" id="select_bau3">Support</option>
			</select>
		</div>
	</div>


	<div
		style="background-image: url('image/hintergrund5.PNG'); opacity: 0.9">
		<div style="height: 450px; width: 100%;">
			<textarea id="text_area_priv"
				style="display: none; resize: none; width: 50%; height: 300px; position: absolute; left: 450px; top: 540px; font-family: Advent Pro; border: none; border-radius: 45px; font-size: 25px; text-align: center; background-color: transparent;"
				readonly="readonly">Ein Privatkredit wird als Kredit verstanden, welcher von Privatpersonen zur freien Verwendung genutzt wird.

			Voraussetzung für die Aufnahme eines Privatkredites:
			
			Mindestalter: 18 Jahre
			Kreditsumme Minimum: 1.000€
			Kreditsumme Maximum: 100.000€
			Laufzeit: variabel – max. 120 Monate
</textarea>
		</div>
		<div>
			<textarea id="text_area_bau"
				style="display: none; resize: none; width: 50%; height: 300px; position: absolute; left: 450px; top: 540px; font-family: Advent Pro; border: none; border-radius: 45px; font-size: 25px; text-align: center; background-color: transparent;"
				readonly="readonly">Eine Baufinanzierung bezeichnet die Anschaffung einer Immobilie auf Kredit. Dabei kann nicht nur die Immobilie selbst finanziert werden, sondern auch dazugehörige Grundstücke und Nebenanlagen.
				
Voraussetzung für die Aufnahme einer Baufinanzierung:
			
Mindestalter: 18 Jahre
Kreditsumme Minimum: 50.000€
Kreditsumme Maximum: 750.000€
Laufzeit: variabel - max. 420 Monate
</textarea>
		</div>

		<div id="text_profil"
			style="display: none; resize: none; width: 50%; height: 300px; position: absolute; left: 450px; top: 540px; font-family: Advent Pro; border: none; border-radius: 45px; font-size: 25px; text-align: center; background-color: transparent;">
		<p>Benutzername: <%= UserBean.getUsername() %></p>
		<p>Vorname: <%= UserBean.getFirstName() %></p>
		<p>Nachname: <%= UserBean.getLastName() %></p>
		<p>E-Mail-Adresse: <%= UserBean.getEmail() %></p>
		
		</div>
	</div>

</body>
</html>