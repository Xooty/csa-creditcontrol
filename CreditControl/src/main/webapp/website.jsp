<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	if (selectedValuePriv == "1") {
		textareaPriv.style.display = "block";
		textareaBau.style.display = "none";
		
	} else {
		textareaPriv.style.display = "none";
	}

	if (selectedValuePriv == "2") {
		window.location = "loginform.jsp";
	}

}

//Funktion für das Drop-Down Menü, um auf die Baufinanzierung weitergeleitet zu werden bzw. die Textarea mit den Voraussetzungen als Anzeige erhalten
function changeFuncBau() {

	var selectBoxBau = document.getElementById("box_bau");

	var selectedValueBau = selectBoxBau.options[selectBoxBau.selectedIndex].value;

	var textareaBau = document.getElementById("text_area_bau");
	var textareaPriv = document.getElementById("text_area_priv");		

	if (selectedValueBau == "1") {
		textareaBau.style.display = "block";
		textareaPriv.style.display = "none";
	} else {
		textareaBau.style.display = "none";
	}

	if (selectedValueBau == "2") {
		window.location = "loginform.jsp";
	}
			
}

</script>

</head>

<body>
	<div class="logo_container">
		<div class="lo">
			<img src="image/punkte.PNG">
		</div>
		<div class="lo">
			<img src="image/logo_ppt.JPG" width="65%">
		</div>
		<div class="lo">	
			<!--Loginbutton -->							
			<button class="button_profil"				
				onclick="window.location.href='loginform.jsp';">			
				<img src="image/profil.png" align="left">
				<p>Anmelden</p>
			</button>
		</div>
	</div>
	<div class="container_mid">
		<div class="con">
			<div class="con_style">Privatkredit</div>
			<div style="height: 15px;"></div>
			<!--Drop-Down Menü Privatfinanzierung -->		
			<select class="select_wrapper" id="box_priv"
				onchange="changeFuncPriv();">
				<option class="select_option" value="0" selected disabled hidden>Bitte wählen:</option>
				<option class="select_option" value="1" id="select_priv1">Voraussetzung</option>
				<option class="select_option" value="2" id="select_priv2">Kredit
					beantragen</option>
			</select>
		</div>
		<div class="con">
			<div class="con_style">Baufinanzierung</div>
			<div style="height: 15px;"></div>
			<!--Drop-Down Menü Baufinanzierung -->		
			<select class="select_wrapper" id="box_bau"
				onchange="changeFuncBau();">
				<option class="select_option" value="0" selected disabled hidden>Bitte wählen:</option>
				<option class="select_option" value="1" id="select_bau1">Voraussetzung</option>
				<option class="select_option" value="2" id="select_bau2">Kredit
					beantragen</option>
			</select>
		</div>
	</div>
	<div
		style="background-image: url('image/hintergrund5.PNG'); opacity: 0.9">
		<div style="height: 450px; width: 100%;">
			<textarea id="text_area_priv"
				style="display: none; resize: none; width: 50%; height: 300px; position: relative; left: 450px; top: 50px; font-family: Advent Pro; border: none; border-radius: 45px; font-size: 25px;text-align: center;background-color: transparent;" readonly="readonly">Ein Privatkredit wird als Kredit verstanden, welcher von Privatpersonen zur freien Verwendung genutzt wird.

Voraussetzung für die Aufnahme eines Privatkredites:
			
Mindestalter: 18 Jahre
Kreditsumme Minimum: 1.000€
Kreditsumme Maximum: 100.000€
Laufzeit: variabel - max. 120 Monate
</textarea>
			<textarea id="text_area_bau"
				style="display: none; resize: none; width: 50%; height: 300px; position: relative; left: 450px; top: 50px; font-family: Advent Pro; border: none; border-radius: 45px; font-size: 25px;text-align: center;background-color: transparent;" readonly="readonly">Eine Baufinanzierung bezeichnet die Anschaffung einer Immobilie auf Kredit. Dabei kann nicht nur die Immobilie selbst finanziert werden, sondern auch dazugehörige Grundstücke und Nebenanlagen.
				
Voraussetzung für die Aufnahme einer Baufinanzierung:
			
Mindestalter: 18 Jahre
Kreditsumme Minimum: 50.000€
Kreditsumme Maximum: 750.000€
Laufzeit: min. 60 Monate - max. 420 Monate
</textarea>	
		
		</div>
	</div>
</body>
</html>