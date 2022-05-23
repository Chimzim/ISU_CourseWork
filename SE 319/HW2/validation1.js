function validation1() {
	//Variables that will hold the validation result
        var fnameCheck = true;
	var lnameCheck = true;
	var genderCheck = true;
	var stateCheck = true;
	
	//Holds the values entered by the user and then passes them into the functions for validation checking and then into image creation
        var imageFname = getImage(checkFirst(document.forms["personalInformation"]["firstname"].value), "firstname");
	var imageLname = getImage(checkLast(document.forms["personalInformation"]["lastname"].value), "lastname");
	var imageGender = getImage(checkGender(document.forms["personalInformation"]["gender"].value), "gender");
	var imageState = getImage(checkStates(document.forms["personalInformation"]["states"].value), "states");

    //to check if validation form is right
    var firstNotify = checkFirst(document.forms["personalInformation"]["firstname"].value);
    var lastNotify = checkLast(document.forms["personalInformation"]["lastname"].value);
    var genderNotify = checkGender(document.forms["personalInformation"]["gender"].value);
    var statesNotify = checkStates(document.forms["personalInformation"]["states"].value);

   //gets the notification message. uses a counter to grab the right notification message based on the form list.	
   var count = 0;
   var errorFirst = getNotification(Boolean(firstNotify), "firstname", count);
   count += 1;
   errorLast = getNotification(Boolean(lastNotify), "lastname", count);
   count += 1;
   errorGender = getNotification(Boolean(genderNotify), "gender", count);
   count += 1;
   var errorStates = getNotification(Boolean(statesNotify), "states", count);
   count = 0;
   //Appends then images into the form and then after appends the error message
    document.getElementById("First").appendChild(imageFname);
    document.getElementById("First").appendChild(errorFirst);
	
    document.getElementById("Last").appendChild(imageLname);
    document.getElementById("Last").appendChild(errorLast);
	
    document.getElementById("Orientation").appendChild(imageGender);
    document.getElementById("Orientation").appendChild(errorGender);
	
    document.getElementById("Donde").appendChild(imageState);
    document.getElementById("Donde").appendChild(errorStates);

    if(firstNotify == true && lastNotify == true){
		if(genderNotify == true && statesNotify == true) {
		//go to the next page
		window.location.assign("./Validation2.html");
		}
	}

}
//makes sure the input follows an alpha numeric format
function checkFirst(first) {
	if(alphaNumCheck(first)) {
		return true;
	}
	else {
		return false;
	}
}
//makes sure no specail characters are allowed
function checkLast(last) {
	if(!containsSpecailChar(last)) {
	     return false;
	}
	return true;
}
//created an array and checks to see if the input matches any object in the array
function checkGender(orientation) {
	var gen = ['Male', 'Female'];
	if(gen.indexOf(orientation) == -1) {
		return false;
	}
	return true;
}
//created an array and checks to see if the input matches any object in the array
function checkStates(origin) {
	var names = ['Arizona', 'California', 'Colorado', 'Florida', 'Hawaii', 'Iowa', 'NewYor', 'Texas', 'Virginia', 'Washington'];
	if(names.indexOf(origin) == -1) {
		return false;
	}
	return true;
}

function getNotification(bool, ID, counter) {
    var label = document.getElementById("labelNotify" + ID);
    if (label == null) {
        label = document.createElement("LABEL");
        label.id = "labelNotify" + ID;
        // label.className = "errorMessage";
        label.setAttribute( 'class', 'errorMessage' );
      }
	if(counter == 0) {
    label.innerHTML = bool ? "" : "Please make sure the firstname contains only alphabetic or numeric characters";
	}
	else if(counter == 1) {
		label.innerHTML = bool ? "" : "Please make sure the firstname contains only alphabetic or numeric characters";
	}
	else if(counter == 2) {
		label.innerHTML = bool ? "" : "Please select from the drop down";
	}
	else if(counter == 3) {
		label.innerHTML = bool ? "" : "Please select from the drop down";
	}
    return label;
}


function getImage(bool, ID) {
    var image = document.getElementById("image" + ID);
    if (image == null) {
        image = new Image(15, 15);
        image.id = "image" + ID;
    }
    image.src = bool ? './correct.png' : './wrong.png';
    return image;
}

function emailCheck(email) {
    atSplit = email.split('@');
    if (atSplit.length == 2 && alphaNumCheck(atSplit[0])) {
        periodSplit = atSplit[1].split('.')
        if (periodSplit.length == 2 && alphaNumCheck(periodSplit[0] + periodSplit[1])) {
            return true;
        }
    }
    valCheck = false;
    return false;
}

function alphaNumCheck(entry) {
    let regex = /^[C-Za-z0-9]+$/g;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }
}
//Checks to see if the String consist of any specail characters which are listed inside the function
function containsSpecailChar(userInput) {
	let regex = /[ !@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/g;
	if(userInput != null && !userInput.match(regex) ) {
		return true;
	} else {
		return false;
	}
}


function deleteCookie( name ) {
  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}
