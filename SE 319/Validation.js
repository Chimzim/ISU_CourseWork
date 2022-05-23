
function validateButton() {
//these values will correspond to the value the user enters for the respective fields
//Frist name, Last name, Gender, State origin
	var userInput0 = document.forms["Fname"]["firstname"].value;
	var userInput1 = document.forms["Lname"]["lastname"].value;
	var userInput2 = document.forms["orien"]["gender"].value;
	var userInput3 = document.forms["donde"]["states"].value;
	alert(userInput0);

	var vfirst, vlast, vgender,vstate, i = 0, errorMessage;
//This block of code will validate the requirements for the firstname and the Last name 
vfirst = userInput0.includes("1","2","3","4","5","6","7","8","9");
vlast = userInput1.includes("!","@","#","$","%","^","&","*","(",")","_","+","?","/",";",":","=","`","~","{","[","}","]","<",">");
vgender = userInput2.includes("Male","Female");
vstate = userInput3.includes("Arizona", "California", "Colorado", "Florida", "Hawaii", "Iowa", "New York", "Texas", "Virginia", "Washington");
if(vfirst == false) {
	errorMessage = "Please make sure the first name field only contains characters\n";
	i += 1;
}
if(vlast == false) {
	errorMessage += "Please make sure the last name field only contains characters or numeric characters\n";
	i += 1;
}
if(vgender == false) {
	errorMessage += "Please make sure you choose either Male or Female\n";
	i += 1;
}
if(vstate == false) {
	errorMessage += "Please make sure you choose one of the state options";
	i += 1;
}
if(i >0) {
	alert(errorMessage);
}
document.getElementById("test").innerHTML = "https://pixnio.com/free-images/flora-plants/fruits/strawberries-pictures/strawberry-fruit-406x544.jpg" alt="strawberry";
}

function getvfirst() {
	if(vfirst == true) {
		vfirst = 
	return vfirst;
}

function getvlast() {
	return vlast;
}

function getvgender(){
	return vgender;
}

function getvstate() {
	vstate = "https://pixnio.com/free-images/flora-plants/fruits/strawberries-pictures/strawberry-fruit-406x544.jpg" alt="strawberry";
	return vstate;
}