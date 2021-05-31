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

//Funktion für das Drop-Down Menü, um auf die Privatfinanzierungsseite weitergeleitet zu werden bzw. die Textarea mit den Voraussetzungen als Anzeige erhalten
	function changeFuncPriv() {

		var selectBoxPriv = document.getElementById("box_priv");
		var selectedValuePriv = selectBoxPriv.options[selectBoxPriv.selectedIndex].value;
		
		var textareaPriv = document.getElementById("text_area_priv");
		var textareaBau = document.getElementById("text_area_bau");	
		var textareaSup = document.getElementById("text_area_sup");
		var buttonSup = document.getElementById("button_sup");
		var textAreaCreditsPriv = document.getElementById("text_creditsPriv");
		var textAreaCreditsBau = document.getElementById("text_creditsBau");

		var textareaProfil = document.getElementById("text_profil");

		if (selectedValuePriv == "1") {
			textareaPriv.style.display = "block";
			textareaBau.style.display = "none";
			textareaProfil.style.display= "none";
			textareaSup.style.display = "none";
			buttonSup.style.display = "none";
			textAreaCreditsPriv.style.display = "none";
			textAreaCreditsBau.style.display = "none";
			
		} else if(selectedValuePriv == "3"){
			textareaSup.style.display = "block";
			textareaBau.style.display = "none";
			textareaProfil.style.display= "none";
			textareaPriv.style.display = "none";
			buttonSup.style.display = "block";
			textAreaCreditsPriv.style.display = "none";
			textAreaCreditsBau.style.display = "none";
		
		}else {
			textareaPriv.style.display = "none";
		}
		
		if (selectedValuePriv == "2") {
			window.location = "credit.jsp";
		}

	}

//Funktion für das Drop-Down Menü, um auf die Baufinanzierung weitergeleitet zu werden bzw. die Textarea mit den Voraussetzungen als Anzeige erhalten
	function changeFuncBau() {

		var selectBoxBau = document.getElementById("box_bau");

		var selectedValueBau = selectBoxBau.options[selectBoxBau.selectedIndex].value;

		var textareaBau = document.getElementById("text_area_bau");
		var textareaPriv = document.getElementById("text_area_priv");	
		var textareaSup = document.getElementById("text_area_sup");
		var buttonSup = document.getElementById("button_sup");
		var textAreaCreditsPriv = document.getElementById("text_creditsPriv");
		var textAreaCreditsBau = document.getElementById("text_creditsBau");
	
		var textareaProfil = document.getElementById("text_profil");
	
		if (selectedValueBau == "1") {
			textareaBau.style.display = "block";
			textareaPriv.style.display = "none";
			textareaProfil.style.display= "none";
			textareaSup.style.display = "none";
			buttonSup.style.display = "none";
			textAreaCreditsPriv.style.display = "none";
			textAreaCreditsBau.style.display = "none";
			
		} else if(selectedValueBau == "3"){
			textareaSup.style.display = "block";
			textareaPriv.style.display = "none";
			textareaBau.style.display= "none";
			textareaProfil.style.display = "none";
			buttonSup.style.display = "block";
			textAreaCreditsPriv.style.display = "none";
			textAreaCreditsBau.style.display = "none";
			
		}else {
			textareaBau.style.display = "none";
		}

		if (selectedValueBau == "2") {
			window.location = "credit_mortage.jsp";
		}
				
	}
	
	<!--Funktion, um die anderen Textareas auszublenden, wenn der Profil-Button gedrückt wird -->		
	function profil(){
		var textareaProfil = document.getElementById("text_profil");
		
		textareaProfil.style.display = "block";
	
		var textareaPriv = document.getElementById("text_area_priv");
		var textareaBau = document.getElementById("text_area_bau");
		var textareaSup = document.getElementById("text_area_sup");
		var buttonSup = document.getElementById("button_sup");
		var textAreaCreditsPriv = document.getElementById("text_creditsPriv");
		var textAreaCreditsBau = document.getElementById("text_creditsBau");
			
		textareaSup.style.display = "none";
		textareaBau.style.display = "none";
		textareaPriv.style.display = "none";
		buttonSup.style.display = "none";
		textAreaCreditsPriv.style.display = "none";
		textAreaCreditsBau.style.display = "none";
	}
	
	//Funktion, um die anderen Textareas auszublenden und für die Ausgabe des Textfeldes einer aufgenommenen Baufinanzierung
	function showCreditsBau(){
			
		var textAreaCreditsBau = document.getElementById("text_creditsBau");
		
		textAreaCreditsBau.style.display = "block";
		
		var textareaProfil = document.getElementById("text_profil");
		var textareaPriv = document.getElementById("text_area_priv");
		var textareaBau = document.getElementById("text_area_bau");
		var textareaSup = document.getElementById("text_area_sup");
		var buttonSup = document.getElementById("button_sup");
		var textAreaCreditsPriv = document.getElementById("text_creditsPriv");
		
		textareaSup.style.display = "none";
		textareaBau.style.display = "none";
		textareaPriv.style.display = "none";
		textareaProfil.style.display = "none";
		buttonSup.style.display = "none";
		textAreaCreditsPriv.style.display = "none"
		
	}
	
	//Funktion, um die anderen Textareas auszublenden und für die Ausgabe des Textfeldes eines aufgenommenen Privatkredites
	function showCreditsPriv(){
		
		var textAreaCreditsPriv = document.getElementById("text_creditsPriv");
		
		textAreaCreditsPriv.style.display = "block";
		
		var textareaProfil = document.getElementById("text_profil");
		var textareaPriv = document.getElementById("text_area_priv");
		var textareaBau = document.getElementById("text_area_bau");
		var textareaSup = document.getElementById("text_area_sup");
		var buttonSup = document.getElementById("button_sup");
		var textAreaCreditsBau = document.getElementById("text_creditsBau");
		
		textareaSup.style.display = "none";
		textareaBau.style.display = "none";
		textareaPriv.style.display = "none";
		textareaProfil.style.display = "none";
		buttonSup.style.display = "none";
		textAreaCreditsBau.style.display = "none"
		
	}
