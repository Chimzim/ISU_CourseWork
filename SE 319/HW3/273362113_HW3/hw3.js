var rs = require('readline-sync');

var num1 = rs.question("1st Number: ");
var num2 = rs.question("2nd Number: ");
var num3 = rs.question("3rd Number: ");
var num4 = rs.question("4th Number: ");

//Output variables
var fact = 1, sum = 0, reverse = "", palindrome = true, i, j, z;
var sumLen = num2.length;


//gathers the factorial of the first number
var temp1 = Number(num1);
var temp2 = Number(num2);
for(i = temp1; i > 0 ; i--) {
	fact = fact * i;
}

//testing out the summation of the number
var placeHolder = temp2;
while(placeHolder > 0) {
	sum += Math.floor(placeHolder % 10);
	placeHolder = Math.floor(placeHolder / 10);
}

//this will revrse the order of the number 3
for(j = num3.length-1; j >= 0; j--) {
	reverse += num3.charAt(j);
}

//This will check if the last number is a palindrome
if(num4.length == 1) { palindrome = false; }
for(z = 0; z < num4.length/2; z++) {
	if(num4.charAt(z) != num4.charAt(num4.length-z-1)) {
		palindrome = false;
	}
}


console.log("Factorial of the 1st number is = " + fact);
console.log("The sum of all the digits in the 2nd Number is = " + sum);
console.log("The reverse of the 3rd number is = " + Number(reverse));
console.log("Is the 4th Number a palindrome (True/False?)"+ palindrome);