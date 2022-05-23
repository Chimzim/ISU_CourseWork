function validation2() {
//Variables that will hold the validation result
    var emailCheck = true;
	var phoneCheck = true;
	
//to check if validation form is right
    var emailNotify = emailCheckfunc(document.forms["personalInformation"]["email"].value);
    var phoneNotify = phoneNumCheck(document.forms["personalInformation"]["phone"].value);

	//Holds the values entered by the user and then passes them into the functions for validation checking 
    var imageEmail = getImage(emailCheckfunc(document.forms["personalInformation"]["email"].value), "email");
	var imagePhone = getImage(phoneNotify, "phone");

//error message counting		
   var count = 0;
   var errorEmail = getNotification(Boolean(emailNotify), "email", count);
   count += 1;
   errorPhone = getNotification(Boolean(phoneNotify), "phone", count);
   count = 0;
   //Appends then images into the form 
    document.getElementById("Email").appendChild(imageEmail);
    document.getElementById("Email").appendChild(errorEmail);
	
    document.getElementById("Phone").appendChild(imagePhone);
    document.getElementById("Phone").appendChild(errorPhone);

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
    label.innerHTML = bool ? "" : "Please enter a valid email";
	}
	else if(counter == 1) {
		label.innerHTML = bool ? "" : "Please enter an email in the format xxx-xxx-xxxx or xxxxxxxxxx";
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

function emailCheckfunc(email) {
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
//check to see if the input is consisting of only numbers
function numCheck(isNum) {
	let regex = /[!@#$%^&*()_+\=\[\]{};':"\\|,.<>\/?/a-zA-z]/g;
	if(isNum != null && !isNum.match(regex) ) {
		return true;
	} else {
		return false;
	}
}
//checks to see if the input matches the required format of xxx-xxx-xxxx or xxxxxxxxxx
function phoneNumCheck(userInput) {
	numCheck(userInput);
	if(userInput.includes("-")&& userInput.length == 12) {
		return true;
	} else if(userInput.length == 10){
		return true;
	}else {
		return false;
	}
}


function deleteCookie( name ) {
  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

function alphaNumCheck(entry) {
    let regex = /^[a-z0-9]+$/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }
}