</script>

</head>

<body>
	<!--Zeigt den Namen des eingeloggten Users an -->		
	<div
		style="position: absolute; left: 1705px; top: 90px; font-family: Advent Pro;">
		<p>Username: <%= UserBean.getUsername() %></p>
	</div>
	<div class="logo_container">
		<div class="lo">
			<img src="image/punkte.PNG">
		</div>
		<div class="lo">
			<img src="image/logo_ppt.JPG" width="65%">
		</div>
		<div class="lo">
		<!--Profil-Button -->		
			<button class="button_profil" onclick="profil();">
				<img src="image/profil.png" align="left">
				<p>Profil</p>
			</button>
			<!--Logout -->		
			<div
				style="position: absolute; bottom: -45px; left: 110px; font-family: Advent Pro;">
				<a href="website.jsp">Logout</a>
			</div>
			<!--Privatkredit anzeigen -->		
			<button class="button_profil" style="position: absolute; top:390px; left:110px" onclick="showCreditsPriv();">Privatkredit anzeigen</button>
			<!--Baufinanzierung anzeigen -->		
			<button class="button_profil" style="position: absolute; top:450px; left:110px" onclick="showCreditsBau();">Baufinanzierung anzeigen</button>
		</div>
			<div
				style="position: absolute; bottom: -45px; left: 110px; font-family: Advent Pro;">
				<a href="website.jsp">Logout</a>
			</div>
		</div>
	<div class="container_mid">
		<div class="con">
			<div class="con_style">Privatkredit</div>
			<div style="height: 15px;"></div>
			<!--Drop-Down Menü Privatfinanzierung -->		
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
			<!--Drop-Down Menü Baufinanzierung -->
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
			<!--Textarea für die Voraussetzung der Aufnahme von Privatkrediten-->
			<textarea id="text_area_priv"
				style="display: none; resize: none; width: 50%; height: 300px; position: absolute; left: 450px; top: 540px; font-family: Advent Pro; border: none; border-radius: 45px; font-size: 25px; text-align: center; background-color: transparent;"
				readonly="readonly">Ein Privatkredit wird als Kredit verstanden, welcher von Privatpersonen zur freien Verwendung genutzt wird.

			Voraussetzung für die Aufnahme eines Privatkredites:
			
			Mindestalter: 18 Jahre
			Kreditsumme Minimum: 1.000€
			Kreditsumme Maximum: 100.000€
			Laufzeit: variabel – max. 120 Monate
			Effektiver Jahreszins: 10 % p. a
			Gebundener Sollzinsatz: 3,99 % p. a
