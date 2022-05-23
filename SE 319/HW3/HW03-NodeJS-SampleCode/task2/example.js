var rs = require('readline-sync');

var fNum1 = rs.question('1st Number: ');
var fNum2 = rs.question('2nd Number: ');
var action = rs.question('Enter the action{+,-,*,/,%}');

var result = fNum1 + action + fNum2

var result = eval(result);

console.log(result);
