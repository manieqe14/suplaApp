var field_active = false;

function whenDomReady(){
	console.log("DOM READY");
	
}
function showDevices(){
	
	let req = new XMLHttpRequest();
	req.open('GET', document.location.origin + "/suplaDevices");
	req.setRequestHeader("Content-type", "Application/json");
	req.send();
	req.onload = function(){
		document.getElementById("info-view-content").innerHTML = generateTable(JSON.parse(this.responseText)._embedded.suplaDevices);
		showWindow(true);
	};
}

function addNewDevice(){
	let newDeviceFormula = '<h2>New Device</h2><form id="new-supla-device-form">' 
		+ '<p class="input-field"><input type="text" name="name"><label for="name">Name</label></p>' 
		+ '<p class="input-field"><input type="text" name="address"><label for="address">Address URL</label></p>'
		+ '<p class="input-field">Brightness<br><span class="radio-group-field"><input type="radio" name="brightness" value="true"><label for="true">True</label></span>'
		+ '<span class="radio-group-field"><input type="radio" name="brightness" value="false"><label for="true">False</label></span></p>'  
		+ '<button id="create-new-supla-device" value="Create">Create</button></form>';
	
	document.getElementById("info-view-content").innerHTML = newDeviceFormula;
	showWindow(true);
	formulaFunctions();

}

//showing window
function showWindow(value){
	if(value == true)
	{
		document.getElementById("info-view").style.display = "block";
		document.getElementById("main-container").style.opacity = 0;
	}
	else{
		document.getElementById("info-view").style.display = "none";
		document.getElementById("main-container").style.opacity = 1;
	}
}


//function after window appeared
function formulaFunctions(){

	document.getElementById("create-new-supla-device").addEventListener('click', function(e){
		e.preventDefault();
		document.getElementById("info-view").classList.add("loading");
		
		let response = httpRequest(document.location.origin + "/suplaDevices/", formToJSON(document.getElementById("new-supla-device-form")));
		//console.log(formToJSON(document.getElementById("new-supla-device-form")));
		if(response[0] == '2'){
			alert("Success!");
			showWindow(false);
		}
		else{
			alert("error!");
		}
		
		document.getElementById("info-view").classList.remove("loading");
		
	});
}

function httpRequest(address, data){
	let responseCode;
	httpRequest = new XMLHttpRequest();
	httpRequest.open('POST', address);
	httpRequest.setRequestHeader("Content-type", "application/json");
	httpRequest.send(data);
	httpRequest.onload = function(){
		responseCode = this.status;
		console.log("response text: \n" + this.responseText);
		console.log("response code: \n" + responseCode);
	};
	
	return responseCode;
}

function formToJSON(form){
	let inputElements = form.getElementsByTagName("input");
	let result = {};
	
	for(var i = 0; i < inputElements.length; i++){
		let inputElement = inputElements[i];
		result[inputElement.name] = inputElement.value;
	}
	
	return JSON.stringify(result);
}

function generateTable(data){
	
	let result = '<table><thead><tr>';
	let keys = Object.keys(data[0]);
	keys.pop();
	keys.forEach(function(value){
		result+= '<td>'+ value + '</td>';
	});
	
	result+= '</tr></thead><tbody>';
	
	data.forEach(function(singleItem){
		result+= '<tr>';
		
		keys.forEach(function(key){
			result+= '<td>' + singleItem[key] + '</td>';
		});
		result+= '</tr>';		
		
	});
	
	result+= '</tbody></table>';
	
	return result;
	
}