</textarea>
		</div>
		<div>
		<!--Textarea für die Voraussetzung der Aufnahme einer Baufinanzierung-->
			<textarea id="text_area_bau"
				style="display: none; resize: none; width: 50%; height: 300px; position: absolute; left: 450px; top: 540px; font-family: Advent Pro; border: none; border-radius: 45px; font-size: 25px; text-align: center; background-color: transparent;"
				readonly="readonly">Eine Baufinanzierung bezeichnet die Anschaffung einer Immobilie auf Kredit. Dabei kann nicht nur die Immobilie selbst finanziert werden, sondern auch dazugehörige Grundstücke und Nebenanlagen.
				
Voraussetzung für die Aufnahme einer Baufinanzierung:
			
Mindestalter: 18 Jahre
Kreditsumme Minimum: 50.000€
Kreditsumme Maximum: 750.000€
Laufzeit: min. 60 Monate - max. 420 Monate
Effektiver Jahreszins: 1,2 % p. a
Gebundener Sollzinsatz: 1,15 % p. a
</textarea>
		</div>
		
			<!--Supportfunktion mit Button -->
			<div style="height: 450px; width: 100%;">
			<textarea id="text_area_sup"
				style="display: none; resize: none; width: 50%; height: 300px; position: absolute; left: 450px; top: 540px; font-family: Advent Pro; border: none; border-radius: 45px; font-size: 25px; text-align: center"
				> Bitte schreiben Sie Ihr Anliegen in das Feld:	</textarea>
			<button id="button_sup" class="button_profil" style="position: absolute; left:1500px;bottom:120px; display: none;" onclick="javascript:alert('Vielen Dank für Ihr Anliegen! Die Nachricht wurde an creditcontrol@support.com weitergeleitet und wird zeitnah von einem Mitarbeiter bearbeitet.')">Anliegen abschicken</button>
			</div>
			
		<!--Anzeige der Profildaten -->	
		<div id="text_profil"
			style="display: none; resize: none; width: 50%; height: 300px; position: absolute; left: 450px; top: 540px; font-family: Advent Pro; border: none; border-radius: 45px; font-size: 25px; text-align: center; background-color: transparent;">
		<p>Benutzername: <%= UserBean.getUsername() %></p>
		<p>Vorname: <%= request.getSession().getAttribute("first_name") %></p>
		<p>Nachname: <%= request.getSession().getAttribute("last_name") %></p>
		<p>E-Mail-Adresse: <%= request.getSession().getAttribute("email") %></p>
		
		</div>
		<!--Anezeige der Details für die vom Nutzer aufgenommene Baufinanzierung -->		
		<div id="text_creditsBau"
			style="display: none; resize: none; width: 50%; height: 300px; position: absolute; left: 450px; top: 540px; font-family: Advent Pro; border: none; border-radius: 45px; font-size: 25px; text-align: center; background-color: transparent;">
		<p>Immobilienart:<%= request.getSession().getAttribute("") %></p>
		<p>Kredithöhe: <%= request.getSession().getAttribute("") %></p>
		<p>Laufzeit: <%= request.getSession().getAttribute("") %></p>
		<p>Ratenhöhe <%= request.getSession().getAttribute("") %></p>
		<p>Status der Genehmigung: <%= request.getSession().getAttribute("") %></p>
		</div>
		
		<!--Anezeige der Details für die vom Nutzer aufgenommenen Privatkredit -->	
		<div id="text_creditsPriv"
		style="display: none; resize: none; width: 50%; height: 300px; position: absolute; left: 450px; top: 540px; font-family: Advent Pro; border: none; border-radius: 45px; font-size: 25px; text-align: center; background-color: transparent;">
		<p>Verwendungszweck:<%= request.getSession().getAttribute("") %></p>
		<p>Kredithöhe: <%= request.getSession().getAttribute("") %></p>
		<p>Laufzeit: <%= request.getSession().getAttribute("") %></p>
		<p>Ratenhöhe <%= request.getSession().getAttribute("") %></p>
		<p>Status der Genehmigung: <%= request.getSession().getAttribute("") %></p>
		</div>
	</div>

</body>
</html>