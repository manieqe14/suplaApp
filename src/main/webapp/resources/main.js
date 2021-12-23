var field_active = false;
var deviceKeys = {},
    actionButton = {};

function whenDomReady(){
    document.getElementById('close-info-view-window-button').addEventListener('click', function(){
        console.log("OK");
        showWindow(false);
    });
	
}
function showDevices(){
	
	let req = new XMLHttpRequest();
	req.open('GET', document.location.origin + "/suplaDevices");
	req.setRequestHeader("Content-type", "Application/json");
	req.send();
	req.onload = function(){
		document.getElementById("info-view-content").innerHTML = generateTable(JSON.parse(this.responseText));
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
		
        let callback = function formulaFunctionsReturned(responseCode, data){
            if(responseCode == 200)
                showWindow(false);            
            else
                alert(data);
            
            document.getElementById("info-view").classList.remove("loading");
        }
        
        let data = formToJSON(document.getElementById("new-supla-device-form"));
        httpRequest(document.location.origin + "/suplaDevices/", data, 'POST', callback);
        
		
		
		
	});
}

function httpRequest(address, data, method, callback){
    
	let responseCode;
	let httpRequest = new XMLHttpRequest();
	httpRequest.open(method, address);
	httpRequest.setRequestHeader("Content-type", "application/json");
	httpRequest.onload = function(){
		/* console.log("response text: \n" + this.responseText);
		console.log("response code: \n" + this.status); */
        callback(this.status, this.responseText);
        //onSuplaDeviceUpdated(this.responseText);
	};
    httpRequest.send(data);
 
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
	deviceKeys = Object.keys(data[0]);
    
	deviceKeys.forEach(function(value){
		result+= '<td>'+ value + '</td>';
	});
	
	result+= '<td><!--space for button--></td></tr></thead><tbody>';
	
	data.forEach(function(singleItem){
		result+= '<tr device-id="' + singleItem.id + '">';
		
		deviceKeys.forEach(function(key){
			result+= '<td class="'+ key + '">' + singleItem[key] + '</td>';
		});
        result+= '<td><button class="edit-device" value="edit" onclick="editDevice(' + singleItem.id + ')">Edit</button><button class="delete-device" value="delete" onclick="deleteDevice(' + singleItem.id + ')">Delete</button></td>';
		result+= '</tr>';		
		
	});
	
	result+= '</tbody></table>';
	
	return result;
	
}

function deleteDevice(id){
    
    let callback = function(id){
        document.querySelector('tr[device-id="' + id + '"]').remove();
    }
    httpRequest(document.location.origin + "/suplaDevices/" + id, "", 'DELETE', callback);
}

function editDevice(id){
    
    editButton = document.querySelector('tr[device-id="' + id + '"] .edit-device');
    deleteButton = document.querySelector('tr[device-id="' + id + '"] .delete-device');
        
    if(editButton.value == 'edit'){
        document.querySelectorAll('tr[device-id="' + id +'"] td:not(:last-child):not(:first-child').forEach(function(item) {
            item.contentEditable = true;
            item.classList.add('editable-value');
        });
        
        editButton.innerHTML = "SAVE";
        editButton.value = 'save';
        deleteButton.style.display = "inline-block";
    }
    
    else if(editButton.value == 'save'){
        let device = {};
        deviceKeys.forEach(function(value){
            device[value] = document.querySelector('tr[device-id="' + id + '"] .' + value).innerHTML;
        });
        
        let callback = function onSuplaDeviceUpdated(data){
            let dataParsed = JSON.parse(data);
            
            document.querySelectorAll('tr[device-id="' + dataParsed.id +'"] td:not(:last-child):not(:first-child').forEach(function(item) {
                item.contentEditable = false;
                item.classList.remove('editable-value');
            });
               
            editButton.innerHTML = "EDIT";
            editButton.value = 'edit';
            deleteButton.style.display = "none";
        }
        
        
        if(validateDeviceFields(device))
            httpRequest(document.location.origin + "/suplaDevices/" + id, JSON.stringify(device), 'PUT', callback);
    }
}


function validateDeviceFields(device){
    if((device.brightness == 'true') || (device.brightness == 'false')){
        document.querySelector('tr[device-id="' + device.id + '"] .brightness').classList.remove('red-marked');
        
        if(validateIPaddress(device.address)){
            document.querySelector('tr[device-id="' + device.id + '"] .address').classList.remove('red-marked');
            return true;
        }
        else{
            document.querySelector('tr[device-id="' + device.id + '"] .address').classList.add('red-marked');
        }
    }
    else{
        document.querySelector('tr[device-id="' + device.id + '"] .brightness').classList.add('red-marked');
    }
    return false;
}

function validateIPaddress(ipaddress) {  
  if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(ipaddress)) {  
    return (true);
  }  
  return (false)  
} 
