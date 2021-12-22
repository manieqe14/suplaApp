<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/main.css" type="text/css">
<script id="main-functions" src="/resources/main.js"></script>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<div id="info-view">
		<div id="info-view-content">
		 	Tu bedo jakies dane
		</div>
		<button id="close-info-view-window-button">Close</button>
	</div>
	<div id="main-container">
		<div id="second-container">
			<form action=searchBook>
				<input type="text" name="word"><br>
				<input type="submit"><br>
			</form>
			
			<div class="container">
				<button id="view-all-devices" onclick="showDevices()">View all devices</button>
				<button id="add-new-device" onclick="addNewDevice()">Add new Supla device</button>
			</div>
		</div>
	</div>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Lato:wght@100;300;400;700&display=swap" rel="stylesheet">
	<script>
		(function(){
			whenDomReady();
		})();
	</script>
</body>
</html>