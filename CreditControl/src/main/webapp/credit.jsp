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


	<div class="logo_container">
		<div class="lo">
			<img src="image/punkte.PNG">
		</div>
		<div class="lo">

			<img src="image/logo_ppt.JPG" width="65%">
		</div>
		<div class="lo">
			<button class="button_profil">
				<img src="image/profil.png" align="left">
				<p>Profil</p>
			</button>
		</div>
	</div>

	<div class="layoutstyle">
		<div class="layout_container">
			<div class="layout"></div>
		</div>
		<table style="position: relative; left: 600px; bottom: 300px; border-spacing: 15px;">
			<tr>
				<th></th>
				<th style="left: 45px; position: relative; font-family: Advent Pro;">Bitte
					füllen Sie aus:</th>
			</tr>
			<tr>
				<td><label class="label_antrag">Verwendungszweck</label></td>
				<td><input class="inputstyle" type="text"
					name="form_verwendungszweck"></input></td>
			</tr>
			<tr>
				<td><label class="label_antrag">Kreditbetrag</label></td>
				<td><input class="inputstyle" type="text"
					name="form_kreditbetrag"></input></td>
			</tr>
			<tr>
				<td><label class="label_antrag">Laufzeit</label></td>
				<td><input class="inputstyle" type="text" name="form_laufzeit"></input></td>
			</tr>
			<tr>
				<td><label class="label_antrag">Arbeitgeber</label></td>
				<td><input class="inputstyle" type="text"
					name="form_arbeitgeber"></input></td>
			</tr>
			<tr>
				<td><label class="label_antrag">Beschäftigungsverhältnis</label></td>
				<td><select class="select_antrag"
					style="position: relative; left: 50px">
						<option class="select_option" selected>Bitte wählen:</option>
						<option class="select_option" value="angestellter">Angestelle/r</option>
						<option class="select_option" value="arbeiter">Arbeiter/in</option>
						<option class="select_option" value="arbeitslos">Arbeitslos</option>
						<option class="select_option" value="beamter">Beamte/r</option>
						<option class="select_option" value="selbststanediger">Selbstständig/r</option>
						<option class="select_option" value="student">Student/in</option>
						<option class="select_option" value="rentner">Rentner/in</option>
				</select></td>
			</tr>
			<tr>
				<td><label class="label_antrag">Bruttoeinkommen</label></td>
				<td><select class="select_antrag"
					style="position: relative; left: 50px">
						<option class="select_option" selected>Bitte wählen:</option>
						<option class="select_option" value="low">unter 1500 €</option>
						<option class="select_option" value="middle">1501-2500 €</option>
						<option class="select_option" value="upper">2501-3500 €</option>
						<option class="select_option" value="top">über 3500 €</option>
				</select></td>
			</tr>
		</table>

		<div class="lo">
			<button class="button_antrag" type="submit">
				<img src="image/häkchen.ico" align="left">
				<p>Antrag abschicken</p>
			</button>
		</div>
	</div>
</body>
</html>