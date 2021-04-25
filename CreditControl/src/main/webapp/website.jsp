<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>CreditControl</title>

<script>
	function changeFunc() {

		var selectBox = document.getElementById("box_priv");
		var selectedValue = selectBox.options[selectBox.selectedIndex].value;
		var textarea = document.getElementById("text_area");
		
		if (selectedValue == "1") {
			textarea.style.display = "block";
		} else {
			textarea.style.display = "none";
		}
		
		if (selectedValue =="2"){
			window.location="credit.jsp";
			
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
			<button class="button_profil" onclick="window.location.href='http://localhost:8080/CreditControl/loginform.jsp';">
				<img src="image/profil.png" align="left">
				<p>Profil</p>		
			</button>
		</div>
	</div>


	<div class="container_mid">
		<div class="con">
			<div class="con_style">Privatkredit</div>
			<div style="height: 15px;"></div>
			<select class="select_wrapper" id="box_priv" onchange="changeFunc();">
				<option class="select_option" selected>Bitte wählen:</option>
				<option class="select_option" value="1" >Voraussetzung</option>
				<option class="select_option" value="2" >Kredit beantragen</option>
				<option class="select_option" value="3">Support</option>
			</select>
		</div>
		<div class="con">
			<div class="con_style">Baufinanzierung</div>
			<div style="height: 15px;"></div>
			<select class="select_wrapper">
				<option class="select_option" selected>Bitte wählen:</option>
				<option class="select_option" value="kredit_voraussetzung">Voraussetzung</option>
				<option class="select_option" value="kredit_bau">Kredit
					beantragen</option>
				<option class="select_option" value="bau_support">Support</option>
			</select>
		</div>
	</div>

	<div
		style="background-image: url('image/hintergrund5.PNG'); opacity: 0.9">
		<div style="height: 450px; width: 100%;">
			<textarea name="text_area" id="text_area"
				style="display: none; resize: none; width: 100%; height: 300px; position: relative; left: 150px; top: 50px;">Voraussetzung für die Aufnahme eines Privatkredites: </textarea>
		</div>
	</div>
	

</body>





























</html>